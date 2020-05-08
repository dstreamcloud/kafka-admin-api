package cloud.dstream.kafkaadminapi.dtos;

import java.util.List;

public class PartitionDTO {
    private Integer partition;

    private BrokerDTO leader;

    private List<BrokerDTO> replicas;

    private List<BrokerDTO> isr;

    public PartitionDTO(Integer partition, BrokerDTO leader, List<BrokerDTO> replicas, List<BrokerDTO> isr) {
        this.partition = partition;
        this.leader = leader;
        this.replicas = replicas;
        this.isr = isr;
    }

    public Integer getPartition() {
        return partition;
    }

    public void setPartition(Integer partition) {
        this.partition = partition;
    }

    public BrokerDTO getLeader() {
        return leader;
    }

    public void setLeader(BrokerDTO leader) {
        this.leader = leader;
    }

    public List<BrokerDTO> getReplicas() {
        return replicas;
    }

    public void setReplicas(List<BrokerDTO> replicas) {
        this.replicas = replicas;
    }

    public List<BrokerDTO> getIsr() {
        return isr;
    }

    public void setIsr(List<BrokerDTO> isr) {
        this.isr = isr;
    }
}
