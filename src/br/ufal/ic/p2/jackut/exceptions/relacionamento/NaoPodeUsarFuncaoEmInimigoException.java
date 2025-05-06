package br.ufal.ic.p2.jackut.exceptions.relacionamento;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exceção lançada quando um usuário tenta usar uma função que é inválida quando chamada com um inimigo.
 */
public final class NaoPodeUsarFuncaoEmInimigoException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Função inválida: %s é seu inimigo.";

    /**
     * Cria nova exceção.
     *
     * @param inimigo o nome do inimigo.
     */
    public NaoPodeUsarFuncaoEmInimigoException(String inimigo) {
        super(String.format(MENSAGEM, inimigo));
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param inimigo o nome do inimigo.
     * @param cause a causa.
     */
    public NaoPodeUsarFuncaoEmInimigoException(String inimigo, Throwable cause) {
        super(String.format(MENSAGEM, inimigo), cause);
    }
}
