package br.ufal.ic.p2.jackut.exceptions.relacionamento;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exce��o lan�ada quando um usu�rio tenta adicionar outro como paquera novamente.
 */
public final class JaEhPaqueraException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Usu�rio j� est� adicionado como paquera.";

    /**
     * Cria nova exce��o.
     */
    public JaEhPaqueraException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public JaEhPaqueraException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
