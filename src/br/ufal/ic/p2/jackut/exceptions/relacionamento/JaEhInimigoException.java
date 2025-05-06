package br.ufal.ic.p2.jackut.exceptions.relacionamento;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exceção lançada quando um usuário tenta adicionar outro como inimigo novamente.
 */
public final class JaEhInimigoException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Usuário já está adicionado como inimigo.";

    /**
     * Cria nova exceção.
     */
    public JaEhInimigoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public JaEhInimigoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
