package com.empresa.produtoapi.repository;

import com.empresa.produtoapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository para acesso aos dados de Produto
 * Extende JpaRepository para operações CRUD automáticas
 * 
 * @author Ricardo Modesto - Exercício Final Arquitetura de Software
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    /**
     * Busca produtos pelo nome (busca exata)
     * 
     * @param nome Nome do produto
     * @return Lista de produtos com o nome especificado
     */
    List<Produto> findByNome(String nome);

    /**
     * Busca produtos cujo nome contenha a string especificada (busca parcial)
     * 
     * @param nome Parte do nome do produto
     * @return Lista de produtos que contêm o nome especificado
     */
    List<Produto> findByNomeContainingIgnoreCase(String nome);

    /**
     * Busca produtos por categoria
     * 
     * @param categoria Categoria do produto
     * @return Lista de produtos da categoria especificada
     */
    List<Produto> findByCategoria(String categoria);

    /**
     * Busca produtos por categoria (case insensitive)
     * 
     * @param categoria Categoria do produto
     * @return Lista de produtos da categoria especificada
     */
    List<Produto> findByCategoriaIgnoreCase(String categoria);

    /**
     * Verifica se existe um produto com o nome especificado
     * 
     * @param nome Nome do produto
     * @return true se existe, false caso contrário
     */
    boolean existsByNome(String nome);

}
