package br.com.carde.api.application.vehicle;

import br.com.carde.api.domain.model.Vehicle;
import br.com.carde.api.domain.repository.VehicleRepository;
import br.com.carde.api.exception.ResourceNotFoundException;
import br.com.carde.api.presentation.dto.request.ReorderItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReorderVehiclesUseCase {

    private final VehicleRepository repository;

    @Caching(evict = {
        @CacheEvict(cacheNames = "vehicles", allEntries = true),
        @CacheEvict(cacheNames = "vehicle-by-id", allEntries = true)
    })
    @Transactional
    public void execute(List<ReorderItemRequest> items) {
        items.forEach(item -> {
            Vehicle existing = repository.findById(item.id())
                    .orElseThrow(() -> new ResourceNotFoundException("Vehicle", item.id()));
            Vehicle reordered = new Vehicle(
                    existing.id(),
                    existing.name(),
                    existing.brand(),
                    existing.year(),
                    existing.category(),
                    existing.shortDescription(),
                    existing.fullHistory(),
                    existing.imageUrls(),
                    existing.engineSoundUrl(),
                    existing.specs(),
                    item.displayOrder()
            );
            repository.save(reordered);
        });
    }
}
