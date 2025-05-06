package br.ufal.ic.p2.jackut.exceptions.relacionamento;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exceção lançada quando o usuário tenta se adicionar como paquera.
 */
public final class NaoPodeSeAdicionarComoPaqueraException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Usuário não pode ser paquera de si mesmo.";

    /**
     * Cria nova exceção.
     */
    public NaoPodeSeAdicionarComoPaqueraException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public NaoPodeSeAdicionarComoPaqueraException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
