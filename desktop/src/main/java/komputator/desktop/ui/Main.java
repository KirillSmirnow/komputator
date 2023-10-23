package komputator.desktop.ui;

import komputator.desktop.client.ComputationClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import static java.util.UUID.randomUUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class Main {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final ComputationClient computationClient;

    @EventListener(ApplicationReadyEvent.class)
    public void run() {
        var frame = new ComputationFrame();
        frame.setComputeAction((mode, n) -> {
            frame.getButtonEnabled().accept(false);
            if (mode == Mode.FOREGROUND) {
                compute(n, frame.getProgress(), frame.getResult());
                frame.getButtonEnabled().accept(true);
            } else {
                executor.submit(() -> {
                    compute(n, frame.getProgress(), frame.getResult());
                    frame.getButtonEnabled().accept(true);
                });
            }
        });
    }

    @SneakyThrows
    private void compute(long n, IntConsumer progress, Consumer<String> result) {
        result.accept("n = %s. Please wait...".formatted(n));
        var id = randomUUID();
        computationClient.startComputation(id, n);
        while (true) {
            var computationResult = computationClient.getComputationResult(id);
            log.info("Progress: {}%", computationResult.getCompletionPercentage());
            progress.accept(computationResult.getCompletionPercentage());
            if (computationResult.isCompleted()) {
                result.accept("Result: %s (error = %s)".formatted(computationResult.getValue(), computationResult.getError()));
                break;
            }
            Thread.sleep(1000);
        }
    }
}
