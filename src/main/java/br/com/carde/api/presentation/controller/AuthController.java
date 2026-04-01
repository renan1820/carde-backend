package br.com.carde.api.presentation.controller;

import br.com.carde.api.application.auth.LoginUseCase;
import br.com.carde.api.presentation.dto.request.LoginRequest;
import br.com.carde.api.presentation.dto.response.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Autenticação do painel administrativo")
public class AuthController {

    private final LoginUseCase loginUseCase;

    @PostMapping("/login")
    @Operation(summary = "Login admin", description = "Retorna JWT com validade de 24h")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(loginUseCase.execute(request));
    }
}
