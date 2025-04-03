package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando a senha especificado � null ou uma string vazia.
 */
public final class SenhaInvalidaException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Senha inv�lida.";

    /**
     * Cria nova exce��o.
     */
    public SenhaInvalidaException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public SenhaInvalidaException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
