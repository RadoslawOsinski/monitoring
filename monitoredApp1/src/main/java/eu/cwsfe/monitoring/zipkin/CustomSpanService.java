package eu.cwsfe.monitoring.zipkin;

import brave.Span;
import brave.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomSpanService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomSpanService.class);
    private final Tracer tracer;

    public CustomSpanService(Tracer tracer) {
        this.tracer = tracer;
    }

    public void registerCustomSpan(String message) throws InterruptedException {
        LOGGER.info("registerCustomSpan invoked");
        Span span = tracer.nextSpan().name("spanName");
        span.tag("tag1", "tag1 value");
        span.annotate("Test custom span: " + message);
        span.start();
        Thread.sleep(30);
        span.finish();
    }
}
