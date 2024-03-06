package com.tingshulien.card.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "Card microservice API documentation",
        version = "v1",
        description = "Bank card microservice API documentation for account creation, update, fetch and delete operations.",
        contact = @Contact(
            name = "Tim Lien",
            email = "timlientw@gmail.com",
            url = "https://www.linkedin.com/in/timlientw/"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "http://www.apache.org/licenses/LICENSE-2.0.html"
        )
    ),
    externalDocs = @ExternalDocumentation(
        description = "More about Swagger",
        url = "https://swagger.io"
    )
)
@Configuration
public class SwaggerConfig {

}

