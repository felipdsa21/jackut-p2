package br.ufal.ic.p2.jackut.entities;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Classe que armazena os dados de uma comunidade.
 */
public final class Comunidade implements Serializable {
    /**
     * O nome da comunidade.
     */
    private final String nome;

    /**
     * A descrição da comunidade.
     */
    private final String descricao;

    /**
     * O login do dono da comunidade.
     */
    private final String dono;

    /**
     * O conjunto de membros da comunidade.
     */
    private final LinkedHashSet<String> membros;

    /**
     * Cria um nova comunidade com os dados especificados.
     *
     * @param nome o nome.
     * @param descricao a descrição.
     * @param dono o login do dono.
     */
    public Comunidade(String nome, String descricao, String dono) {
        this.nome = nome;
        this.descricao = descricao;
        this.dono = dono;
        this.membros = new LinkedHashSet<>();
        this.membros.add(dono);
    }

    /**
     * Retorna o nome da comunidade.
     *
     * @return o nome.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Retorna a descrição da comunidade.
     *
     * @return a descrição.
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     * Retorna o login do dono da comunidade.
     *
     * @return o login.
     */
    public String getDono() {
        return this.dono;
    }

    /**
     * Retorna o conjunto de membros da comunidade.
     *
     * @return o conjunto.
     */
    public Set<String> getMembros() {
        return this.membros;
    }
}
