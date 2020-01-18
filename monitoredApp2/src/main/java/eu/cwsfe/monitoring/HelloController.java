package eu.cwsfe.monitoring;

import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    /**
     * https://micrometer.io/docs/concepts
     */
    @Timed(value = "helloController.hello2")
    @GetMapping("/hello2")
    public String hello1 () {
        LOGGER.info("hello2 invoked");
        return "hello 2 test app 2. Now: " + Instant.now();
    }

    /**
     * https://micrometer.io/docs/concepts
     */
    @Timed(value = "helloController.root")
    @GetMapping("/")
    public String root () {
        LOGGER.info("root invoked");
        return "root app 2";
    }

}
