package br.ufal.ic.p2.jackut.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import br.ufal.ic.p2.jackut.exceptions.comunidade.ComunidadeJaExisteException;
import br.ufal.ic.p2.jackut.exceptions.comunidade.ComunidadeNaoCriadaException;
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
     * As comunidades criadas.
     * <br>Mapeia um nome para a comunidade correspondente.
     */
    private final HashMap<String, Comunidade> comunidades;

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
        this.comunidades = new HashMap<>();
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
     * Remove o usuário que tenha o login especificado.
     *
     * @param login o login.
     * @throws UsuarioNaoCadastradoException se não houver um usuário com esse login.
     */
    public void removerUsuario(String login) throws UsuarioNaoCadastradoException {
        this.encontrarUsuario(login);
        this.sessoes.entrySet().removeIf(entry -> login.equals(entry.getValue()));

        for (var comunidade : this.comunidades.values()) {
            if (login.equals(comunidade.getDono())) {
                this.removerComunidade(comunidade.getNome());
            }
        }

        for (var outro : this.usuarios.values()) {
            outro.getConvitesAmizade().remove(login);
            outro.getAmigos().remove(login);
            outro.getFas().remove(login);
            outro.getPaqueras().remove(login);
            outro.getInimigos().remove(login);
            outro.getRecados().removeIf(recado -> login.equals(recado.remetente()));
            outro.getMensagens().removeIf(mensagem -> login.equals(mensagem.remetente()));
        }

        this.usuarios.remove(login);
    }

    /**
     * Adiciona uma nova comunidade.
     *
     * @param comunidade a comunidade.
     * @throws ComunidadeJaExisteException se uma comunidade com o mesmo nome já existir.
     */
    public void adicionarComunidade(Comunidade comunidade) throws ComunidadeJaExisteException {
        if (this.comunidades.containsKey(comunidade.getNome())) {
            throw new ComunidadeJaExisteException();
        } else {
            this.comunidades.put(comunidade.getNome(), comunidade);
        }
    }

    /**
     * Encontra a comunidade que tenha o nome especificado.
     *
     * @param nome o nome.
     * @return a comunidade.
     * @throws ComunidadeNaoCriadaException se não houver uma comunidade com esse nome.
     */
    public Comunidade encontrarComunidade(String nome) throws ComunidadeNaoCriadaException {
        return MiscUtils.requireNonNull(this.comunidades.get(nome), ComunidadeNaoCriadaException::new);
    }

    /**
     * Remove a comunidade que tenha o nome especificado.
     *
     * @param nome o nome.
     */
    private void removerComunidade(String nome) {
        var comunidade = this.comunidades.remove(nome);
        for (var login : comunidade.getMembros()) {
            this.usuarios.get(login).getComunidades().remove(nome);
        }
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
