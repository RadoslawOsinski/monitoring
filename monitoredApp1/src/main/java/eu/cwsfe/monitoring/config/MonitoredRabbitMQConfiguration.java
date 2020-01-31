package eu.cwsfe.monitoring.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonitoredRabbitMQConfiguration {

    public static final String TEST_MESSAGE_QUEUE = "test.message.queue";

    @Bean
    public Queue helloWorldQueue() {
        return new Queue(TEST_MESSAGE_QUEUE, true);
    }

}
