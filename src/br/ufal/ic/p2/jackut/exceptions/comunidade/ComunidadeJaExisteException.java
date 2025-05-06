package br.ufal.ic.p2.jackut.exceptions.comunidade;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exce��o lan�ada quando j� existe uma comunidade com o nome escolhido.
 */
public final class ComunidadeJaExisteException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "Comunidade com esse nome j� existe.";

    /**
     * Cria nova exce��o.
     */
    public ComunidadeJaExisteException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public ComunidadeJaExisteException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
