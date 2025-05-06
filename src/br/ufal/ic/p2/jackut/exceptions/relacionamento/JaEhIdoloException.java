package br.ufal.ic.p2.jackut.exceptions.relacionamento;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exce��o lan�ada quando um usu�rio tenta adicionar outro como idolo novamente.
 */
public final class JaEhIdoloException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Usu�rio j� est� adicionado como �dolo.";

    /**
     * Cria nova exce��o.
     */
    public JaEhIdoloException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public JaEhIdoloException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
