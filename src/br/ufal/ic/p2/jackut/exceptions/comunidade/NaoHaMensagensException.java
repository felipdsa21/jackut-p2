package br.ufal.ic.p2.jackut.exceptions.comunidade;

import br.ufal.ic.p2.jackut.exceptions.JackutException;

/**
 * Exceção lançada quando o usuário tenta ler uma mensagem sem ter mensagem não lidas.
 */
public final class NaoHaMensagensException extends JackutException {
    /**
     * Mensagem da exceção.
     */
    private static final String MENSAGEM = "Não há mensagens.";

    /**
     * Cria nova exceção.
     */
    public NaoHaMensagensException() {
        super(MENSAGEM);
    }

    /**
     * Cria nova exceção com a causa especificada.
     *
     * @param cause a causa.
     */
    public NaoHaMensagensException(Throwable cause) {
        super(MENSAGEM, cause);
    }
}
