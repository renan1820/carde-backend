package br.com.carde.api.domain.model;

public record AdminUser(Long id, String email, String passwordHash) {}
