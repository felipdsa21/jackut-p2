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
 * Classe que armazena os dados de um usu�rio espec�fico.
 */
public final class Usuario implements Serializable {
    /**
     * O login do usu�rio.
     */
    private final String login;

    /**
     * A senha do usu�rio.
     */
    private final String senha;

    /**
     * Os atributos (como "descricao" ou "idiomas") e seus valores do usu�rio.
     */
    private final HashMap<String, String> atributos;

    /**
     * O conjunto de amigos do usu�rio.
     */
    private final LinkedHashSet<String> amigos;

    /**
     * O conjunto de usu�rios que enviaram convites de amizade ainda n�o aceitos.
     */
    private final LinkedHashSet<String> convitesAmizade;

    /**
     * Os recados enviados por outros usu�rios.
     */
    private final ArrayList<String> recados;

    /**
     * Cria novo usu�rio com os dados especificados.
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
     * Retorna o login do usu�rio.
     *
     * @return o login.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Retorna a senha do usu�rio.
     *
     * @return a senha.
     */
    public String getSenha() {
        return this.senha;
    }

    /**
     * Retorna os atributos do usu�rio.
     *
     * @return os atributos.
     */
    public Map<String, String> getAtributos() {
        return this.atributos;
    }

    /**
     * Retorna o conjunto de amigos do usu�rio.
     *
     * @return o conjunto.
     */
    public Set<String> getAmigos() {
        return this.amigos;
    }

    /**
     * Retorna o conjunto de usu�rios que enviaram convites de amizade.
     *
     * @return o conjunto.
     */
    public Set<String> getConvitesAmizade() {
        return this.convitesAmizade;
    }

    /**
     * Retorna os recados enviados ao usu�rio.
     *
     * @return os recados.
     */
    public List<String> getRecados() {
        return this.recados;
    }
}
