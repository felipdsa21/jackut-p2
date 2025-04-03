package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando o usu�rio especificado n�o p�de ser encontrado.
 */
public final class UsuarioNaoCadastradoException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Usu�rio n�o cadastrado.";

    /**
     * Cria nova exce��o.
     */
    public UsuarioNaoCadastradoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public UsuarioNaoCadastradoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
