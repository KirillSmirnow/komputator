package komputator.desktop.client;

import lombok.Data;

@Data
public class ComputationResult {

    private final int completionPercentage;
    private final Double value;
    private final Double error;

    public boolean isCompleted() {
        return value != null;
    }
}
