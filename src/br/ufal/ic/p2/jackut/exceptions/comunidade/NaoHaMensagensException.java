package br.ufal.ic.p2.jackut.exceptions.comunidade;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exce��o lan�ada quando o usu�rio tenta ler uma mensagem sem ter mensagem n�o lidas.
 */
public final class NaoHaMensagensException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "N�o h� mensagens.";

    /**
     * Cria nova exce��o.
     */
    public NaoHaMensagensException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public NaoHaMensagensException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
