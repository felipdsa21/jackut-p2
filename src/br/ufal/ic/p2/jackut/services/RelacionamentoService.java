package br.ufal.ic.p2.jackut.services;

import java.util.Collection;
import java.util.function.Supplier;

import br.ufal.ic.p2.jackut.entities.Dados;
import br.ufal.ic.p2.jackut.entities.Mensagem;
import br.ufal.ic.p2.jackut.exceptions.relacionamento.ConviteAindaNaoAceitoException;
import br.ufal.ic.p2.jackut.exceptions.relacionamento.JaEhAmigoException;
import br.ufal.ic.p2.jackut.exceptions.relacionamento.JaEhIdoloException;
import br.ufal.ic.p2.jackut.exceptions.relacionamento.JaEhInimigoException;
import br.ufal.ic.p2.jackut.exceptions.relacionamento.JaEhPaqueraException;
import br.ufal.ic.p2.jackut.exceptions.relacionamento.NaoPodeSeAdicionarComoAmigoException;
import br.ufal.ic.p2.jackut.exceptions.relacionamento.NaoPodeSeAdicionarComoIdoloException;
import br.ufal.ic.p2.jackut.exceptions.relacionamento.NaoPodeSeAdicionarComoInimigoException;
import br.ufal.ic.p2.jackut.exceptions.relacionamento.NaoPodeSeAdicionarComoPaqueraException;
import br.ufal.ic.p2.jackut.exceptions.relacionamento.NaoPodeUsarFuncaoEmInimigoException;
import br.ufal.ic.p2.jackut.exceptions.usuario.UsuarioNaoCadastradoException;
import br.ufal.ic.p2.jackut.utils.MiscUtils;
import br.ufal.ic.p2.jackut.utils.ServiceUtils;

/**
 * Serviço implementando relacionamentos (amigo, fã, ídolo, paquera, inimigo) entre usuários.
 */
public final class RelacionamentoService {
    /**
     * O banco de dados.
     */
    private final Supplier<Dados> dados;

    /**
     * Cria nova instância do serviço.
     *
     * @param dados o banco de dados.
     */
    public RelacionamentoService(Supplier<Dados> dados) {
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
     * @throws NaoPodeUsarFuncaoEmInimigoException se o outro usuário for inimigo.
     */
    public void adicionarAmigo(String sessao, String amigo) throws
        UsuarioNaoCadastradoException,
        NaoPodeSeAdicionarComoAmigoException,
        JaEhAmigoException,
        ConviteAindaNaoAceitoException,
        NaoPodeUsarFuncaoEmInimigoException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuarioPorSessao(sessao);

        var login = usuario.getLogin();
        if (login.equals(amigo)) {
            throw new NaoPodeSeAdicionarComoAmigoException();
        }

        var usuarioAmigo = dados.encontrarUsuario(amigo);
        ServiceUtils.checarSeEhInimigo(usuario, usuarioAmigo);

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

    /**
     * Retorna se um usuário é fã de outro.
     *
     * @param login o login do usuário.
     * @param idolo o login do possível ídolo.
     * @return true se o usuário for fã de ídolo, false caso contrário.
     * @throws UsuarioNaoCadastradoException se o usuário não existir.
     */
    public boolean ehFa(String login, String idolo) throws UsuarioNaoCadastradoException {
        var dados = this.dados.get();
        dados.encontrarUsuario(login);
        var usuarioIdolo = dados.encontrarUsuario(idolo);
        return usuarioIdolo.getFas().contains(login);
    }

    /**
     * Adiciona um usuário como ídolo de outro.
     *
     * @param sessao um id de sessão.
     * @param idolo o login do novo ídolo.
     * @throws UsuarioNaoCadastradoException se a sessão for inválida ou o ídolo não existir.
     * @throws NaoPodeSeAdicionarComoIdoloException se o usuário tentar adicionar a si mesmo.
     * @throws NaoPodeUsarFuncaoEmInimigoException se o outro usuário for inimigo.
     * @throws JaEhIdoloException se o outro usuário já for ídolo.
     */
    public void adicionarIdolo(String sessao, String idolo) throws
        UsuarioNaoCadastradoException,
        NaoPodeSeAdicionarComoIdoloException,
        NaoPodeUsarFuncaoEmInimigoException,
        JaEhIdoloException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuarioPorSessao(sessao);
        var login = usuario.getLogin();

        if (login.equals(idolo)) {
            throw new NaoPodeSeAdicionarComoIdoloException();
        }

        var usuarioIdolo = dados.encontrarUsuario(idolo);
        ServiceUtils.checarSeEhInimigo(usuario, usuarioIdolo);

        if (!usuarioIdolo.getFas().add(login)) {
            throw new JaEhIdoloException();
        }
    }

    /**
     * Retorna a conjunto de fãs de um usuário.
     *
     * @param login o login do usuário.
     * @return a conjunto de fãs como uma string.
     * @throws UsuarioNaoCadastradoException se o usuário não existir.
     * @see MiscUtils#collectionToString(Collection)
     */
    public String getFas(String login) throws UsuarioNaoCadastradoException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuario(login);
        return MiscUtils.collectionToString(usuario.getFas());
    }

    /**
     * Retorna se um usuário é paquera de outro.
     *
     * @param sessao um id de sessão.
     * @param paquera o login do possível paquera.
     * @return true se o usuário for paquera do outro, false caso contrário.
     * @throws UsuarioNaoCadastradoException se a sessão for inválida ou o paquera não existir.
     */
    public boolean ehPaquera(String sessao, String paquera) throws UsuarioNaoCadastradoException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuarioPorSessao(sessao);
        return usuario.getPaqueras().contains(paquera);
    }

    /**
     * Adiciona um usuário como paquera de outro.
     *
     * @param sessao um id de sessão.
     * @param paquera o login do novo paquera.
     * @throws UsuarioNaoCadastradoException se a sessão for inválida ou o paquera não existir.
     * @throws NaoPodeSeAdicionarComoPaqueraException se o usuário tentar adicionar a si mesmo.
     * @throws NaoPodeUsarFuncaoEmInimigoException se o outro usuário for inimigo.
     * @throws JaEhPaqueraException se o outro usuário já for paquera.
     */
    public void adicionarPaquera(String sessao, String paquera) throws
        UsuarioNaoCadastradoException,
        NaoPodeSeAdicionarComoPaqueraException,
        NaoPodeUsarFuncaoEmInimigoException,
        JaEhPaqueraException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuarioPorSessao(sessao);
        var login = usuario.getLogin();

        if (login.equals(paquera)) {
            throw new NaoPodeSeAdicionarComoPaqueraException();
        }

        var usuarioPaquera = dados.encontrarUsuario(paquera);
        ServiceUtils.checarSeEhInimigo(usuario, usuarioPaquera);

        if (!usuario.getPaqueras().add(paquera)) {
            throw new JaEhPaqueraException();
        } else if (usuarioPaquera.getPaqueras().contains(login)) {
            var msg1 = usuarioPaquera.getAtributos().get("nome") + " é seu paquera - Recado do Jackut.";
            var msg2 = usuario.getAtributos().get("nome") + " é seu paquera - Recado do Jackut.";
            usuario.getRecados().add(new Mensagem(null, msg1));
            usuarioPaquera.getRecados().add(new Mensagem(null, msg2));
        }
    }

    /**
     * Retorna a conjunto de paqueras de um usuário.
     *
     * @param sessao um id de sessão.
     * @return a conjunto de paqueras como uma string.
     * @throws UsuarioNaoCadastradoException se a sessão for inválida.
     * @see MiscUtils#collectionToString(Collection)
     */
    public String getPaqueras(String sessao) throws UsuarioNaoCadastradoException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuarioPorSessao(sessao);
        return MiscUtils.collectionToString(usuario.getPaqueras());
    }

    /**
     * Adiciona um usuário como inimigo de outro.
     *
     * @param sessao um id de sessão.
     * @param inimigo o login do novo inimigo.
     * @throws UsuarioNaoCadastradoException se a sessão for inválida ou o ídolo não existir.
     * @throws NaoPodeSeAdicionarComoInimigoException se o usuário tentar adicionar a si mesmo.
     * @throws JaEhInimigoException se o outro usuário já for inimigo.
     */
    public void adicionarInimigo(String sessao, String inimigo)
        throws UsuarioNaoCadastradoException, NaoPodeSeAdicionarComoInimigoException, JaEhInimigoException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuarioPorSessao(sessao);
        var login = usuario.getLogin();

        if (login.equals(inimigo)) {
            throw new NaoPodeSeAdicionarComoInimigoException();
        }

        var usuarioInimigo = dados.encontrarUsuario(inimigo);

        if (usuario.getInimigos().add(inimigo)) {
            usuarioInimigo.getInimigos().add(login);
        } else {
            throw new JaEhInimigoException();
        }
    }
}
