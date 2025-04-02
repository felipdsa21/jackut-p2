package br.ufal.ic.p2.jackut.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.ufal.ic.p2.jackut.exceptions.LoginInvalidoException;
import br.ufal.ic.p2.jackut.exceptions.SenhaInvalidaException;
import br.ufal.ic.p2.jackut.utils.MiscUtils;

/**
 * Classe que armazena os dados de um usuário específico.
 */
public final class Usuario implements Serializable {
    /**
     * O login do usuário.
     */
    private final String login;

    /**
     * A senha do usuário.
     */
    private final String senha;

    /**
     * Os atributos (como "descricao" ou "idiomas") e seus valores do usuário.
     */
    private final HashMap<String, String> atributos;

    /**
     * O conjunto de amigos do usuário.
     */
    private final LinkedHashSet<String> amigos;

    /**
     * O conjunto de usuários que enviaram convites de amizade ainda não aceitos.
     */
    private final LinkedHashSet<String> convitesAmizade;

    /**
     * Os recados enviados por outros usuários.
     */
    private final ArrayList<String> recados;

    /**
     * Cria novo usuário com os dados especificados.
     *
     * @param login o login.
     * @param senha a senha.
     * @param nome o valor do atributo "nome".
     * @throws LoginInvalidoException se o login for null ou uma string vazia.
     * @throws SenhaInvalidaException se a senha for null ou uma string vazia.
     */
    public Usuario(String login, String senha, String nome) throws LoginInvalidoException, SenhaInvalidaException {
        this.login = MiscUtils.requireNonEmptyString(login, LoginInvalidoException::new);
        this.senha = MiscUtils.requireNonEmptyString(senha, SenhaInvalidaException::new);
        this.atributos = new HashMap<>();
        this.atributos.put("nome", nome);
        this.amigos = new LinkedHashSet<>();
        this.convitesAmizade = new LinkedHashSet<>();
        this.recados = new ArrayList<>();
    }

    /**
     * Retorna o login do usuário.
     *
     * @return o login.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Retorna a senha do usuário.
     *
     * @return a senha.
     */
    public String getSenha() {
        return this.senha;
    }

    /**
     * Retorna os atributos do usuário.
     *
     * @return os atributos.
     */
    public Map<String, String> getAtributos() {
        return this.atributos;
    }

    /**
     * Retorna o conjunto de amigos do usuário.
     *
     * @return o conjunto.
     */
    public Set<String> getAmigos() {
        return this.amigos;
    }

    /**
     * Retorna o conjunto de usuários que enviaram convites de amizade.
     *
     * @return o conjunto.
     */
    public Set<String> getConvitesAmizade() {
        return this.convitesAmizade;
    }

    /**
     * Retorna os recados enviados ao usuário.
     *
     * @return os recados.
     */
    public List<String> getRecados() {
        return this.recados;
    }
}
