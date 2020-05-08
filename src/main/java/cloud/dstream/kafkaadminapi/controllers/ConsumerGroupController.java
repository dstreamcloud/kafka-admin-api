package cloud.dstream.kafkaadminapi.controllers;

import cloud.dstream.kafkaadminapi.dtos.ConsumerGroupDTO;
import cloud.dstream.kafkaadminapi.dtos.NewTopicDTO;
import cloud.dstream.kafkaadminapi.dtos.ResponseDTO;
import cloud.dstream.kafkaadminapi.dtos.TopicDTO;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/consumer-group")
public class ConsumerGroupController {
    @Autowired
    AdminClient adminClient;

    @GetMapping("")
    ResponseDTO list() throws Exception {
        ListConsumerGroupsResult result = adminClient.listConsumerGroups();
        Collection<ConsumerGroupListing> listing = result.all().get();
        return new ResponseDTO(listing.stream().map(cg -> new ConsumerGroupDTO(cg.groupId(),cg.isSimpleConsumerGroup())).toArray());
    }

    @GetMapping("/{id}")
    ResponseDTO get(@PathVariable("id") String id) throws Exception {
        DescribeConsumerGroupsResult result = adminClient.describeConsumerGroups(Collections.singleton(id));
        ConsumerGroupDescription desc = result.all().get().get(id);
        return new ResponseDTO(new ConsumerGroupDTO(desc));
    }

    @GetMapping("/{id}/offsets")
    ResponseDTO getOffset(@PathVariable("id") String id) throws Exception {
        ListConsumerGroupOffsetsResult result = adminClient.listConsumerGroupOffsets(id);
        List<ConsumerGroupDTO.OffsetDTO> offsets = new ArrayList<>();
        for (Map.Entry<TopicPartition, OffsetAndMetadata> entry : result.partitionsToOffsetAndMetadata().get().entrySet()) {
            offsets.add(new ConsumerGroupDTO.OffsetDTO(entry.getKey(), entry.getValue()));
        }
        return new ResponseDTO(offsets);
    }

    @DeleteMapping("/{id}")
    ResponseDTO delete(@PathVariable("id") String id) throws Exception {
        DeleteConsumerGroupsResult result = adminClient.deleteConsumerGroups(Collections.singleton(id));
        result.all().get();
        return new ResponseDTO(new ConsumerGroupDTO(id));
    }
}
