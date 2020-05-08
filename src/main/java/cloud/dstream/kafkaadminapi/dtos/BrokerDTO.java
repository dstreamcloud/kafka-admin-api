package cloud.dstream.kafkaadminapi.dtos;

import org.apache.kafka.common.Node;

public class BrokerDTO {
    private Integer id;

    private String rack;

    private String host;

    private Integer port;

    public BrokerDTO(Integer id, String rack, String host, Integer port) {
        this.id = id;
        this.rack = rack;
        this.host = host;
        this.port = port;
    }

    public BrokerDTO(Node node) {
        this.id = node.id();
        this.rack = node.rack();
        this.host = node.host();
        this.port = node.port();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRack() {
        return rack;
    }

    public void setRack(String rack) {
        this.rack = rack;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
