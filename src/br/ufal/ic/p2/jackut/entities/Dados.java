package br.ufal.ic.p2.jackut.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import br.ufal.ic.p2.jackut.exceptions.usuario.UsuarioJaCadastradoException;
import br.ufal.ic.p2.jackut.exceptions.usuario.UsuarioNaoCadastradoException;
import br.ufal.ic.p2.jackut.utils.MiscUtils;

/**
 * Classe que contém todos os dados armazenados pelo sistema.
 * <br>Funciona como um banco de dados.
 */
public final class Dados implements Serializable {
    /**
     * Os usuários cadastrados.
     * <br>Mapeia um login para o usuário que tem esse login.
     */
    private final HashMap<String, Usuario> usuarios;

    /**
     * As sessões abertas.
     * <br>Mapeia um id de sessão para o login do usuário que abriu essa sessão.
     */
    private final HashMap<String, String> sessoes;

    /**
     * Cria banco de dados vazio.
     */
    public Dados() {
        this.usuarios = new HashMap<>();
        this.sessoes = new HashMap<>();
    }

    /**
     * Adiciona um novo usuário.
     *
     * @param usuario o usuário.
     * @throws UsuarioJaCadastradoException se um usuário com o mesmo login já existir.
     */
    public void adicionarUsuario(Usuario usuario) throws UsuarioJaCadastradoException {
        if (this.usuarios.containsKey(usuario.getLogin())) {
            throw new UsuarioJaCadastradoException();
        } else {
            this.usuarios.put(usuario.getLogin(), usuario);
        }
    }

    /**
     * Encontra o usuário que tenha o login especificado.
     *
     * @param login o login.
     * @return o usuário.
     * @throws UsuarioNaoCadastradoException se não houver um usuário com esse login.
     */
    public Usuario encontrarUsuario(String login) throws UsuarioNaoCadastradoException {
        return MiscUtils.requireNonNull(this.usuarios.get(login), UsuarioNaoCadastradoException::new);
    }

    /**
     * Encontra o usuário que abriu a sessão especificada.
     *
     * @param sessao um id de sessão.
     * @return o usuário.
     * @throws UsuarioNaoCadastradoException se não houver uma sessão com esse id.
     */
    public Usuario encontrarUsuarioPorSessao(String sessao) throws UsuarioNaoCadastradoException {
        return this.encontrarUsuario(this.sessoes.get(sessao));
    }

    /**
     * Retorna o mapa das sessões abertas para os usuários que as abriram.
     *
     * @return o mapa.
     */
    public Map<String, String> getSessoes() {
        return this.sessoes;
    }
}
