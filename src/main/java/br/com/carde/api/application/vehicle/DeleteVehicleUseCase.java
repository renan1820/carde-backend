package br.com.carde.api.application.vehicle;

import br.com.carde.api.domain.repository.VehicleRepository;
import br.com.carde.api.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteVehicleUseCase {

    private final VehicleRepository repository;

    @Transactional
    public void execute(String id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Vehicle", id);
        }
        repository.deleteById(id);
    }
}
