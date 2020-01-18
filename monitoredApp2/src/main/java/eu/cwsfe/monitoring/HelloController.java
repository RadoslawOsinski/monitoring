package eu.cwsfe.monitoring;

import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /**
     * https://micrometer.io/docs/concepts
     */
    @Timed(value = "helloController.hello2")
    @GetMapping("/hello2")
    public String hello1 () {
        return "hello 2 test app 2";
    }

    /**
     * https://micrometer.io/docs/concepts
     */
    @Timed(value = "helloController.root")
    @GetMapping("/")
    public String root () {
        return "root app 2";
    }

}
