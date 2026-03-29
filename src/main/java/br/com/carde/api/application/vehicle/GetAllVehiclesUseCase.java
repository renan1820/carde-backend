package br.com.carde.api.application.vehicle;

import br.com.carde.api.domain.model.Vehicle;
import br.com.carde.api.domain.model.VehicleCategory;
import br.com.carde.api.domain.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetAllVehiclesUseCase {

    private final VehicleRepository repository;

    @Transactional(readOnly = true)
    public Page<Vehicle> execute(VehicleCategory category, Pageable pageable) {
        if (category != null) {
            return repository.findByCategory(category, pageable);
        }
        return repository.findAll(pageable);
    }
}
