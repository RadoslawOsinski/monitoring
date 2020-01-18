package eu.cwsfe.monitoring;

import eu.cwsfe.monitoring.history.History;
import eu.cwsfe.monitoring.history.HistoryRepository;
import eu.cwsfe.monitoring.monitoredapp2.MonitoredApp2Service;
import eu.cwsfe.monitoring.zipkin.CustomSpanService;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    private final HistoryRepository historyRepository;
    private final MonitoredApp2Service monitoredApp2Service;
    private final CustomSpanService customSpanService;

    public HelloController(
            HistoryRepository historyRepository, MonitoredApp2Service monitoredApp2Service,
            CustomSpanService customSpanService
    ) {
        this.historyRepository = historyRepository;
        this.monitoredApp2Service = monitoredApp2Service;
        this.customSpanService = customSpanService;
    }

    /**
     * https://micrometer.io/docs/concepts
     */
    @Timed(value = "helloController.hello1")
    @GetMapping("/hello1")
    public String hello1 () throws InterruptedException {
        LOGGER.info("hello2 invoked");
        History history = new History(UUID.randomUUID().toString().substring(0, 30), UUID.randomUUID().toString());
        historyRepository.add(history);
        String response = monitoredApp2Service.getMonitoredApp2Response();
        LOGGER.info("Response from second app: {}", response);
        customSpanService.registerCustomSpan(response);
        try {
            historyRepository.executeDatabaseError();
        } catch (BadSqlGrammarException ignored) {
            //ignored error
        }
        return "hello 1 test app 1";
    }

    /**
     * https://micrometer.io/docs/concepts
     */
    @Timed(value = "helloController.helloException")
    @GetMapping("/helloException")
    public String helloException() {
        LOGGER.info("helloException invoked");
        String response = monitoredApp2Service.getMonitoredApp2Response();
        LOGGER.info("Response from second app: {}", response);
        historyRepository.executeDatabaseError();
        return "helloException";
    }

    /**
     * https://micrometer.io/docs/concepts
     */
    @Timed(value = "helloController.root")
    @GetMapping("/")
    public String root () {
        LOGGER.info("root invoked");
        return "root app 1";
    }

}
