package cloud.dstream.kafkaadminapi.controllers;

import cloud.dstream.kafkaadminapi.dtos.*;
import org.apache.kafka.clients.admin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/cluster")
public class ClusterController {
    @Autowired
    AdminClient adminClient;

    @GetMapping("")
    ResponseDTO list() throws Exception {
        DescribeClusterResult result = adminClient.describeCluster();
        return new ResponseDTO(
                new ClusterDTO(
                        result.clusterId().get(),
                        new BrokerDTO(result.controller().get()),
                        result.nodes().get().stream().map(node -> new BrokerDTO(node)).collect(Collectors.toList())
                )
        );
    }
}
