package com.empresa.produtoapi;

import com.empresa.produtoapi.controller.ProdutoController;
import com.empresa.produtoapi.model.Produto;
import com.empresa.produtoapi.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testes de integração para ProdutoController
 * 
 * @author Ricardo Modesto - Exercício Final Arquitetura de Software
 */
@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @Test
    void deveListarTodosProdutos() throws Exception {
        // Arrange
        List<Produto> produtos = Arrays.asList(
            new Produto(1L, "Produto 1", "Descrição 1", new BigDecimal("100.00"), 10, "Categoria 1", null),
            new Produto(2L, "Produto 2", "Descrição 2", new BigDecimal("200.00"), 20, "Categoria 2", null)
        );
        
        when(produtoService.listarTodos()).thenReturn(produtos);

        // Act & Assert
        mockMvc.perform(get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nome").value("Produto 1"))
                .andExpect(jsonPath("$[1].nome").value("Produto 2"));
    }

    @Test
    void deveBuscarProdutoPorId() throws Exception {
        // Arrange
        Produto produto = new Produto(1L, "Produto Teste", "Descrição", 
                                     new BigDecimal("150.00"), 5, "Teste", null);
        
        when(produtoService.buscarPorIdOuLancarExcecao(1L)).thenReturn(produto);

        // Act & Assert
        mockMvc.perform(get("/produtos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Produto Teste"))
                .andExpect(jsonPath("$.preco").value(150.00));
    }

    @Test
    void deveContarProdutos() throws Exception {
        // Arrange
        when(produtoService.contarProdutos()).thenReturn(25L);

        // Act & Assert
        mockMvc.perform(get("/produtos/contar"))
                .andExpect(status().isOk())
                .andExpect(content().string("25"));
    }

    @Test
    void deveCriarNovoProduto() throws Exception {
        // Arrange
        Produto produto = new Produto(null, "Novo Produto", "Nova Descrição", 
                                     new BigDecimal("99.99"), 100, "Nova Categoria", null);
        Produto produtoSalvo = new Produto(1L, "Novo Produto", "Nova Descrição", 
                                          new BigDecimal("99.99"), 100, "Nova Categoria", null);
        
        when(produtoService.salvar(any(Produto.class))).thenReturn(produtoSalvo);

        // Act & Assert
        mockMvc.perform(post("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"Novo Produto\",\"descricao\":\"Nova Descrição\"," +
                        "\"preco\":99.99,\"quantidadeEstoque\":100,\"categoria\":\"Nova Categoria\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Novo Produto"));
    }

}
