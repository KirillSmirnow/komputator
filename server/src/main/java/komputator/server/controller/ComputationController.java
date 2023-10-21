package komputator.server.controller;

import komputator.server.service.ComputationRequest;
import komputator.server.service.ComputationResult;
import komputator.server.service.ComputationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ComputationController {

    private final ComputationService computationService;

    @PutMapping("/computations/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ComputationResult startComputation(@PathVariable UUID id, @RequestBody ComputationRequest request) {
        return computationService.startComputation(id, request);
    }

    @GetMapping("/computations/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ComputationResult getComputationResult(@PathVariable UUID id) {
        return computationService.getComputationResult(id);
    }
}
