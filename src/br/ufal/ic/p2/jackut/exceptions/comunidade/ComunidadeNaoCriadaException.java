package br.ufal.ic.p2.jackut.exceptions.comunidade;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exce��o lan�ada quando n�o existe uma comunidade com o nome especificado.
 */
public final class ComunidadeNaoCriadaException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Comunidade n�o existe.";

    /**
     * Cria nova exce��o.
     */
    public ComunidadeNaoCriadaException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public ComunidadeNaoCriadaException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
