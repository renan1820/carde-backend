package br.com.carde.api.infrastructure.persistence.jpa;

import br.com.carde.api.infrastructure.persistence.entity.AdminUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminUserJpaRepository extends JpaRepository<AdminUserEntity, Long> {
    Optional<AdminUserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
