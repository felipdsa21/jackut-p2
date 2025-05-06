package br.ufal.ic.p2.jackut.exceptions.relacionamento;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exce��o lan�ada quando um usu�rio tenta enviar um convite repetidamente.
 */
public final class ConviteAindaNaoAceitoException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Usu�rio j� est� adicionado como amigo, esperando aceita��o do convite.";

    /**
     * Cria nova exce��o.
     */
    public ConviteAindaNaoAceitoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public ConviteAindaNaoAceitoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
