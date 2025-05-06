package br.ufal.ic.p2.jackut.exceptions.usuario;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exceção lançada quando o atributo requisitado não estiver preenchido.
 */
public final class AtributoNaoPreenchidoException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Atributo não preenchido.";

    /**
     * Cria nova exceção.
     */
    public AtributoNaoPreenchidoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public AtributoNaoPreenchidoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
