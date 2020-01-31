package eu.cwsfe.monitoring.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@RabbitListener
@Service
public class TestMessageHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestMessageHandler.class);

    public void handleMessage(String message) {
        LOGGER.info("Received from rabbit queue: {}", message);
    }

}
