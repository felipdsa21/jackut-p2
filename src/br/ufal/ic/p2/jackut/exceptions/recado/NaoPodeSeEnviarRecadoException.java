package br.ufal.ic.p2.jackut.exceptions.recado;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exce��o lan�ada quando o usu�rio tenta enviar recado para si mesmo.
 */
public final class NaoPodeSeEnviarRecadoException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Usu�rio n�o pode enviar recado para si mesmo.";

    /**
     * Cria nova exce��o.
     */
    public NaoPodeSeEnviarRecadoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public NaoPodeSeEnviarRecadoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
