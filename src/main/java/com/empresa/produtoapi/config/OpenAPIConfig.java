package com.empresa.produtoapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do OpenAPI/Swagger para documentação da API
 * 
 * @author Ricardo Modesto - Exercício Final Arquitetura de Software
 */
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST de Produtos - Exercício Final")
                        .version("1.0.0")
                        .description("API RESTful para gerenciamento de produtos de e-commerce. " +
                                     "Implementada seguindo o padrão arquitetural MVC com Spring Boot. " +
                                     "Bootcamp Arquiteto(a) de Software - Desafio Final.")
                        .contact(new Contact()
                                .name("Ricardo Modesto")
                                .email("ricardo.modesto@email.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                );
    }

}
