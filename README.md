# 🛍️ API REST de Produtos

[![Java](https://img.shields.io/badge/Java-17-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.1-brightgreen)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.8+-blue)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

> **Bootcamp Arquiteto(a) de Software - Desafio Final**  
> API RESTful para gerenciamento de produtos de e-commerce, implementada com padrão arquitetural MVC.

---

## 📋 Sobre o Projeto

Este projeto implementa uma **API REST completa** para gerenciamento de produtos, desenvolvida como parte do desafio final do Bootcamp de Arquitetura de Software. A API permite que parceiros da empresa consultem e integrem dados de produtos através de endpoints padronizados e bem documentados.

### ✨ Características Principais

- ✅ Arquitetura MVC (Model-View-Controller)
- ✅ Operações CRUD completas (Create, Read, Update, Delete)
- ✅ Persistência de dados com Spring Data JPA e H2
- ✅ Documentação automática com Swagger/OpenAPI
- ✅ Validação de dados com Bean Validation
- ✅ Tratamento centralizado de exceções
- ✅ Testes unitários e de integração
- ✅ Código limpo e bem documentado

---

## 🏗️ Arquitetura

O projeto segue o **padrão MVC em camadas**:

```
┌─────────────────────────────────────┐
│    Controller Layer (Apresentação)  │  ← Endpoints REST
├─────────────────────────────────────┤
│    Service Layer (Negócio)          │  ← Lógica de negócio
├─────────────────────────────────────┤
│    Repository Layer (Persistência)  │  ← Acesso a dados
├─────────────────────────────────────┤
│    Model Layer (Domínio)            │  ← Entidades
├─────────────────────────────────────┤
│    Database (H2)                    │  ← Banco de dados
└─────────────────────────────────────┘
```

Documentação completa da arquitetura disponível em: [DOCUMENTACAO_TECNICA.md](DOCUMENTACAO_TECNICA.md)

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| Java | 17+ | Linguagem de programação |
| Spring Boot | 3.2.1 | Framework principal |
| Spring Data JPA | 3.2.1 | Persistência de dados |
| H2 Database | 2.2.224 | Banco de dados em memória |
| SpringDoc OpenAPI | 2.3.0 | Documentação Swagger |
| Lombok | 1.18.30 | Redução de boilerplate |
| Maven | 3.8+ | Gerenciador de dependências |

---

## 📦 Estrutura do Projeto

```
produto-api/
├── src/
│   ├── main/
│   │   ├── java/com/empresa/produtoapi/
│   │   │   ├── controller/         # Controllers REST
│   │   │   ├── service/            # Lógica de negócio
│   │   │   ├── repository/         # Repositórios JPA
│   │   │   ├── model/              # Entidades de domínio
│   │   │   ├── exception/          # Exceções customizadas
│   │   │   ├── config/             # Configurações
│   │   │   └── ProdutoApiApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql            # Dados iniciais
│   └── test/                       # Testes
├── pom.xml
└── README.md
```

---

## 🚀 Como Executar

### Pré-requisitos

- Java 17 ou superior
- Maven 3.8 ou superior
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Passo 1: Clonar/Baixar o Projeto

```bash
# Se baixou o ZIP, descompacte
unzip produto-api.zip
cd produto-api
```

### Passo 2: Compilar o Projeto

```bash
mvn clean install
```

### Passo 3: Executar a Aplicação

**Opção A - Via Maven:**
```bash
mvn spring-boot:run
```

**Opção B - Via IDE:**
1. Abra o projeto na IDE
2. Execute a classe `ProdutoApiApplication.java`

**Opção C - Via JAR:**
```bash
java -jar target/produto-api-1.0.0.jar
```

### Passo 4: Acessar a Aplicação

- **API Base**: http://localhost:8080/produtos
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **H2 Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:produtodb`
  - Username: `sa`
  - Password: _(deixar vazio)_

---

## 📡 Endpoints da API

### Produtos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/produtos` | Lista todos os produtos |
| `GET` | `/produtos/{id}` | Busca produto por ID |
| `GET` | `/produtos/nome/{nome}` | Busca produtos por nome |
| `GET` | `/produtos/categoria/{categoria}` | Busca produtos por categoria |
| `GET` | `/produtos/contar` | Conta total de produtos |
| `POST` | `/produtos` | Cria novo produto |
| `PUT` | `/produtos/{id}` | Atualiza produto existente |
| `DELETE` | `/produtos/{id}` | Deleta produto |

### Exemplos de Requisições

#### Criar Produto (POST /produtos)

```json
{
  "nome": "Mouse Gamer RGB",
  "descricao": "Mouse gamer com iluminação RGB e 7 botões programáveis",
  "preco": 199.90,
  "quantidadeEstoque": 50,
  "categoria": "Periféricos"
}
```

#### Resposta

```json
{
  "id": 26,
  "nome": "Mouse Gamer RGB",
  "descricao": "Mouse gamer com iluminação RGB e 7 botões programáveis",
  "preco": 199.90,
  "quantidadeEstoque": 50,
  "categoria": "Periféricos",
  "dataCadastro": "2024-12-20T10:30:00"
}
```

---

## 🧪 Executar Testes

```bash
# Executar todos os testes
mvn test

# Executar testes com relatório de cobertura
mvn clean test jacoco:report
```

---

## 📊 Dados Iniciais

A aplicação vem pré-configurada com **25 produtos** em diversas categorias:
- Informática (notebooks, periféricos)
- Celulares e Tablets
- Áudio (fones, caixas de som)
- Games (consoles, controles)
- Smart Home (assistentes, lâmpadas)
- Fotografia (câmeras, drones)

Esses dados são carregados automaticamente através do arquivo `data.sql`.

---

## 📚 Documentação

### Swagger/OpenAPI

Acesse a documentação interativa da API em: http://localhost:8080/swagger-ui.html

A documentação inclui:
- Descrição de todos os endpoints
- Parâmetros necessários
- Exemplos de requisição/resposta
- Códigos de status HTTP
- Interface para testar a API

### Documentação Técnica

Para informações detalhadas sobre a arquitetura, componentes e decisões técnicas, consulte:
- [DOCUMENTACAO_TECNICA.md](DOCUMENTACAO_TECNICA.md)

---

## 🎯 Funcionalidades Implementadas

### Requisitos Obrigatórios ✅

- [x] CRUD completo (Create, Read, Update, Delete)
- [x] Contagem de registros
- [x] Find All (listar todos)
- [x] Find By ID (buscar por ID)
- [x] Find By Name (buscar por nome)
- [x] Padrão arquitetural MVC
- [x] Persistência de dados

### Funcionalidades Extras ✨

- [x] Busca por categoria
- [x] Validação de dados (Bean Validation)
- [x] Tratamento de exceções
- [x] Documentação Swagger/OpenAPI
- [x] Dados iniciais para testes
- [x] Console H2 para inspeção do banco
- [x] Logs formatados
- [x] Código bem documentado

---

## 🔧 Configurações

As principais configurações estão em `application.properties`:

```properties
# Porta do servidor
server.port=8080

# Banco de dados H2
spring.datasource.url=jdbc:h2:mem:produtodb

# Console H2
spring.h2.console.enabled=true

# Swagger UI
springdoc.swagger-ui.path=/swagger-ui.html
```

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Por favor:

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/NovaFuncionalidade`)
3. Commit suas mudanças (`git commit -m 'Add: nova funcionalidade'`)
4. Push para a branch (`git push origin feature/NovaFuncionalidade`)
5. Abra um Pull Request

---

## 📝 Licença

Este projeto está licenciado sob a licença Apache 2.0 - veja o arquivo [LICENSE](LICENSE) para detalhes.

---

## 👨‍💻 Autor

**Ricardo Modesto**

- Projeto: Exercício Final - Bootcamp Arquiteto(a) de Software
- Email: modestoricardo@uol.com.br
- GitHub: https://github.com/modestoricardo/Bootcamp_Arquiteto-a-_de_Software_DesafioFinal

---


