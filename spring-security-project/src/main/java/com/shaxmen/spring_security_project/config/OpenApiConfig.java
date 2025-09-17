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
        title = "Spring Security API",
        version = "1.0.0",
        description = "API documentation for authentication and JWT-based security.",
        contact = @Contact(
            name = "Spring Security JWT Demo",
            email = "morningstar@gmail.com",
            url = "http://localhost:8080" // ✅ Updated to HTTP
        ),
        license = @License(
            name = "License name",
            url = "http://localhost:8080/license" // ✅ Updated to HTTP
        ),
        termsOfService = "http://localhost:8080/terms" // ✅ Updated to HTTP
    ),
    servers = {
        @Server(
            description = "Development Server (HTTP)",
            url = "http://localhost:8080" // ✅ Updated to HTTP
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
  // No additional code required here — configuration is annotation-based
}