package com.empresa.produtoapi.controller;

import com.empresa.produtoapi.model.Produto;
import com.empresa.produtoapi.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para gerenciamento de Produtos
 * Expõe endpoints para operações CRUD e consultas
 * 
 * @author Ricardo Modesto - Exercício Final Arquitetura de Software
 */
@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "API para gerenciamento de produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    /**
     * Lista todos os produtos cadastrados
     * 
     * @return Lista de produtos
     */
    @GetMapping
    @Operation(summary = "Listar todos os produtos", description = "Retorna uma lista com todos os produtos cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")
    public ResponseEntity<List<Produto>> listarTodos() {
        List<Produto> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    /**
     * Busca um produto por ID
     * 
     * @param id ID do produto
     * @return Produto encontrado
     */
    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Retorna um produto específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto encontrado"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Produto> buscarPorId(
            @Parameter(description = "ID do produto", required = true)
            @PathVariable Long id) {
        Produto produto = produtoService.buscarPorIdOuLancarExcecao(id);
        return ResponseEntity.ok(produto);
    }

    /**
     * Busca produtos por nome (busca parcial)
     * 
     * @param nome Nome ou parte do nome do produto
     * @return Lista de produtos encontrados
     */
    @GetMapping("/nome/{nome}")
    @Operation(summary = "Buscar produtos por nome", description = "Retorna produtos que contenham o nome especificado")
    @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")
    public ResponseEntity<List<Produto>> buscarPorNome(
            @Parameter(description = "Nome ou parte do nome do produto", required = true)
            @PathVariable String nome) {
        List<Produto> produtos = produtoService.buscarPorNome(nome);
        return ResponseEntity.ok(produtos);
    }

    /**
     * Busca produtos por categoria
     * 
     * @param categoria Categoria do produto
     * @return Lista de produtos da categoria
     */
    @GetMapping("/categoria/{categoria}")
    @Operation(summary = "Buscar produtos por categoria", description = "Retorna produtos de uma categoria específica")
    @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")
    public ResponseEntity<List<Produto>> buscarPorCategoria(
            @Parameter(description = "Categoria do produto", required = true)
            @PathVariable String categoria) {
        List<Produto> produtos = produtoService.buscarPorCategoria(categoria);
        return ResponseEntity.ok(produtos);
    }

    /**
     * Conta o total de produtos cadastrados
     * 
     * @return Número total de produtos
     */
    @GetMapping("/contar")
    @Operation(summary = "Contar produtos", description = "Retorna o número total de produtos cadastrados")
    @ApiResponse(responseCode = "200", description = "Contagem retornada com sucesso")
    public ResponseEntity<Long> contar() {
        long total = produtoService.contarProdutos();
        return ResponseEntity.ok(total);
    }

    /**
     * Cria um novo produto
     * 
     * @param produto Dados do produto a ser criado
     * @return Produto criado
     */
    @PostMapping
    @Operation(summary = "Criar novo produto", description = "Cadastra um novo produto no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<Produto> criar(
            @Parameter(description = "Dados do produto", required = true)
            @Valid @RequestBody Produto produto) {
        Produto produtoSalvo = produtoService.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    /**
     * Atualiza um produto existente
     * 
     * @param id ID do produto a ser atualizado
     * @param produto Dados atualizados do produto
     * @return Produto atualizado
     */
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto", description = "Atualiza os dados de um produto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<Produto> atualizar(
            @Parameter(description = "ID do produto", required = true)
            @PathVariable Long id,
            @Parameter(description = "Dados atualizados do produto", required = true)
            @Valid @RequestBody Produto produto) {
        Produto produtoAtualizado = produtoService.atualizar(id, produto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    /**
     * Deleta um produto
     * 
     * @param id ID do produto a ser deletado
     * @return Resposta sem conteúdo
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar produto", description = "Remove um produto do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do produto", required = true)
            @PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
