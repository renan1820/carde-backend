package br.com.carde.api.infrastructure.persistence.adapter;

import br.com.carde.api.domain.model.AdminUser;
import br.com.carde.api.domain.repository.AdminUserRepository;
import br.com.carde.api.infrastructure.persistence.entity.AdminUserEntity;
import br.com.carde.api.infrastructure.persistence.jpa.AdminUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AdminUserRepositoryAdapter implements AdminUserRepository {

    private final AdminUserJpaRepository jpaRepository;

    @Override
    public Optional<AdminUser> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(e -> new AdminUser(e.getId(), e.getEmail(), e.getPasswordHash()));
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public void save(String email, String passwordHash) {
        jpaRepository.save(new AdminUserEntity(email, passwordHash));
    }
}
