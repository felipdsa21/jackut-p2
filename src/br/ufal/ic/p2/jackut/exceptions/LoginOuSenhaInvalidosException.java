package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada ao tentar abrir uma sess�o com um login inexistente ou uma senha incorreta.
 */
public final class LoginOuSenhaInvalidosException extends Exception {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Login ou senha inv�lidos.";

    /**
     * Cria nova exce��o.
     */
    public LoginOuSenhaInvalidosException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public LoginOuSenhaInvalidosException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
