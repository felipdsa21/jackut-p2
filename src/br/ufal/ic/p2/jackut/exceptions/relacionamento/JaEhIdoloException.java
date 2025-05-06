package br.ufal.ic.p2.jackut.exceptions.relacionamento;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exceção lançada quando um usuário tenta adicionar outro como idolo novamente.
 */
public final class JaEhIdoloException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Usuário já está adicionado como ídolo.";

    /**
     * Cria nova exceção.
     */
    public JaEhIdoloException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public JaEhIdoloException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
