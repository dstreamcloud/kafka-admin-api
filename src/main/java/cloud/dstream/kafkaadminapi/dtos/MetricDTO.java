package cloud.dstream.kafkaadminapi.dtos;

import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;

import java.util.Map;

public class MetricDTO<T extends Metric> {
    private String name;

    private String group;

    private String description;

    private Map<String, String> tags;

    private Object value;

    public MetricDTO(MetricName metric, T metricValue) {
        name = metric.name();
        group = metric.group();
        description = metric.description();
        tags = metric.tags();
        value = metricValue.metricValue();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
