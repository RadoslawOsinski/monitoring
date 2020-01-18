package eu.cwsfe.monitoring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    /**
     * @return autowired bean witch is intercepted by zipkin.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
