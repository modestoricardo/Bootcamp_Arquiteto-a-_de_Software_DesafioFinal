package com.empresa.produtoapi.exception;

/**
 * Exceção lançada quando um recurso não é encontrado
 * 
 * @author Ricardo Modesto - Exercício Final Arquitetura de Software
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s não encontrado(a) com %s: '%s'", resourceName, fieldName, fieldValue));
    }

}
