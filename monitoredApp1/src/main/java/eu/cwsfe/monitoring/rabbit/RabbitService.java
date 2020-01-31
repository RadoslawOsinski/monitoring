package eu.cwsfe.monitoring.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {

    private final RabbitTemplate tracedRabbitTemplate;

    public RabbitService(RabbitTemplate tracedRabbitTemplate) {
        this.tracedRabbitTemplate = tracedRabbitTemplate;
    }

    public void sendSomeMessage(String msg) {
        tracedRabbitTemplate.convertAndSend("test.message.queue", msg);
    }

}
