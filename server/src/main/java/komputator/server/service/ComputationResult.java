package komputator.server.service;

import komputator.server.repository.Identifiable;
import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.util.UUID;

@Data
@Builder
public class ComputationResult implements Identifiable<UUID> {

    private final UUID id;
    private final ComputationRequest request;

    @With
    private final int completionPercentage;

    @With
    private final Double value;

    @With
    private final Double error;
}
