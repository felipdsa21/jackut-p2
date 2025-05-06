package br.ufal.ic.p2.jackut.exceptions.usuario;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exceção lançada ao tentar cadastrar um usuário com um login já usado por outro.
 */
public final class UsuarioJaCadastradoException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Conta com esse nome já existe.";

    /**
     * Cria nova exceção.
     */
    public UsuarioJaCadastradoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public UsuarioJaCadastradoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
