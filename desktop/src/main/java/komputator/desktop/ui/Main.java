package komputator.desktop.ui;

import komputator.desktop.client.ComputationClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import static java.util.UUID.randomUUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class Main {

    private final ComputationClient computationClient;

    @EventListener(ApplicationReadyEvent.class)
    @SneakyThrows
    public void run() {
        var id = randomUUID();
        computationClient.startComputation(id, Integer.MAX_VALUE);
        while (true) {
            var computationResult = computationClient.getComputationResult(id);
            log.info("Progress: {}%", computationResult.getCompletionPercentage());
            if (computationResult.isCompleted()) {
                log.info("Result: {} (error = {})", computationResult.getValue(), computationResult.getError());
                break;
            }
            Thread.sleep(1000);
        }
    }
}
