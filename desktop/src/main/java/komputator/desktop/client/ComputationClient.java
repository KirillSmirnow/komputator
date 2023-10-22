package komputator.desktop.client;

import java.util.UUID;

public interface ComputationClient {

    void startComputation(UUID id, int n);

    ComputationResult getComputationResult(UUID id);
}
