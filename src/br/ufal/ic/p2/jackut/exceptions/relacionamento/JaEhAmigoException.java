package br.ufal.ic.p2.jackut.exceptions.relacionamento;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exceção lançada quando um usuário tenta enviar um convite para outro que já seja amigo.
 */
public final class JaEhAmigoException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Usuário já está adicionado como amigo.";

    /**
     * Cria nova exceção.
     */
    public JaEhAmigoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public JaEhAmigoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
