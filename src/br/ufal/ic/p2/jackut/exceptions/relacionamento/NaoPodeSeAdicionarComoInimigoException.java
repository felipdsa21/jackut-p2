package br.ufal.ic.p2.jackut.exceptions.relacionamento;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exceção lançada quando o usuário tenta se adicionar como inimigo.
 */
public final class NaoPodeSeAdicionarComoInimigoException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Usuário não pode ser inimigo de si mesmo.";

    /**
     * Cria nova exceção.
     */
    public NaoPodeSeAdicionarComoInimigoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public NaoPodeSeAdicionarComoInimigoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
