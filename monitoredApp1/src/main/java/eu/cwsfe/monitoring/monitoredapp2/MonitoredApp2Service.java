package eu.cwsfe.monitoring.monitoredapp2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class MonitoredApp2Service {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoredApp2Service.class);
    private final RestTemplate restTemplate;
    private final String monitoredApp2Address;

    public MonitoredApp2Service(Environment environment, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.monitoredApp2Address = environment.getRequiredProperty("pl.cwsfe.monitoredApp2.url");
    }

    public String getMonitoredApp2Response() {
        String response = null;
        try {
            response = restTemplate.getForObject(monitoredApp2Address + "/hello2", String.class);
        } catch (RestClientException e) {
            LOGGER.info("Problem with communication with: {} at getMonitoredApp2Response", monitoredApp2Address, e);
        }
        return response;
    }

}
