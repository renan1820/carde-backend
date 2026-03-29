package br.com.carde.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI cardeOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CARDE Museum API")
                        .description("API do Museu CARDE — maior museu de veículos da América Latina, Contagem/MG.")
                        .version("v1")
                        .contact(new Contact()
                                .name("Museu CARDE")
                                .email("tech@carde.com.br")))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
