package br.com.carde.api.application.vehicle;

import br.com.carde.api.domain.model.Vehicle;
import br.com.carde.api.domain.repository.VehicleRepository;
import br.com.carde.api.presentation.dto.request.VehicleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateVehicleUseCase {

    private final VehicleRepository repository;

    @CacheEvict(cacheNames = "vehicles", allEntries = true)
    @Transactional
    public Vehicle execute(VehicleRequest request) {
        String id = UUID.randomUUID().toString();
        Map<String, String> specs = buildSpecs(request);
        Vehicle vehicle = new Vehicle(
                id,
                request.name(),
                request.brand(),
                request.year(),
                request.category(),
                request.shortDescription(),
                request.fullHistory(),
                request.imageUrl(),
                request.engineSoundUrl(),
                specs
        );
        return repository.save(vehicle);
    }

    private Map<String, String> buildSpecs(VehicleRequest request) {
        Map<String, String> specs = new LinkedHashMap<>();
        if (request.specs() != null) {
            request.specs().stream()
                    .sorted((a, b) -> Integer.compare(a.sortOrder(), b.sortOrder()))
                    .forEach(s -> specs.put(s.key(), s.value()));
        }
        return specs;
    }
}
