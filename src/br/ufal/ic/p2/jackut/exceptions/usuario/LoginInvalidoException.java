package br.ufal.ic.p2.jackut.exceptions.usuario;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exceção lançada quando o login especificado é null ou uma string vazia.
 */
public final class LoginInvalidoException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Login inválido.";

    /**
     * Cria nova exceção.
     */
    public LoginInvalidoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public LoginInvalidoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
