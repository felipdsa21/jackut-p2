package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando um usuário tenta enviar um convite repetidamente.
 */
public final class ConviteAindaNaoAceitoException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Usuário já está adicionado como amigo, esperando aceitação do convite.";

    /**
     * Cria nova exceção.
     */
    public ConviteAindaNaoAceitoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public ConviteAindaNaoAceitoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
