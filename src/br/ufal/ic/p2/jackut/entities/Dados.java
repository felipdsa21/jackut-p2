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
     * As comunidades criadas.
     * <br>Mapeia um nome para a comunidade correspondente.
     */
    private final HashMap<String, Comunidade> comunidades;

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
        this.comunidades = new HashMap<>();
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
     * Remove o usu�rio que tenha o login especificado.
     *
     * @param login o login.
     * @throws UsuarioNaoCadastradoException se n�o houver um usu�rio com esse login.
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
     * @throws ComunidadeJaExisteException se uma comunidade com o mesmo nome j� existir.
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
     * @throws ComunidadeNaoCriadaException se n�o houver uma comunidade com esse nome.
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
     * Retorna o mapa das sess�es abertas para os usu�rios que as abriram.
     *
     * @return o mapa.
     */
    public Map<String, String> getSessoes() {
        return this.sessoes;
    }
}
