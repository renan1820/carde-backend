package br.com.carde.api.domain.repository;

import br.com.carde.api.domain.model.Vehicle;
import br.com.carde.api.domain.model.VehicleCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface VehicleRepository {
    Page<Vehicle> findAll(Pageable pageable);
    Page<Vehicle> findByCategory(VehicleCategory category, Pageable pageable);
    Optional<Vehicle> findById(String id);
    Vehicle save(Vehicle vehicle);
    void deleteById(String id);
    boolean existsById(String id);
}
