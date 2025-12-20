# Documentação Técnica - API REST de Produtos

## 📋 Índice
1. [Visão Geral do Projeto](#visão-geral-do-projeto)
2. [Arquitetura do Sistema](#arquitetura-do-sistema)
3. [Estrutura de Pastas](#estrutura-de-pastas)
4. [Componentes da Aplicação](#componentes-da-aplicação)
5. [Endpoints da API](#endpoints-da-api)
6. [Modelo de Dados](#modelo-de-dados)
7. [Tecnologias Utilizadas](#tecnologias-utilizadas)
8. [Configuração e Execução](#configuração-e-execução)
9. [Testes](#testes)

---

## 📖 Visão Geral do Projeto

### Contexto
Este projeto implementa uma **API REST** para gerenciamento de produtos de uma empresa de e-commerce. A API permite que parceiros da empresa consultem e integrem dados de produtos através de endpoints padronizados.

### Objetivo
Fornecer uma solução escalável, de fácil manutenção e bem documentada para disponibilizar publicamente dados de produtos aos parceiros da empresa.

### Características Principais
- ✅ API RESTful seguindo boas práticas
- ✅ Padrão arquitetural MVC (Model-View-Controller)
- ✅ Operações CRUD completas
- ✅ Persistência de dados com JPA/Hibernate
- ✅ Documentação automática com Swagger/OpenAPI
- ✅ Banco de dados H2 (em memória)
- ✅ Separação clara de responsabilidades em camadas

---

## 🏗️ Arquitetura do Sistema

### Padrão Arquitetural: MVC

A aplicação segue o padrão **MVC (Model-View-Controller)** adaptado para APIs REST, onde:

- **Model (Modelo)**: Representa as entidades de domínio e regras de negócio
- **View (Visão)**: Representada pelos DTOs e respostas JSON
- **Controller (Controlador)**: Gerencia as requisições HTTP e respostas

### Arquitetura em Camadas

```
┌─────────────────────────────────────────┐
│         CAMADA DE APRESENTAÇÃO          │
│         (Controller Layer)              │
│  - ProdutoController                    │
│  - GlobalExceptionHandler               │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│         CAMADA DE NEGÓCIO               │
│         (Service Layer)                 │
│  - ProdutoService                       │
│  - Validações e Regras                  │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│         CAMADA DE PERSISTÊNCIA          │
│         (Repository Layer)              │
│  - ProdutoRepository                    │
│  - Spring Data JPA                      │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│         CAMADA DE MODELO                │
│         (Model/Domain Layer)            │
│  - Produto (Entity)                     │
└─────────────────────────────────────────┘
                    ↓
┌─────────────────────────────────────────┐
│         BANCO DE DADOS                  │
│         (H2 Database)                   │
│  - Tabela: PRODUTO                      │
└─────────────────────────────────────────┘
```

### Fluxo de Dados (Request/Response)

**Requisição (Cliente → Servidor):**
```
Cliente HTTP
    ↓
ProdutoController (valida e recebe requisição)
    ↓
ProdutoService (aplica lógica de negócio)
    ↓
ProdutoRepository (acessa banco de dados)
    ↓
Banco de Dados H2
```

**Resposta (Servidor → Cliente):**
```
Banco de Dados H2
    ↓
ProdutoRepository (retorna entidade)
    ↓
ProdutoService (processa dados)
    ↓
ProdutoController (formata resposta JSON)
    ↓
Cliente HTTP
```

---

## 📁 Estrutura de Pastas

```
produto-api/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── empresa/
│   │   │           └── produtoapi/
│   │   │               │
│   │   │               ├── controller/
│   │   │               │   ├── ProdutoController.java
│   │   │               │   └── GlobalExceptionHandler.java
│   │   │               │
│   │   │               ├── service/
│   │   │               │   └── ProdutoService.java
│   │   │               │
│   │   │               ├── repository/
│   │   │               │   └── ProdutoRepository.java
│   │   │               │
│   │   │               ├── model/
│   │   │               │   └── Produto.java
│   │   │               │
│   │   │               ├── dto/ (opcional)
│   │   │               │   ├── ProdutoDTO.java
│   │   │               │   └── ProdutoCreateDTO.java
│   │   │               │
│   │   │               ├── config/ (opcional)
│   │   │               │   ├── OpenAPIConfig.java
│   │   │               │   └── CorsConfig.java
│   │   │               │
│   │   │               └── ProdutoApiApplication.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── schema.sql (opcional)
│   │       └── data.sql (opcional)
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── empresa/
│                   └── produtoapi/
│                       ├── ProdutoControllerTest.java
│                       ├── ProdutoServiceTest.java
│                       └── ProdutoRepositoryTest.java
│
├── pom.xml
├── .gitignore
└── README.md
```

---

## 🧩 Componentes da Aplicação

### 1. Controller Layer (Camada de Apresentação)

#### **ProdutoController.java**
**Responsabilidades:**
- Receber e validar requisições HTTP
- Mapear endpoints REST
- Retornar respostas HTTP apropriadas
- Delegar lógica de negócio para a camada Service

**Principais Anotações:**
- `@RestController`: Marca a classe como controlador REST
- `@RequestMapping("/produtos")`: Define o caminho base dos endpoints
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`: Mapeiam métodos HTTP

**Métodos Principais:**
```java
- listarTodos(): List<Produto>              // GET /produtos
- buscarPorId(Long id): ResponseEntity       // GET /produtos/{id}
- buscarPorNome(String nome): List<Produto>  // GET /produtos/nome/{nome}
- contar(): long                             // GET /produtos/contar
- salvar(Produto produto): Produto           // POST /produtos
- atualizar(Long id, Produto): Produto       // PUT /produtos/{id}
- deletar(Long id): void                     // DELETE /produtos/{id}
```

#### **GlobalExceptionHandler.java**
**Responsabilidades:**
- Tratamento centralizado de exceções
- Formatação de mensagens de erro
- Retorno de códigos HTTP apropriados

---

### 2. Service Layer (Camada de Negócio)

#### **ProdutoService.java**
**Responsabilidades:**
- Implementar lógica de negócio
- Validar regras de domínio
- Orquestrar chamadas aos repositórios
- Transformar dados quando necessário

**Principais Anotações:**
- `@Service`: Marca a classe como um serviço Spring
- `@Autowired`: Injeta dependências

**Métodos Principais:**
```java
- listarTodos(): List<Produto>
- buscarPorId(Long id): Optional<Produto>
- buscarPorNome(String nome): List<Produto>
- salvar(Produto produto): Produto
- atualizar(Long id, Produto produto): Produto
- deletar(Long id): void
- contarProdutos(): long
```

**Validações Implementadas:**
- Verificação de campos obrigatórios
- Validação de preço (não pode ser negativo)
- Validação de estoque (não pode ser negativo)
- Verificação de duplicidade de nome (opcional)

---

### 3. Repository Layer (Camada de Persistência)

#### **ProdutoRepository.java**
**Responsabilidades:**
- Abstração de acesso ao banco de dados
- Operações CRUD automáticas (via JPA)
- Queries personalizadas

**Principais Anotações:**
- `@Repository`: Marca a interface como repositório Spring
- Extends `JpaRepository<Produto, Long>`: Herda métodos CRUD

**Métodos:**
```java
// Métodos herdados do JpaRepository:
- findAll(): List<Produto>
- findById(Long id): Optional<Produto>
- save(Produto produto): Produto
- deleteById(Long id): void
- count(): long

// Métodos personalizados:
- findByNome(String nome): List<Produto>
- findByCategoria(String categoria): List<Produto> (opcional)
- findByPrecoLessThan(BigDecimal preco): List<Produto> (opcional)
```

**Vantagens do Spring Data JPA:**
- Redução de código boilerplate
- Queries geradas automaticamente
- Suporte a paginação e ordenação
- Integração com Hibernate

---

### 4. Model Layer (Camada de Modelo)

#### **Produto.java**
**Responsabilidades:**
- Representar a entidade de domínio
- Mapear estrutura para o banco de dados
- Definir validações básicas

**Principais Anotações:**
- `@Entity`: Marca a classe como entidade JPA
- `@Table(name = "produto")`: Define nome da tabela
- `@Id`: Define chave primária
- `@GeneratedValue`: Define estratégia de geração de ID
- `@Column`: Customiza mapeamento de colunas

**Atributos:**
```java
- id: Long                          // Identificador único
- nome: String                      // Nome do produto
- descricao: String                 // Descrição detalhada
- preco: BigDecimal                 // Preço do produto
- quantidadeEstoque: Integer        // Quantidade em estoque
- categoria: String                 // Categoria do produto
- dataCadastro: LocalDateTime       // Data de cadastro
```

**Validações (usando Bean Validation):**
```java
@NotNull(message = "Nome é obrigatório")
@Size(min = 3, max = 255)
private String nome;

@NotNull(message = "Preço é obrigatório")
@DecimalMin(value = "0.0", inclusive = false)
private BigDecimal preco;

@NotNull
@Min(0)
private Integer quantidadeEstoque;
```

---

### 5. Classe Principal

#### **ProdutoApiApplication.java**
**Responsabilidades:**
- Ponto de entrada da aplicação
- Configuração do Spring Boot
- Inicialização do contexto

**Estrutura:**
```java
@SpringBootApplication
public class ProdutoApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProdutoApiApplication.class, args);
    }
}
```

---

## 🌐 Endpoints da API

### Base URL
```
http://localhost:8080/produtos
```

### Documentação Swagger
```
http://localhost:8080/swagger-ui.html
```

### Endpoints Disponíveis

#### 1. Listar Todos os Produtos
```http
GET /produtos
```
**Resposta (200 OK):**
```json
[
  {
    "id": 1,
    "nome": "Notebook Dell",
    "descricao": "Notebook Dell Inspiron 15",
    "preco": 3500.00,
    "quantidadeEstoque": 10,
    "categoria": "Informática",
    "dataCadastro": "2024-12-20T10:30:00"
  }
]
```

#### 2. Buscar Produto por ID
```http
GET /produtos/{id}
```
**Parâmetros:**
- `id` (path): ID do produto

**Resposta (200 OK):**
```json
{
  "id": 1,
  "nome": "Notebook Dell",
  "descricao": "Notebook Dell Inspiron 15",
  "preco": 3500.00,
  "quantidadeEstoque": 10,
  "categoria": "Informática",
  "dataCadastro": "2024-12-20T10:30:00"
}
```

**Resposta (404 Not Found):**
```json
{
  "timestamp": "2024-12-20T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Produto não encontrado com ID: 1"
}
```

#### 3. Buscar Produtos por Nome
```http
GET /produtos/nome/{nome}
```
**Parâmetros:**
- `nome` (path): Nome do produto (busca parcial)

**Resposta (200 OK):**
```json
[
  {
    "id": 1,
    "nome": "Notebook Dell",
    "descricao": "Notebook Dell Inspiron 15",
    "preco": 3500.00,
    "quantidadeEstoque": 10,
    "categoria": "Informática",
    "dataCadastro": "2024-12-20T10:30:00"
  }
]
```

#### 4. Contar Produtos
```http
GET /produtos/contar
```
**Resposta (200 OK):**
```json
25
```

#### 5. Criar Novo Produto
```http
POST /produtos
Content-Type: application/json
```
**Body:**
```json
{
  "nome": "Mouse Logitech",
  "descricao": "Mouse sem fio Logitech MX Master 3",
  "preco": 350.00,
  "quantidadeEstoque": 50,
  "categoria": "Periféricos"
}
```

**Resposta (201 Created):**
```json
{
  "id": 2,
  "nome": "Mouse Logitech",
  "descricao": "Mouse sem fio Logitech MX Master 3",
  "preco": 350.00,
  "quantidadeEstoque": 50,
  "categoria": "Periféricos",
  "dataCadastro": "2024-12-20T10:35:00"
}
```

#### 6. Atualizar Produto
```http
PUT /produtos/{id}
Content-Type: application/json
```
**Parâmetros:**
- `id` (path): ID do produto a ser atualizado

**Body:**
```json
{
  "nome": "Mouse Logitech MX Master 3S",
  "descricao": "Mouse sem fio Logitech MX Master 3S - Nova versão",
  "preco": 380.00,
  "quantidadeEstoque": 45,
  "categoria": "Periféricos"
}
```

**Resposta (200 OK):**
```json
{
  "id": 2,
  "nome": "Mouse Logitech MX Master 3S",
  "descricao": "Mouse sem fio Logitech MX Master 3S - Nova versão",
  "preco": 380.00,
  "quantidadeEstoque": 45,
  "categoria": "Periféricos",
  "dataCadastro": "2024-12-20T10:35:00"
}
```

#### 7. Deletar Produto
```http
DELETE /produtos/{id}
```
**Parâmetros:**
- `id` (path): ID do produto a ser deletado

**Resposta (204 No Content)**

---

## 💾 Modelo de Dados

### Entidade: Produto

#### Estrutura da Tabela
```sql
CREATE TABLE produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    quantidade_estoque INT NOT NULL,
    categoria VARCHAR(100),
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### Relacionamentos
Por ser uma API simples, a entidade Produto não possui relacionamentos. Em uma aplicação mais complexa, poderia ter:
- **Categoria** (Many-to-One): Um produto pertence a uma categoria
- **Fornecedor** (Many-to-One): Um produto tem um fornecedor
- **ItemPedido** (One-to-Many): Um produto pode estar em vários pedidos

#### Índices
```sql
CREATE INDEX idx_produto_nome ON produto(nome);
CREATE INDEX idx_produto_categoria ON produto(categoria);
```

---

## 🛠️ Tecnologias Utilizadas

### Framework e Linguagem
- **Java 17+**: Linguagem de programação
- **Spring Boot 3.2.x**: Framework principal
- **Maven**: Gerenciador de dependências e build

### Dependências Spring Boot
- **Spring Web**: Para criar APIs REST
- **Spring Data JPA**: Para persistência de dados
- **Spring Boot DevTools**: Para desenvolvimento (hot reload)
- **Spring Boot Actuator**: Para monitoramento (opcional)

### Banco de Dados
- **H2 Database**: Banco de dados em memória (desenvolvimento)
- **Hibernate**: ORM (Object-Relational Mapping)

### Documentação
- **SpringDoc OpenAPI 3**: Para gerar documentação Swagger

### Validação
- **Jakarta Bean Validation**: Para validação de dados

### Testes
- **JUnit 5**: Framework de testes
- **Spring Boot Test**: Testes de integração
- **Mockito**: Mock de dependências

---

## ⚙️ Configuração e Execução

### Pré-requisitos
- Java 17 ou superior
- Maven 3.8+ ou IDE com suporte Maven (IntelliJ IDEA, Eclipse, VS Code)
- Git (opcional)

### Configuração do Projeto

#### application.properties
```properties
# Configuração do servidor
server.port=8080

# Configuração do H2 Database
spring.datasource.url=jdbc:h2:mem:produtodb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Configuração do JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Console do H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Configuração do OpenAPI/Swagger
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```

### Como Executar

#### Opção 1: Via Maven (linha de comando)
```bash
# Clonar o projeto (se estiver em repositório Git)
git clone https://github.com/sua-empresa/produto-api.git
cd produto-api

# Compilar e executar
mvn clean install
mvn spring-boot:run
```

#### Opção 2: Via IDE
1. Importar projeto Maven na IDE
2. Executar a classe `ProdutoApiApplication.java`

#### Opção 3: Via JAR
```bash
# Gerar o JAR
mvn clean package

# Executar o JAR
java -jar target/produto-api-1.0.0.jar
```

### Acessar a Aplicação

- **API**: http://localhost:8080/produtos
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **H2 Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:produtodb`
  - Username: `sa`
  - Password: (deixar vazio)

---

## 🧪 Testes

### Estrutura de Testes

#### Testes Unitários

**ProdutoServiceTest.java**
```java
// Testa lógica de negócio isoladamente
- testListarTodos()
- testBuscarPorId()
- testSalvar()
- testDeletar()
- testValidacoes()
```

**ProdutoRepositoryTest.java**
```java
// Testa queries do repositório
- testFindByNome()
- testSave()
- testDelete()
- testCount()
```

#### Testes de Integração

**ProdutoControllerTest.java**
```java
// Testa endpoints da API
- testGetAllProdutos()
- testGetProdutoById()
- testCreateProduto()
- testUpdateProduto()
- testDeleteProduto()
```

### Executar Testes

```bash
# Executar todos os testes
mvn test

# Executar testes com relatório de cobertura
mvn clean test jacoco:report
```

---

## 📊 Boas Práticas Implementadas

### 1. Separação de Responsabilidades
- Cada camada tem uma responsabilidade específica
- Controllers não contêm lógica de negócio
- Services não acessam diretamente o banco de dados

### 2. Princípios SOLID
- **Single Responsibility**: Cada classe tem uma única responsabilidade
- **Open/Closed**: Aberto para extensão, fechado para modificação
- **Dependency Inversion**: Dependência de abstrações (interfaces)

### 3. RESTful Best Practices
- Uso correto de métodos HTTP (GET, POST, PUT, DELETE)
- Códigos de status HTTP apropriados
- Recursos nomeados no plural
- Versionamento de API (pode ser adicionado)

### 4. Tratamento de Erros
- Exceções customizadas
- Mensagens de erro claras
- Logging adequado

### 5. Validação de Dados
- Validação no nível de entrada (Controller)
- Validação de regras de negócio (Service)
- Validação de integridade (Model/Entity)

### 6. Documentação
- Código autodocumentado
- Comentários quando necessário
- Swagger/OpenAPI para documentação de endpoints

---

## 🔒 Segurança (Próximos Passos)

Para ambiente de produção, considerar:
- **Autenticação**: Spring Security com JWT
- **Autorização**: Controle de acesso por roles
- **HTTPS**: Certificado SSL/TLS
- **CORS**: Configuração adequada
- **Rate Limiting**: Controle de requisições
- **Input Sanitization**: Prevenção de SQL Injection e XSS

---

## 📈 Melhorias Futuras

1. **Cache**: Implementar cache com Redis ou Caffeine
2. **Paginação**: Adicionar suporte a paginação e ordenação
3. **Filtros Avançados**: Permitir filtros combinados
4. **Auditoria**: Registrar quem criou/modificou produtos
5. **Versionamento**: API versionada (v1, v2)
6. **Containerização**: Docker e Docker Compose
7. **CI/CD**: Pipeline de integração e entrega contínua
8. **Monitoramento**: Integração com Prometheus/Grafana
9. **Logs Centralizados**: ELK Stack (Elasticsearch, Logstash, Kibana)
10. **Testes de Carga**: JMeter ou Gatling

---

## 📝 Notas de Desenvolvimento

### Convenções de Código
- **Nomenclatura**: CamelCase para classes, camelCase para métodos
- **Pacotes**: Todo minúsculo
- **Constantes**: UPPER_SNAKE_CASE
- **Indentação**: 4 espaços
- **Linha**: Máximo 120 caracteres

### Git Flow
```bash
# Branches principais
main         # Produção
develop      # Desenvolvimento

# Feature branches
feature/nome-da-funcionalidade
bugfix/nome-do-bug
hotfix/correção-urgente
```

---

## 📞 Suporte e Contato

**Desenvolvedor:**
- Nome: Ricardo Modesto
- Projeto: Exercício Final - Bootcamp Arquiteto de Software
- Email: ricardo.modesto@email.com

---

## 📜 Licença

Este projeto foi desenvolvido como exercício acadêmico do Bootcamp Arquiteto(a) de Software.

---

**Versão do Documento:** 1.0.0  
**Data da Última Atualização:** 20/12/2024  
**Autor:** Ricardo Modesto - Exercício Final Arquitetura de Software
