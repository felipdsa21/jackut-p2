package br.ufal.ic.p2.jackut.exceptions;

/**
 * Classe base para todas as exceções do sistema.
 */
public abstract class JackutException extends Exception {
    /**
     * Cria nova exceção.
     */
    public JackutException(String message) {
        super(message);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public JackutException(String message, Throwable cause) {
        super(message, cause);
    }
}
