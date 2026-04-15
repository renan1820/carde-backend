package br.com.carde.api.infrastructure.persistence.jpa;

import br.com.carde.api.domain.model.VehicleCategory;
import br.com.carde.api.infrastructure.persistence.entity.VehicleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VehicleJpaRepository extends JpaRepository<VehicleEntity, String> {

    Page<VehicleEntity> findByActiveTrueOrderByDisplayOrderAsc(Pageable pageable);

    Page<VehicleEntity> findByCategoryAndActiveTrueOrderByDisplayOrderAsc(
            VehicleCategory category, Pageable pageable);

    Optional<VehicleEntity> findByIdAndActiveTrue(String id);

    @Query("SELECT MAX(v.displayOrder) FROM VehicleEntity v WHERE v.active = true")
    Optional<Integer> findMaxDisplayOrder();
}
