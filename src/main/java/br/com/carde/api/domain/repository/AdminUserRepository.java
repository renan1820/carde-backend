package br.com.carde.api.domain.repository;

import br.com.carde.api.domain.model.AdminUser;

import java.util.Optional;

public interface AdminUserRepository {
    Optional<AdminUser> findByEmail(String email);
    boolean existsByEmail(String email);
    void save(String email, String passwordHash);
}
