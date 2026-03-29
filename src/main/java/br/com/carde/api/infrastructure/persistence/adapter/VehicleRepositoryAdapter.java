package br.com.carde.api.infrastructure.persistence.adapter;

import br.com.carde.api.domain.model.Vehicle;
import br.com.carde.api.domain.model.VehicleCategory;
import br.com.carde.api.domain.repository.VehicleRepository;
import br.com.carde.api.infrastructure.persistence.jpa.VehicleJpaRepository;
import br.com.carde.api.infrastructure.persistence.mapper.VehicleEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VehicleRepositoryAdapter implements VehicleRepository {

    private final VehicleJpaRepository jpaRepository;
    private final VehicleEntityMapper mapper;

    @Override
    public Page<Vehicle> findAll(Pageable pageable) {
        return jpaRepository.findByActiveTrueOrderByDisplayOrderAsc(pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Vehicle> findByCategory(VehicleCategory category, Pageable pageable) {
        return jpaRepository.findByCategoryAndActiveTrueOrderByDisplayOrderAsc(category, pageable)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<Vehicle> findById(String id) {
        return jpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        return jpaRepository.findById(vehicle.id()).map(existing -> {
            // Update: merge into managed entity so orphanRemoval cleans up specs
            existing.setName(vehicle.name());
            existing.setBrand(vehicle.brand());
            existing.setYear(vehicle.year());
            existing.setCategory(vehicle.category());
            existing.setShortDescription(vehicle.shortDescription());
            existing.setFullHistory(vehicle.fullHistory());
            existing.setImageUrl(vehicle.imageUrl());
            existing.setEngineSoundUrl(vehicle.engineSoundUrl());
            existing.getSpecs().clear();
            mapper.addSpecs(existing, vehicle.specs());
            return mapper.toDomain(jpaRepository.save(existing));
        }).orElseGet(() -> mapper.toDomain(jpaRepository.save(mapper.toEntity(vehicle))));
    }

    @Override
    public void deleteById(String id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(String id) {
        return jpaRepository.existsById(id);
    }
}
