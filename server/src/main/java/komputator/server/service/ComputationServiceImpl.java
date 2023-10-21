package komputator.server.service;

import komputator.server.repository.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class ComputationServiceImpl implements ComputationService {

    private final ExecutorService executor = Executors.newFixedThreadPool(4);

    private final Repository<ComputationResult> repository;

    @Override
    public ComputationResult startComputation(UUID id, ComputationRequest request) {
        var existingComputationResult = repository.get(id);
        if (existingComputationResult != null) {
            return existingComputationResult;
        }
        var newComputationResult = ComputationResult.builder()
                .id(id)
                .request(request)
                .build();
        repository.save(newComputationResult);
        executor.submit(() -> compute(id));
        return newComputationResult;
    }

    private void compute(UUID id) {
        var computationResult = repository.get(id);
        var n = computationResult.getRequest().getN();
        double result = 0;
        for (long k = 0; k < n; k++) {
            if (k % 2 == 0) {
                result += 1 / (2.0 * k + 1);
            } else {
                result -= 1 / (2.0 * k + 1);
            }
            updateProgress(computationResult, (int) Math.round((k + 1.0) / n * 100));
        }
        complete(computationResult, result * 4);
    }

    private void updateProgress(ComputationResult computationResult, int percentage) {
        repository.save(computationResult.withCompletionPercentage(percentage));
    }

    private void complete(ComputationResult computationResult, double value) {
        var error = Math.abs(Math.PI - value);
        repository.save(computationResult.withCompletionPercentage(100).withValue(value).withError(error));
    }

    @Override
    public ComputationResult getComputationResult(UUID id) {
        return repository.get(id);
    }
}
