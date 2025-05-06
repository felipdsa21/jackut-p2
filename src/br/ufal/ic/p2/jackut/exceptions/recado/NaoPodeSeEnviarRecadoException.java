package br.ufal.ic.p2.jackut.exceptions.recado;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exceção lançada quando o usuário tenta enviar recado para si mesmo.
 */
public final class NaoPodeSeEnviarRecadoException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Usuário não pode enviar recado para si mesmo.";

    /**
     * Cria nova exceção.
     */
    public NaoPodeSeEnviarRecadoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public NaoPodeSeEnviarRecadoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
