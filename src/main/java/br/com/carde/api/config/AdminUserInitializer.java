package br.com.carde.api.config;

import br.com.carde.api.domain.repository.AdminUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminUserInitializer implements ApplicationRunner {

    private final AdminUserRepository adminUserRepository;

    @Value("${admin.email:admin@carde.com.br}")
    private String adminEmail;

    @Value("${ADMIN_PASSWORD:Admin@2025}")
    private String adminPassword;

    @Override
    public void run(ApplicationArguments args) {
        if (!adminUserRepository.existsByEmail(adminEmail)) {
            String hash = new BCryptPasswordEncoder(12).encode(adminPassword);
            adminUserRepository.save(adminEmail, hash);
            log.info("Admin user created: {}", adminEmail);
        }
    }
}
