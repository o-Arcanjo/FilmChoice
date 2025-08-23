package com.filmchoice.config;

import java.util.List; // <-- IMPORTAR

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI; // <-- IMPORTAR
import io.swagger.v3.oas.models.info.Contact; // <-- IMPORTAR
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // 1. Defina o nome do seu esquema de segurança. Usaremos este nome como referência.
        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("FilmChoice API")
                        .version("1.0.0")
                        .description("API para gerenciamento de filmes e usuários")
                        .contact(new Contact()
                                .name("Equipe FilmChoice")
                                .email("contato@filmchoice.com")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080" )
                                .description("Servidor de Desenvolvimento")
                ))
                // 2. Adicione o requisito de segurança global (o cadeado em todas as rotas)
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                // 3. Adicione a definição do componente de segurança (o que é "bearerAuth")
                .components(
                    new Components()
                        .addSecuritySchemes(securitySchemeName,
                            new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                );
    }
}
