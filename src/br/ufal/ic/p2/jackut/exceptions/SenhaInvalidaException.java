package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando a senha especificado é null ou uma string vazia.
 */
public final class SenhaInvalidaException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Senha inválida.";

    /**
     * Cria nova exceção.
     */
    public SenhaInvalidaException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public SenhaInvalidaException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
