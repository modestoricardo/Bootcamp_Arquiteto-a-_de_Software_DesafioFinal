# 🚀 Como Importar e Executar no IntelliJ IDEA

## 📥 Passo 1: Importar o Projeto

### Opção A: Abrir Projeto Existente

1. Abra o IntelliJ IDEA
2. Clique em **File → Open**
3. Navegue até a pasta `produto-api`
4. Selecione a pasta e clique em **OK**
5. Aguarde o IntelliJ detectar que é um projeto Maven
6. Clique em **Trust Project** quando solicitado

### Opção B: Importar como Projeto Maven

1. Abra o IntelliJ IDEA
2. Clique em **File → New → Project from Existing Sources**
3. Selecione a pasta `produto-api`
4. Escolha **Import project from external model**
5. Selecione **Maven**
6. Clique em **Next** e depois **Finish**

---

## ⚙️ Passo 2: Configurar o Projeto

### Verificar o JDK

1. Vá em **File → Project Structure** (ou `Ctrl+Alt+Shift+S`)
2. Em **Project Settings → Project**
3. Certifique-se de que **SDK** está configurado para **Java 17** ou superior
4. Se não tiver Java 17, clique em **Add SDK → Download JDK**
5. Selecione versão 17 ou superior (recomendo Amazon Corretto 17 ou Oracle JDK 17)

### Sincronizar Dependências Maven

1. Aguarde o IntelliJ baixar todas as dependências automaticamente
2. Se não iniciar automaticamente, clique no ícone Maven no canto superior direito
3. Ou clique com botão direito em `pom.xml` → **Maven → Reload Project**

---

## ▶️ Passo 3: Executar a Aplicação

### Opção A: Executar pela Classe Principal

1. Navegue até: `src/main/java/com/empresa/produtoapi/ProdutoApiApplication.java`
2. Clique com botão direito na classe
3. Selecione **Run 'ProdutoApiApplication'**
4. Ou use o atalho: `Ctrl+Shift+F10`

### Opção B: Executar pelo Maven

1. Abra a aba **Maven** no lado direito (ou `View → Tool Windows → Maven`)
2. Expanda **produto-api → Plugins → spring-boot**
3. Clique duas vezes em **spring-boot:run**

### Opção C: Via Terminal Integrado

1. Abra o terminal integrado: `View → Tool Windows → Terminal`
2. Execute:
```bash
mvn spring-boot:run
```

---

## ✅ Passo 4: Verificar se Está Funcionando

Após executar, você deve ver no console:

```
========================================
🚀 API REST de Produtos está rodando!
========================================
📍 URL Base: http://localhost:8080/produtos
📚 Swagger UI: http://localhost:8080/swagger-ui.html
💾 H2 Console: http://localhost:8080/h2-console
========================================
```

### Testar a API

Abra o navegador e acesse:

1. **Swagger UI**: http://localhost:8080/swagger-ui.html
   - Interface interativa para testar todos os endpoints

2. **Listar Produtos**: http://localhost:8080/produtos
   - Deve retornar JSON com 25 produtos

3. **H2 Console**: http://localhost:8080/h2-console
   - JDBC URL: `jdbc:h2:mem:produtodb`
   - Username: `sa`
   - Password: (deixar vazio)

---

## 🧪 Passo 5: Executar os Testes

### Via IntelliJ

1. Clique com botão direito na pasta `src/test/java`
2. Selecione **Run 'All Tests'**
3. Ou use o atalho: `Ctrl+Shift+F10`

### Via Maven

1. Na aba **Maven**, expanda **Lifecycle**
2. Clique duas vezes em **test**

### Via Terminal

```bash
mvn test
```

---

## 🔧 Solução de Problemas Comuns

### Erro: "Project JDK is not defined"

**Solução:**
1. Vá em **File → Project Structure**
2. Em **Project**, selecione um JDK 17 ou superior
3. Clique em **Apply** e **OK**

### Erro: "Cannot resolve symbol 'lombok'"

**Solução:**
1. Vá em **File → Settings** (ou `Ctrl+Alt+S`)
2. Navegue até **Plugins**
3. Procure por "Lombok"
4. Instale o plugin Lombok
5. Reinicie o IntelliJ

### Erro: Dependências não baixadas

**Solução:**
1. Clique com botão direito em `pom.xml`
2. Selecione **Maven → Reload Project**
3. Aguarde o download das dependências

### Porta 8080 já em uso

**Solução:**
1. Abra `src/main/resources/application.properties`
2. Altere a linha: `server.port=8080` para outra porta (ex: 8081)
3. Ou encerre o processo que está usando a porta 8080

---

## 📝 Atalhos Úteis do IntelliJ

| Atalho | Ação |
|--------|------|
| `Ctrl+Shift+F10` | Executar classe/teste atual |
| `Shift+F10` | Re-executar última configuração |
| `Ctrl+F5` | Re-executar (sem debug) |
| `Shift+F9` | Debug |
| `Ctrl+F2` | Parar execução |
| `Alt+5` | Abrir aba Debug |
| `Alt+4` | Abrir aba Run |

---

## 🎯 Próximos Passos

1. ✅ Explore a documentação Swagger: http://localhost:8080/swagger-ui.html
2. ✅ Teste os endpoints usando o Swagger ou Postman
3. ✅ Explore o código fonte
4. ✅ Execute os testes unitários
5. ✅ Customize conforme sua necessidade

---

## 📚 Recursos Adicionais

- [Documentação Técnica](DOCUMENTACAO_TECNICA.md)
- [README Principal](README.md)
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [IntelliJ IDEA Help](https://www.jetbrains.com/help/idea/)

---

**Desenvolvido por Ricardo Modesto**  
**Exercício Final - Bootcamp Arquiteto(a) de Software**

Se tiver alguma dúvida, consulte a documentação ou entre em contato!
