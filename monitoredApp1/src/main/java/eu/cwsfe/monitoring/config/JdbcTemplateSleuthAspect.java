package eu.cwsfe.monitoring.config;

import brave.Span;
import brave.Tracer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("zipkin-jdbc-trace")
@Aspect
@Configuration
public class JdbcTemplateSleuthAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcTemplateSleuthAspect.class);
    private final Tracer tracer;

    @Autowired
    public JdbcTemplateSleuthAspect(Tracer tracer) {
        this.tracer = tracer;
    }

    @Around("execution (* org.springframework.jdbc.core.JdbcTemplate.*(..))")
    public Object traceJdbcCall(final ProceedingJoinPoint pjp) throws Throwable {
        LOGGER.info("Aspect invoked!");
        Span span = tracer.nextSpan().name("JdbcTemplate " + pjp.getSignature().getName());
        span.start();
        if (pjp.getArgs().length > 0) {
            Object arg = pjp.getArgs()[0];
            if (arg instanceof String) {
                span.tag("sql", (String) arg);
            }
        }
        try {
            return pjp.proceed();
        } catch (Exception e) {
            span.error(e);
            throw e;
        } finally {
            span.finish();
        }
    }
}
