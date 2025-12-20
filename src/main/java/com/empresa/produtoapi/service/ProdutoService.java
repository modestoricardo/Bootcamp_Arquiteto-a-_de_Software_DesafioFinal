package com.empresa.produtoapi.service;

import com.empresa.produtoapi.exception.ResourceNotFoundException;
import com.empresa.produtoapi.model.Produto;
import com.empresa.produtoapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service responsável pela lógica de negócio relacionada a Produtos
 * 
 * @author Ricardo Modesto - Exercício Final Arquitetura de Software
 */
@Service
@Transactional
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    /**
     * Lista todos os produtos cadastrados
     * 
     * @return Lista de todos os produtos
     */
    @Transactional(readOnly = true)
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    /**
     * Busca um produto por ID
     * 
     * @param id ID do produto
     * @return Optional contendo o produto se encontrado
     */
    @Transactional(readOnly = true)
    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    /**
     * Busca um produto por ID ou lança exceção se não encontrado
     * 
     * @param id ID do produto
     * @return Produto encontrado
     * @throws ResourceNotFoundException se o produto não for encontrado
     */
    @Transactional(readOnly = true)
    public Produto buscarPorIdOuLancarExcecao(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto", "id", id));
    }

    /**
     * Busca produtos por nome (busca parcial, case insensitive)
     * 
     * @param nome Nome ou parte do nome do produto
     * @return Lista de produtos encontrados
     */
    @Transactional(readOnly = true)
    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContainingIgnoreCase(nome);
    }

    /**
     * Busca produtos por categoria
     * 
     * @param categoria Categoria do produto
     * @return Lista de produtos da categoria
     */
    @Transactional(readOnly = true)
    public List<Produto> buscarPorCategoria(String categoria) {
        return produtoRepository.findByCategoriaIgnoreCase(categoria);
    }

    /**
     * Salva um novo produto
     * 
     * @param produto Produto a ser salvo
     * @return Produto salvo com ID gerado
     */
    public Produto salvar(Produto produto) {
        validarProduto(produto);
        return produtoRepository.save(produto);
    }

    /**
     * Atualiza um produto existente
     * 
     * @param id ID do produto a ser atualizado
     * @param produtoAtualizado Dados atualizados do produto
     * @return Produto atualizado
     * @throws ResourceNotFoundException se o produto não for encontrado
     */
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        Produto produtoExistente = buscarPorIdOuLancarExcecao(id);
        
        validarProduto(produtoAtualizado);
        
        // Atualiza os campos
        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
        produtoExistente.setCategoria(produtoAtualizado.getCategoria());
        
        return produtoRepository.save(produtoExistente);
    }

    /**
     * Deleta um produto por ID
     * 
     * @param id ID do produto a ser deletado
     * @throws ResourceNotFoundException se o produto não for encontrado
     */
    public void deletar(Long id) {
        Produto produto = buscarPorIdOuLancarExcecao(id);
        produtoRepository.delete(produto);
    }

    /**
     * Conta o total de produtos cadastrados
     * 
     * @return Número total de produtos
     */
    @Transactional(readOnly = true)
    public long contarProdutos() {
        return produtoRepository.count();
    }

    /**
     * Verifica se um produto existe pelo ID
     * 
     * @param id ID do produto
     * @return true se existe, false caso contrário
     */
    @Transactional(readOnly = true)
    public boolean existePorId(Long id) {
        return produtoRepository.existsById(id);
    }

    /**
     * Valida os dados do produto antes de salvar
     * 
     * @param produto Produto a ser validado
     * @throws IllegalArgumentException se houver erro de validação
     */
    private void validarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório");
        }
        
        if (produto.getPreco() == null || produto.getPreco().doubleValue() <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero");
        }
        
        if (produto.getQuantidadeEstoque() == null || produto.getQuantidadeEstoque() < 0) {
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa");
        }
    }

}
