package cloud.dstream.kafkaadminapi.dtos;

public class NewTopicDTO {
    private String name;

    private Integer numPartitions;

    private Short replicationFactor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumPartitions() {
        return numPartitions;
    }

    public void setNumPartitions(Integer numPartitions) {
        this.numPartitions = numPartitions;
    }

    public Short getReplicationFactor() {
        return replicationFactor;
    }

    public void setReplicationFactor(Short replicationFactor) {
        this.replicationFactor = replicationFactor;
    }
}
