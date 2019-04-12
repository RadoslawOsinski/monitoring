package eu.cwsfe.monitoring;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by Radosław Osiński
 */
@Configuration
public class MetrixConfig {

    private final Environment environment;

    public MetrixConfig(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config()
                .commonTags("region", environment.getProperty("app.region"))
                .commonTags("name", environment.getProperty("app.name"))
                .commonTags("ip", environment.getProperty("app.ip"))
        ;
    }

}
