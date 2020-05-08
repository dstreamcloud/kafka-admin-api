package cloud.dstream.kafkaadminapi.controllers;

import cloud.dstream.kafkaadminapi.dtos.*;
import org.apache.kafka.clients.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    AdminClient adminClient;

    @GetMapping("")
    ResponseDTO list() throws Exception {
        ListTopicsResult result = adminClient.listTopics();
        return new ResponseDTO(result.names().get().stream().map(name -> new TopicDTO(name)).toArray());
    }

    @GetMapping("/{name}")
    ResponseDTO get(@PathVariable("name") String name) throws Exception {
        DescribeTopicsResult result = adminClient.describeTopics(Collections.singleton(name));
        TopicDescription desc = result.all().get().get(name);
        return new ResponseDTO(new TopicDTO(desc));
    }

    @PostMapping("")
    ResponseDTO create(@RequestBody NewTopicDTO body) throws Exception {
        CreateTopicsResult result = adminClient.createTopics(Collections.singleton(new NewTopic(body.getName(), body.getNumPartitions(), body.getReplicationFactor())));
        result.all().get();
        return new ResponseDTO(new TopicDTO(body.getName()));
    }

    @DeleteMapping("/{name}")
    ResponseDTO delete(@PathVariable("name") String name) throws Exception {
        DeleteTopicsResult result = adminClient.deleteTopics(Collections.singleton(name));
        result.all().get();
        return new ResponseDTO(new TopicDTO(name));
    }
}
