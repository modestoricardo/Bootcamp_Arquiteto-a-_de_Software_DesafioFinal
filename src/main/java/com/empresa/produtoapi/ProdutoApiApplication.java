package com.empresa.produtoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação API REST de Produtos
 * 
 * @author Ricardo Modesto - Exercício Final Arquitetura de Software
 * @version 1.0.0
 */
@SpringBootApplication
public class ProdutoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProdutoApiApplication.class, args);
        System.out.println("\n========================================");
        System.out.println("🚀 API REST de Produtos está rodando!");
        System.out.println("========================================");
        System.out.println("📍 URL Base: http://localhost:8080/produtos");
        System.out.println("📚 Swagger UI: http://localhost:8080/swagger-ui.html");
        System.out.println("💾 H2 Console: http://localhost:8080/h2-console");
        System.out.println("========================================\n");
    }

}
