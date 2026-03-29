package br.com.carde.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class CardeApiApplicationTests {

    @Test
    void contextLoads() {
        // Verifica que o contexto Spring inicializa sem erros
        // Requer PostgreSQL rodando (docker compose up -d)
    }
}
