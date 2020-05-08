package cloud.dstream.kafkaadminapi.dtos;

import java.util.List;

public class ClusterDTO {
    private String id;

    private BrokerDTO controller;

    private List<BrokerDTO> nodes;

    public ClusterDTO(String id, BrokerDTO controller, List<BrokerDTO> nodes) {
        this.id = id;
        this.controller = controller;
        this.nodes = nodes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BrokerDTO getController() {
        return controller;
    }

    public void setController(BrokerDTO controller) {
        this.controller = controller;
    }

    public List<BrokerDTO> getNodes() {
        return nodes;
    }

    public void setNodes(List<BrokerDTO> nodes) {
        this.nodes = nodes;
    }
}
