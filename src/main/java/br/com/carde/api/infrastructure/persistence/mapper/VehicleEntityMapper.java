package br.com.carde.api.infrastructure.persistence.mapper;

import br.com.carde.api.domain.model.Vehicle;
import br.com.carde.api.infrastructure.persistence.entity.VehicleEntity;
import br.com.carde.api.infrastructure.persistence.entity.VehicleSpecEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class VehicleEntityMapper {

    public Vehicle toDomain(VehicleEntity entity) {
        Map<String, String> specs = new LinkedHashMap<>();
        if (entity.getSpecs() != null) {
            entity.getSpecs().forEach(s -> specs.put(s.getSpecKey(), s.getSpecValue()));
        }
        List<String> imageUrls = entity.getImageUrls() != null
                ? new ArrayList<>(entity.getImageUrls())
                : new ArrayList<>();
        return new Vehicle(
                entity.getId(),
                entity.getName(),
                entity.getBrand(),
                entity.getYear(),
                entity.getCategory(),
                entity.getShortDescription(),
                entity.getFullHistory(),
                imageUrls,
                entity.getEngineSoundUrl(),
                specs,
                entity.getDisplayOrder()
        );
    }

    public VehicleEntity toEntity(Vehicle vehicle) {
        VehicleEntity entity = new VehicleEntity();
        entity.setId(vehicle.id());
        entity.setName(vehicle.name());
        entity.setBrand(vehicle.brand());
        entity.setYear(vehicle.year());
        entity.setCategory(vehicle.category());
        entity.setShortDescription(vehicle.shortDescription());
        entity.setFullHistory(vehicle.fullHistory());
        if (vehicle.imageUrls() != null) {
            entity.getImageUrls().addAll(vehicle.imageUrls());
        }
        entity.setEngineSoundUrl(vehicle.engineSoundUrl());
        entity.setDisplayOrder(vehicle.displayOrder());
        entity.setActive(true);
        addSpecs(entity, vehicle.specs());
        return entity;
    }

    public void addSpecs(VehicleEntity entity, Map<String, String> specs) {
        if (specs == null) return;
        AtomicInteger order = new AtomicInteger(0);
        specs.forEach((key, value) -> {
            VehicleSpecEntity spec = new VehicleSpecEntity();
            spec.setVehicle(entity);
            spec.setSpecKey(key);
            spec.setSpecValue(value);
            spec.setSortOrder(order.getAndIncrement());
            entity.getSpecs().add(spec);
        });
    }
}
