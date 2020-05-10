package com.algaworks.socialbooks.services.exceptions;

import java.util.function.Supplier;

public class AutorNaoEncontradoException extends RuntimeException {
    private static final long serialVersionUID = -5803245277562454147L;

    public AutorNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public AutorNaoEncontradoException(String mensagem, Throwable causa){
        super(mensagem,causa);
    }

}
