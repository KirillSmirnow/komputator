package komputator.desktop.client;

import java.util.UUID;

public interface ComputationClient {

    void startComputation(UUID id, long n);

    ComputationResult getComputationResult(UUID id);
}
