package br.ufal.ic.p2.jackut.exceptions.comunidade;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exce��o lan�ada quando o usu�rio tenta entrar em uma comunidade da qual ele j� faz parte.
 */
public final class JaEhParteDaComunidadeException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Usuario j� faz parte dessa comunidade.";

    /**
     * Cria nova exce��o.
     */
    public JaEhParteDaComunidadeException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public JaEhParteDaComunidadeException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
