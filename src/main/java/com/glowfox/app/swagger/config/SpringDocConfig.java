package com.glowfox.app.swagger.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Glofox API",
        version = "1.0",
        description = "API for managing classes and bookings on the Glofox platform"
    )
)
public class SpringDocConfig {
    // Additional configuration if necessary
}