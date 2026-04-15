package br.com.carde.api.application.vehicle;

import br.com.carde.api.domain.model.Vehicle;
import br.com.carde.api.domain.repository.VehicleRepository;
import br.com.carde.api.exception.ResourceNotFoundException;
import br.com.carde.api.presentation.dto.request.VehicleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UpdateVehicleUseCase {

    private final VehicleRepository repository;

    @Caching(evict = {
        @CacheEvict(cacheNames = "vehicle-by-id", key = "#id"),
        @CacheEvict(cacheNames = "vehicles", allEntries = true)
    })
    @Transactional
    public Vehicle execute(String id, VehicleRequest request) {
        Vehicle existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle", id));
        Map<String, String> specs = new LinkedHashMap<>();
        if (request.specs() != null) {
            request.specs().stream()
                    .sorted((a, b) -> Integer.compare(a.sortOrder(), b.sortOrder()))
                    .forEach(s -> specs.put(s.key(), s.value()));
        }
        Vehicle updated = new Vehicle(
                id,
                request.name(),
                request.brand(),
                request.year(),
                request.category(),
                request.shortDescription(),
                request.fullHistory(),
                request.imageUrls(),
                request.engineSoundUrl(),
                specs,
                existing.displayOrder()
        );
        return repository.save(updated);
    }
}
