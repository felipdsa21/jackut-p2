package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada ao tentar abrir uma sessão com um login inexistente ou uma senha incorreta.
 */
public final class LoginOuSenhaInvalidosException extends Exception {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Login ou senha inválidos.";

    /**
     * Cria nova exceção.
     */
    public LoginOuSenhaInvalidosException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public LoginOuSenhaInvalidosException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
