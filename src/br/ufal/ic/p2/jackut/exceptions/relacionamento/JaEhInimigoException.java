package br.ufal.ic.p2.jackut.exceptions.relacionamento;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exce��o lan�ada quando um usu�rio tenta adicionar outro como inimigo novamente.
 */
public final class JaEhInimigoException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Usu�rio j� est� adicionado como inimigo.";

    /**
     * Cria nova exce��o.
     */
    public JaEhInimigoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public JaEhInimigoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
