package br.ufal.ic.p2.jackut.exceptions;

/**
 * Classe base para todas as exce��es do sistema.
 */
public abstract class JackutException extends Exception {
    /**
     * Cria nova exce��o.
     *
     * @param message a mensagem.
     */
    public JackutException(String message) {
        super(message);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param message a mensagem.
     * @param cause a causa.
     */
    public JackutException(String message, Throwable cause) {
        super(message, cause);
    }
}
