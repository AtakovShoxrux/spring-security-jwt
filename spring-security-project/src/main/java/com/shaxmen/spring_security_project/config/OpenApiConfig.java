package com.shaxmen.spring_security_project.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(
            name = "Spring security JWT asymmetric Encryption demo",
            email = "morningstar@gmail.com",
            url = "https://localhost:8080"
        ),
        description = "Lorem",
        title = "OpenApi Specification",
        version = "6.9",
        license = @License(
            name = "License name",
            url = "https://localhost:8080/license"
        ),
        termsOfService = "https://localhost:8080/terms"
    ),
    servers = @Server(
        description = "Development server",
        url = "https://localhost:8080"
    )
)
@SecurityScheme(
    name = "bearerAuth",
    description = "JWT auth description",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
