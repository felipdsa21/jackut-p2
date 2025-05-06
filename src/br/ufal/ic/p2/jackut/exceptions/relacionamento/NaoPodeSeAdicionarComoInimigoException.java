package br.ufal.ic.p2.jackut.exceptions.relacionamento;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exce��o lan�ada quando o usu�rio tenta se adicionar como inimigo.
 */
public final class NaoPodeSeAdicionarComoInimigoException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Usu�rio n�o pode ser inimigo de si mesmo.";

    /**
     * Cria nova exce��o.
     */
    public NaoPodeSeAdicionarComoInimigoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public NaoPodeSeAdicionarComoInimigoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
