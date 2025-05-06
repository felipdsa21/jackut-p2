package br.ufal.ic.p2.jackut.exceptions.usuario;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exce��o lan�ada quando o atributo requisitado n�o estiver preenchido.
 */
public final class AtributoNaoPreenchidoException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Atributo n�o preenchido.";

    /**
     * Cria nova exce��o.
     */
    public AtributoNaoPreenchidoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public AtributoNaoPreenchidoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
