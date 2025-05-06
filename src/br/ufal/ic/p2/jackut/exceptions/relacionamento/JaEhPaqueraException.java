package br.ufal.ic.p2.jackut.exceptions.relacionamento;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exceção lançada quando um usuário tenta adicionar outro como paquera novamente.
 */
public final class JaEhPaqueraException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Usuário já está adicionado como paquera.";

    /**
     * Cria nova exceção.
     */
    public JaEhPaqueraException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public JaEhPaqueraException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
