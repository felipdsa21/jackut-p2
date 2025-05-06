package br.ufal.ic.p2.jackut.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import br.ufal.ic.p2.jackut.exceptions.usuario.UsuarioJaCadastradoException;
import br.ufal.ic.p2.jackut.exceptions.usuario.UsuarioNaoCadastradoException;
import br.ufal.ic.p2.jackut.utils.MiscUtils;

/**
 * Classe que cont�m todos os dados armazenados pelo sistema.
 * <br>Funciona como um banco de dados.
 */
public final class Dados implements Serializable {
    /**
     * Os usu�rios cadastrados.
     * <br>Mapeia um login para o usu�rio que tem esse login.
     */
    private final HashMap<String, Usuario> usuarios;

    /**
     * As sess�es abertas.
     * <br>Mapeia um id de sess�o para o login do usu�rio que abriu essa sess�o.
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
     * Adiciona um novo usu�rio.
     *
     * @param usuario o usu�rio.
     * @throws UsuarioJaCadastradoException se um usu�rio com o mesmo login j� existir.
     */
    public void adicionarUsuario(Usuario usuario) throws UsuarioJaCadastradoException {
        if (this.usuarios.containsKey(usuario.getLogin())) {
            throw new UsuarioJaCadastradoException();
        } else {
            this.usuarios.put(usuario.getLogin(), usuario);
        }
    }

    /**
     * Encontra o usu�rio que tenha o login especificado.
     *
     * @param login o login.
     * @return o usu�rio.
     * @throws UsuarioNaoCadastradoException se n�o houver um usu�rio com esse login.
     */
    public Usuario encontrarUsuario(String login) throws UsuarioNaoCadastradoException {
        return MiscUtils.requireNonNull(this.usuarios.get(login), UsuarioNaoCadastradoException::new);
    }

    /**
     * Encontra o usu�rio que abriu a sess�o especificada.
     *
     * @param sessao um id de sess�o.
     * @return o usu�rio.
     * @throws UsuarioNaoCadastradoException se n�o houver uma sess�o com esse id.
     */
    public Usuario encontrarUsuarioPorSessao(String sessao) throws UsuarioNaoCadastradoException {
        return this.encontrarUsuario(this.sessoes.get(sessao));
    }

    /**
     * Retorna o mapa das sess�es abertas para os usu�rios que as abriram.
     *
     * @return o mapa.
     */
    public Map<String, String> getSessoes() {
        return this.sessoes;
    }
}
