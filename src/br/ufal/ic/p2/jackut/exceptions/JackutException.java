package br.ufal.ic.p2.jackut.exceptions;

/**
 * Classe base para todas as exce��es do sistema.
 */
public abstract class JackutException extends Exception {
    /**
     * Cria nova exce��o.
     */
    public JackutException(String message) {
        super(message);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public JackutException(String message, Throwable cause) {
        super(message, cause);
    }
}
