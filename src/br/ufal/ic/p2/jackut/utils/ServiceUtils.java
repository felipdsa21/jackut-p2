package br.ufal.ic.p2.jackut.utils;

import br.ufal.ic.p2.jackut.entities.Usuario;
import br.ufal.ic.p2.jackut.exceptions.relacionamento.NaoPodeUsarFuncaoEmInimigoException;

/**
 * Classe para compartilhar funções úteis entre serviços.
 */
public final class ServiceUtils {
    private ServiceUtils() {
    }

    /**
     * Lança uma exceção se o usuário for inimigo do outro.
     *
     * @param usuario o usuário
     * @param outro o possível inimigo.
     * @throws NaoPodeUsarFuncaoEmInimigoException quando outro é inimigo do usuario.
     */
    public static void checarSeEhInimigo(Usuario usuario, Usuario outro) throws NaoPodeUsarFuncaoEmInimigoException {
        if (usuario.getInimigos().contains(outro.getLogin())) {
            throw new NaoPodeUsarFuncaoEmInimigoException(outro.getAtributos().get("nome"));
        }
    }
}
