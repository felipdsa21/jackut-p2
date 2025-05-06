package br.ufal.ic.p2.jackut.exceptions.relacionamento;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exceção lançada quando o usuário tenta se adicionar como ídolo.
 */
public final class NaoPodeSeAdicionarComoIdoloException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Usuário não pode ser fã de si mesmo.";

    /**
     * Cria nova exceção.
     */
    public NaoPodeSeAdicionarComoIdoloException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public NaoPodeSeAdicionarComoIdoloException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
