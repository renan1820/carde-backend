package br.com.carde.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
class CardeApiApplicationTests {

    @Test
    void contextLoads() {
        // Verifica que o contexto Spring inicializa sem erros
        // Local: requer PostgreSQL rodando (docker compose up -d)
        // CI: usa o serviço PostgreSQL do GitHub Actions
    }
}
