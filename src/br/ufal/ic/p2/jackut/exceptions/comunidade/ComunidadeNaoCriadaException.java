package br.ufal.ic.p2.jackut.exceptions.comunidade;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exceção lançada quando não existe uma comunidade com o nome especificado.
 */
public final class ComunidadeNaoCriadaException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Comunidade não existe.";

    /**
     * Cria nova exceção.
     */
    public ComunidadeNaoCriadaException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public ComunidadeNaoCriadaException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
