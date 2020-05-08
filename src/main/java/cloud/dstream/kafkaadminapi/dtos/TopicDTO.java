package cloud.dstream.kafkaadminapi.dtos;

import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.TopicPartitionInfo;

import java.util.ArrayList;
import java.util.List;

public class TopicDTO {
    private String name;

    private Boolean internal;

    private List<PartitionDTO> partitions;

    public TopicDTO(TopicDescription desc) {
        partitions = new ArrayList<>();
        for (TopicPartitionInfo info : desc.partitions()) {
            BrokerDTO leader = new BrokerDTO(info.leader().id(), info.leader().rack(), info.leader().host(), info.leader().port());
            List<BrokerDTO> isr = new ArrayList<>();
            for (Node node : info.isr()) {
                isr.add(new BrokerDTO(node));
            }
            List<BrokerDTO> replicas = new ArrayList<>();
            for (Node node : info.replicas()) {
                replicas.add(new BrokerDTO(node));
            }
            PartitionDTO partition = new PartitionDTO(info.partition(), leader, replicas, isr);
            partitions.add(partition);
        }
        internal = desc.isInternal();
        name = desc.name();
    }

    public TopicDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    public List<PartitionDTO> getPartitions() {
        return partitions;
    }

    public void setPartitions(List<PartitionDTO> partitions) {
        this.partitions = partitions;
    }
}
