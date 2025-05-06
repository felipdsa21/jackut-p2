package br.ufal.ic.p2.jackut.exceptions.relacionamento;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exce��o lan�ada quando um usu�rio tenta usar uma fun��o que � inv�lida quando chamada com um inimigo.
 */
public final class NaoPodeUsarFuncaoEmInimigoException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Fun��o inv�lida: %s � seu inimigo.";

    /**
     * Cria nova exce��o.
     *
     * @param inimigo o nome do inimigo.
     */
    public NaoPodeUsarFuncaoEmInimigoException(String inimigo) {
        super(String.format(MENSAGEM, inimigo));
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param inimigo o nome do inimigo.
     * @param cause a causa.
     */
    public NaoPodeUsarFuncaoEmInimigoException(String inimigo, Throwable cause) {
        super(String.format(MENSAGEM, inimigo), cause);
    }
}
