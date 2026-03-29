package br.com.carde.api.application.vehicle;

import br.com.carde.api.domain.model.Vehicle;
import br.com.carde.api.domain.repository.VehicleRepository;
import br.com.carde.api.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetVehicleByIdUseCase {

    private final VehicleRepository repository;

    @Transactional(readOnly = true)
    public Vehicle execute(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle", id));
    }
}
