package br.ufal.ic.p2.jackut.exceptions.relacionamento;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exce��o lan�ada quando um usu�rio tenta enviar um convite para outro que j� seja amigo.
 */
public final class JaEhAmigoException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Usu�rio j� est� adicionado como amigo.";

    /**
     * Cria nova exce��o.
     */
    public JaEhAmigoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public JaEhAmigoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
