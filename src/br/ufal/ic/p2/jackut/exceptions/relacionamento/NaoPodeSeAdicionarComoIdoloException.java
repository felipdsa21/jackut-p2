package br.ufal.ic.p2.jackut.exceptions.relacionamento;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exce��o lan�ada quando o usu�rio tenta se adicionar como �dolo.
 */
public final class NaoPodeSeAdicionarComoIdoloException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Usu�rio n�o pode ser f� de si mesmo.";

    /**
     * Cria nova exce��o.
     */
    public NaoPodeSeAdicionarComoIdoloException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public NaoPodeSeAdicionarComoIdoloException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
