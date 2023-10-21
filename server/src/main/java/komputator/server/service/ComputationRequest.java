package komputator.server.service;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ComputationRequest {

    @Min(2)
    private final long n;
}
