package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário tenta enviar um convite para si mesmo.
 */
public final class NaoPodeSeAdicionarComoAmigoException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Usuário não pode adicionar a si mesmo como amigo.";

    /**
     * Cria nova exceção.
     */
    public NaoPodeSeAdicionarComoAmigoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public NaoPodeSeAdicionarComoAmigoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
