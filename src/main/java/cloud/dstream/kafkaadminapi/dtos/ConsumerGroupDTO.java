package cloud.dstream.kafkaadminapi.dtos;

import org.apache.kafka.clients.admin.ConsumerGroupDescription;
import org.apache.kafka.clients.admin.MemberAssignment;
import org.apache.kafka.clients.admin.MemberDescription;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.List;
import java.util.stream.Collectors;

public class ConsumerGroupDTO {
    private String groupId;

    private Boolean isSimpleConsumerGroup;

    private BrokerDTO coordinator;

    private List<MemberDTO> members;

    private String partitionAssignor;

    private String state;

    public ConsumerGroupDTO(String groupId) {
        this.groupId = groupId;
    }

    public ConsumerGroupDTO(ConsumerGroupDescription desc) {
        groupId = desc.groupId();
        isSimpleConsumerGroup = desc.isSimpleConsumerGroup();
        coordinator = new BrokerDTO(desc.coordinator());
        members = desc.members().stream().map(m -> new MemberDTO(m)).collect(Collectors.toList());
        partitionAssignor = desc.partitionAssignor();
        state = desc.state().name();
    }

    public ConsumerGroupDTO(String groupId, Boolean isSimpleConsumerGroup) {
        this.groupId = groupId;
        this.isSimpleConsumerGroup = isSimpleConsumerGroup;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Boolean getSimpleConsumerGroup() {
        return isSimpleConsumerGroup;
    }

    public void setSimpleConsumerGroup(Boolean simpleConsumerGroup) {
        isSimpleConsumerGroup = simpleConsumerGroup;
    }

    public BrokerDTO getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(BrokerDTO coordinator) {
        this.coordinator = coordinator;
    }

    public List<MemberDTO> getMembers() {
        return members;
    }

    public void setMembers(List<MemberDTO> members) {
        this.members = members;
    }

    public String getPartitionAssignor() {
        return partitionAssignor;
    }

    public void setPartitionAssignor(String partitionAssignor) {
        this.partitionAssignor = partitionAssignor;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static class MemberDTO {
        private String consumerId;

        private String groupInstanceId;

        private String clientId;

        private String host;

        private MemberAssignmentDTO assignment;

        public MemberDTO(MemberDescription desc) {
            consumerId = desc.consumerId();
            groupInstanceId = desc.groupInstanceId().orElse("");
            clientId = desc.clientId();
            host = desc.host();
            assignment = new MemberAssignmentDTO(desc.assignment());
        }

        public String getConsumerId() {
            return consumerId;
        }

        public void setConsumerId(String consumerId) {
            this.consumerId = consumerId;
        }

        public String getGroupInstanceId() {
            return groupInstanceId;
        }

        public void setGroupInstanceId(String groupInstanceId) {
            this.groupInstanceId = groupInstanceId;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public MemberAssignmentDTO getAssignment() {
            return assignment;
        }

        public void setAssignment(MemberAssignmentDTO assignment) {
            this.assignment = assignment;
        }
    }

    public static class MemberAssignmentDTO {
        private List<TopicPartitionDTO> topicPartitions;

        public MemberAssignmentDTO(MemberAssignment assignment) {
            topicPartitions = assignment.topicPartitions().stream().map(part -> new TopicPartitionDTO(part)).collect(Collectors.toList());
        }
    }

    public static class TopicPartitionDTO {
       private Integer partition;

       private String topic;

       public TopicPartitionDTO(TopicPartition part) {
           partition = part.partition();
           topic = part.topic();
       }

        public Integer getPartition() {
            return partition;
        }

        public void setPartition(Integer partition) {
            this.partition = partition;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }
    }

    public static class OffsetDTO {
        private Integer partition;

        private String topic;

        private Long offset;

        private String metadata;

        private Integer leaderEpoch;

        public OffsetDTO(TopicPartition partition, OffsetAndMetadata oam) {
            this.partition = partition.partition();
            topic = partition.topic();
            offset = oam.offset();
            metadata = oam.metadata();
            leaderEpoch = oam.leaderEpoch().orElse(null);
        }

        public Integer getPartition() {
            return partition;
        }

        public void setPartition(Integer partition) {
            this.partition = partition;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public Long getOffset() {
            return offset;
        }

        public void setOffset(Long offset) {
            this.offset = offset;
        }

        public String getMetadata() {
            return metadata;
        }

        public void setMetadata(String metadata) {
            this.metadata = metadata;
        }

        public Integer getLeaderEpoch() {
            return leaderEpoch;
        }

        public void setLeaderEpoch(Integer leaderEpoch) {
            this.leaderEpoch = leaderEpoch;
        }
    }


}
