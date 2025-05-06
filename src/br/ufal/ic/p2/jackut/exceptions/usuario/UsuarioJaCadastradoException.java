package br.ufal.ic.p2.jackut.exceptions.usuario;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exce��o lan�ada ao tentar cadastrar um usu�rio com um login j� usado por outro.
 */
public final class UsuarioJaCadastradoException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Conta com esse nome j� existe.";

    /**
     * Cria nova exce��o.
     */
    public UsuarioJaCadastradoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public UsuarioJaCadastradoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
