package eu.cwsfe.monitoring.rabbit;

import brave.spring.rabbit.SpringRabbitTracing;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonitoredRabbitMQConfiguration {

    public static final String TEST_MESSAGE_QUEUE = "test.message.queue";

    @Bean
    public Queue helloWorldQueue() {
        return new Queue(TEST_MESSAGE_QUEUE, true);
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter(TestMessageHandler testMessageHandler) {
        return new MessageListenerAdapter(testMessageHandler);
    }

    @Bean
    public SimpleRabbitListenerContainerFactory tracedRabbitListenerContainerFactory(
            SpringRabbitTracing springRabbitTracing,
            ConnectionFactory connectionFactory
    ) {
        return springRabbitTracing.newSimpleRabbitListenerContainerFactory(connectionFactory);
    }

    @Bean
    public SimpleMessageListenerContainer listenerContainer(
            ConnectionFactory tracedRabbitListenerContainerFactory,
            MessageListenerAdapter messageListenerAdapter
    ) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(tracedRabbitListenerContainerFactory);
        container.setQueueNames(TEST_MESSAGE_QUEUE);
        container.setMessageListener(messageListenerAdapter);
        return container;
    }

}
