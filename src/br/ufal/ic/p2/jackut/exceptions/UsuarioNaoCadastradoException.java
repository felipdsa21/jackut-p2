package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário especificado não pôde ser encontrado.
 */
public final class UsuarioNaoCadastradoException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Usuário não cadastrado.";

    /**
     * Cria nova exceção.
     */
    public UsuarioNaoCadastradoException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public UsuarioNaoCadastradoException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
