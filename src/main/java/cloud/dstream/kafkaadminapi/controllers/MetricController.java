package cloud.dstream.kafkaadminapi.controllers;

import cloud.dstream.kafkaadminapi.dtos.MetricDTO;
import cloud.dstream.kafkaadminapi.dtos.ResponseDTO;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/metrics")
public class MetricController {
    @Autowired
    AdminClient adminClient;

    @GetMapping("")
    ResponseDTO get() throws Exception {
        List<MetricDTO> metrics = new ArrayList<>();
        for (Map.Entry<MetricName, ? extends Metric> entry: adminClient.metrics().entrySet()) {
            metrics.add(new MetricDTO(entry.getKey(), entry.getValue()));
        }
        return new ResponseDTO(metrics);
    }
}
