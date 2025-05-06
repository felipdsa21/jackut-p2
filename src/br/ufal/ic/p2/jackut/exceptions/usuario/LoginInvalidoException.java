package br.ufal.ic.p2.jackut.exceptions.usuario;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exce��o lan�ada quando o login especificado � null ou uma string vazia.
 */
public final class LoginInvalidoException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Login inv�lido.";

    /**
     * Cria nova exce��o.
     */
    public LoginInvalidoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public LoginInvalidoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
