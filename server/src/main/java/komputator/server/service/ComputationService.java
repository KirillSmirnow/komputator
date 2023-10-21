package komputator.server.service;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public interface ComputationService {

    ComputationResult startComputation(UUID id, @Valid ComputationRequest request);

    ComputationResult getComputationResult(UUID id);
}
