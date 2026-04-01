package br.com.carde.api.application.auth;

import br.com.carde.api.config.JwtService;
import br.com.carde.api.domain.repository.AdminUserRepository;
import br.com.carde.api.presentation.dto.request.LoginRequest;
import br.com.carde.api.presentation.dto.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase {

    private final AdminUserRepository adminUserRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginResponse execute(LoginRequest request) {
        var user = adminUserRepository.findByEmail(request.email())
                .orElseThrow(() -> new InvalidCredentialsException());

        if (!passwordEncoder.matches(request.password(), user.passwordHash())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtService.generateToken(user.email());
        return new LoginResponse(token, user.email());
    }
}
