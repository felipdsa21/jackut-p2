package br.ufal.ic.p2.jackut.exceptions;

/**
 * Exceção lançada quando o usuário tenta ler um recado sem ter recados não lidos.
 */
public final class NaoHaRecadosException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Não há recados.";

    /**
     * Cria nova exceção.
     */
    public NaoHaRecadosException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public NaoHaRecadosException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
