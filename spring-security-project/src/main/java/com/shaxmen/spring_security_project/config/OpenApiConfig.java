package com.shaxmen.spring_security_project.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
    info = @Info(
        title = "Spring Security API",
        version = "1.0.0",
        description = "API documentation for authentication and JWT-based security.",
        contact = @Contact(
            name = "Spring Security JWT Demo",
            email = "morningstar@gmail.com",
            url = "http://localhost:8080"
        ),
        license = @License(
            name = "License name",
            url = "http://localhost:8080/license"
        ),
        termsOfService = "http://localhost:8080/terms"
    ),
    servers = {
        @Server(
            description = "Development Server (HTTP)",
            url = "http://localhost:8080"
        )
    }
)
@SecurityScheme(
    name = "bearerAuth",
    description = "JWT Bearer authentication",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenApi() {
    return new OpenAPI()
        .info(new io.swagger.v3.oas.models.info.Info()
            .title("Spring Security JWT with asymmetric keys")
            .version("v1")
            .description("Spring Security JWT with asymmetric keys")
        )
        .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
        .components(new Components()
            .addSecuritySchemes("Bearer Authentication",
                new io.swagger.v3.oas.models.security.SecurityScheme()
                    .type(Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")
            ));
  }
}