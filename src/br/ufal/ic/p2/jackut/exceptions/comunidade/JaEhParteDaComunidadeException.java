package br.ufal.ic.p2.jackut.exceptions.comunidade;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exceção lançada quando o usuário tenta entrar em uma comunidade da qual ele já faz parte.
 */
public final class JaEhParteDaComunidadeException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Usuario já faz parte dessa comunidade.";

    /**
     * Cria nova exceção.
     */
    public JaEhParteDaComunidadeException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public JaEhParteDaComunidadeException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
