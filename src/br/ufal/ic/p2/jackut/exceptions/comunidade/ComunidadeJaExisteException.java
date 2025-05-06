package br.ufal.ic.p2.jackut.exceptions.comunidade;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exceção lançada quando já existe uma comunidade com o nome escolhido.
 */
public final class ComunidadeJaExisteException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Comunidade com esse nome já existe.";

    /**
     * Cria nova exceção.
     */
    public ComunidadeJaExisteException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public ComunidadeJaExisteException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
