package komputator.desktop.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ComputationClientImpl implements ComputationClient {

    private final RestTemplate restTemplate;

    @Override
    public void startComputation(UUID id, int n) {
        restTemplate.put("/computations/%s".formatted(id), Map.of("n", n));
    }

    @Override
    public ComputationResult getComputationResult(UUID id) {
        return restTemplate.getForObject("/computations/%s".formatted(id), ComputationResult.class);
    }
}
