package eu.cwsfe.monitoring;

import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /**
     * https://micrometer.io/docs/concepts
     */
    @Timed(value = "helloController.hello1")
    @GetMapping("/hello1")
    public String hello1 () {
        return "hello 1 test app 2";
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
