package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exce��o lan�ada quando o usu�rio tenta ler um recado sem ter recados n�o lidos.
 */
public final class NaoHaRecadosException extends JackutException {
    /**
     * Mensagem da exce��o.
     */
    private static final String MENSAGEM = "N�o h� recados.";

    /**
     * Cria nova exce��o.
     */
    public NaoHaRecadosException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exce��o com a causa especificada.
     *
     * @param cause a causa.
     */
    public NaoHaRecadosException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
