package br.ufal.ic.p2.jackut.utils;

import br.ufal.ic.p2.jackut.entities.Usuario;
import br.ufal.ic.p2.jackut.exceptions.relacionamento.NaoPodeUsarFuncaoEmInimigoException;

/**
 * Classe para compartilhar fun��es �teis entre servi�os.
 */
public final class ServiceUtils {
    private ServiceUtils() {
    }

    /**
     * Lan�a uma exce��o se o usu�rio for inimigo do outro.
     *
     * @param usuario o usu�rio
     * @param outro o poss�vel inimigo.
     * @throws NaoPodeUsarFuncaoEmInimigoException quando outro � inimigo do usuario.
     */
    public static void checarSeEhInimigo(Usuario usuario, Usuario outro) throws NaoPodeUsarFuncaoEmInimigoException {
        if (usuario.getInimigos().contains(outro.getLogin())) {
            throw new NaoPodeUsarFuncaoEmInimigoException(outro.getAtributos().get("nome"));
        }
    }
}
