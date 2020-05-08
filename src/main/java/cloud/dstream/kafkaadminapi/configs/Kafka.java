package cloud.dstream.kafkaadminapi.configs;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class Kafka {
    @Value("${kafka.servers}")
    private String[] servers;

    @Bean
    AdminClient adminClient() {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, String.join(",", servers));
        AdminClient client = AdminClient.create(properties);
        return client;
    }
}
