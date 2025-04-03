package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando o usu�rio tenta enviar um convite para si mesmo.
 */
public final class NaoPodeSeAdicionarComoAmigoException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Usu�rio n�o pode adicionar a si mesmo como amigo.";

    /**
     * Cria nova exce��o.
     */
    public NaoPodeSeAdicionarComoAmigoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public NaoPodeSeAdicionarComoAmigoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
