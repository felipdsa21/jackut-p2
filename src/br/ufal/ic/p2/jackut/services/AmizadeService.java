package br.ufal.ic.p2.jackut.services;

import java.util.Collection;
import java.util.function.Supplier;

import br.ufal.ic.p2.jackut.entities.Dados;
import br.ufal.ic.p2.jackut.exceptions.amizade.ConviteAindaNaoAceitoException;
import br.ufal.ic.p2.jackut.exceptions.amizade.JaEhAmigoException;
import br.ufal.ic.p2.jackut.exceptions.amizade.NaoPodeSeAdicionarComoAmigoException;
import br.ufal.ic.p2.jackut.exceptions.usuario.UsuarioNaoCadastradoException;
import br.ufal.ic.p2.jackut.utils.MiscUtils;

/**
 * Servi�o implementando amizades entre usu�rios.
 */
public final class AmizadeService {
    /**
     * O banco de dados.
     */
    private final Supplier<Dados> dados;

    /**
     * Cria nova inst�ncia do servi�o.
     *
     * @param dados o banco de dados.
     */
    public AmizadeService(Supplier<Dados> dados) {
        this.dados = dados;
    }

    /**
     * Envia um convite de amizade a outro usu�rio ou aceita um convite j� enviado por ele.
     *
     * @param sessao um id de sess�o.
     * @param amigo o login do novo amigo.
     * @throws UsuarioNaoCadastradoException se a sess�o for inv�lida ou o amigo n�o existir.
     * @throws NaoPodeSeAdicionarComoAmigoException se o usu�rio tentar adicionar a si mesmo.
     * @throws JaEhAmigoException se j� forem amigos.
     * @throws ConviteAindaNaoAceitoException se um convite j� houver sido enviado.
     */
    public void adicionarAmigo(String sessao, String amigo) throws
        UsuarioNaoCadastradoException,
        NaoPodeSeAdicionarComoAmigoException,
        JaEhAmigoException,
        ConviteAindaNaoAceitoException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuarioPorSessao(sessao);

        var login = usuario.getLogin();
        if (login.equals(amigo)) {
            throw new NaoPodeSeAdicionarComoAmigoException();
        }

        var usuarioAmigo = dados.encontrarUsuario(amigo);

        if (usuario.getAmigos().contains(amigo)) {
            // J� � amigo do outro usu�rio
            throw new JaEhAmigoException();
        } else if (usuario.getConvitesAmizade().contains(amigo)) {
            // O outro usu�rio j� enviou convite de amizade
            usuario.getConvitesAmizade().remove(amigo);
            usuario.getAmigos().add(amigo);
            usuarioAmigo.getAmigos().add(login);
        } else if (usuarioAmigo.getConvitesAmizade().contains(login)) {
            // Convite de amizade j� enviado ao outro usu�rio, deve esperar resposta
            throw new ConviteAindaNaoAceitoException();
        } else {
            // Enviando convite de amizade ao outro usu�rio
            usuarioAmigo.getConvitesAmizade().add(login);
        }
    }

    /**
     * Retorna se os dois usu�rios s�o amigos.
     *
     * @param login o login do usu�rio.
     * @param amigo o login do poss�vel amigo.
     * @return true se forem amigos, false caso contr�rio.
     * @throws UsuarioNaoCadastradoException se o usu�rio n�o existir.
     */
    public boolean ehAmigo(String login, String amigo) throws UsuarioNaoCadastradoException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuario(login);
        return usuario.getAmigos().contains(amigo);
    }

    /**
     * Retorna a conjunto de amigos de um usu�rio.
     *
     * @param login o login do usu�rio.
     * @return a conjunto de amigos como uma string.
     * @throws UsuarioNaoCadastradoException se o usu�rio n�o existir.
     * @see MiscUtils#collectionToString(Collection)
     */
    public String getAmigos(String login) throws UsuarioNaoCadastradoException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuario(login);
        return MiscUtils.collectionToString(usuario.getAmigos());
    }
}
