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
 * Serviço implementando amizades entre usuários.
 */
public final class AmizadeService {
    /**
     * O banco de dados.
     */
    private final Supplier<Dados> dados;

    /**
     * Cria nova instância do serviço.
     *
     * @param dados o banco de dados.
     */
    public AmizadeService(Supplier<Dados> dados) {
        this.dados = dados;
    }

    /**
     * Envia um convite de amizade a outro usuário ou aceita um convite já enviado por ele.
     *
     * @param sessao um id de sessão.
     * @param amigo o login do novo amigo.
     * @throws UsuarioNaoCadastradoException se a sessão for inválida ou o amigo não existir.
     * @throws NaoPodeSeAdicionarComoAmigoException se o usuário tentar adicionar a si mesmo.
     * @throws JaEhAmigoException se já forem amigos.
     * @throws ConviteAindaNaoAceitoException se um convite já houver sido enviado.
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
            // Já é amigo do outro usuário
            throw new JaEhAmigoException();
        } else if (usuario.getConvitesAmizade().contains(amigo)) {
            // O outro usuário já enviou convite de amizade
            usuario.getConvitesAmizade().remove(amigo);
            usuario.getAmigos().add(amigo);
            usuarioAmigo.getAmigos().add(login);
        } else if (usuarioAmigo.getConvitesAmizade().contains(login)) {
            // Convite de amizade já enviado ao outro usuário, deve esperar resposta
            throw new ConviteAindaNaoAceitoException();
        } else {
            // Enviando convite de amizade ao outro usuário
            usuarioAmigo.getConvitesAmizade().add(login);
        }
    }

    /**
     * Retorna se os dois usuários são amigos.
     *
     * @param login o login do usuário.
     * @param amigo o login do possível amigo.
     * @return true se forem amigos, false caso contrário.
     * @throws UsuarioNaoCadastradoException se o usuário não existir.
     */
    public boolean ehAmigo(String login, String amigo) throws UsuarioNaoCadastradoException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuario(login);
        return usuario.getAmigos().contains(amigo);
    }

    /**
     * Retorna a conjunto de amigos de um usuário.
     *
     * @param login o login do usuário.
     * @return a conjunto de amigos como uma string.
     * @throws UsuarioNaoCadastradoException se o usuário não existir.
     * @see MiscUtils#collectionToString(Collection)
     */
    public String getAmigos(String login) throws UsuarioNaoCadastradoException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuario(login);
        return MiscUtils.collectionToString(usuario.getAmigos());
    }
}
