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
 * Servi�o implementando relacionamentos (amigo, f�, �dolo, paquera, inimigo) entre usu�rios.
 */
public final class RelacionamentoService {
    /**
     * O banco de dados.
     */
    private final Supplier<Dados> dados;

    /**
     * Cria nova inst�ncia do servi�o.
     *
     * @param dados o banco de dados.
     */
    public RelacionamentoService(Supplier<Dados> dados) {
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
     * @throws NaoPodeUsarFuncaoEmInimigoException se o outro usu�rio for inimigo.
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

    /**
     * Retorna se um usu�rio � f� de outro.
     *
     * @param login o login do usu�rio.
     * @param idolo o login do poss�vel �dolo.
     * @return true se o usu�rio for f� de �dolo, false caso contr�rio.
     * @throws UsuarioNaoCadastradoException se o usu�rio n�o existir.
     */
    public boolean ehFa(String login, String idolo) throws UsuarioNaoCadastradoException {
        var dados = this.dados.get();
        dados.encontrarUsuario(login);
        var usuarioIdolo = dados.encontrarUsuario(idolo);
        return usuarioIdolo.getFas().contains(login);
    }

    /**
     * Adiciona um usu�rio como �dolo de outro.
     *
     * @param sessao um id de sess�o.
     * @param idolo o login do novo �dolo.
     * @throws UsuarioNaoCadastradoException se a sess�o for inv�lida ou o �dolo n�o existir.
     * @throws NaoPodeSeAdicionarComoIdoloException se o usu�rio tentar adicionar a si mesmo.
     * @throws NaoPodeUsarFuncaoEmInimigoException se o outro usu�rio for inimigo.
     * @throws JaEhIdoloException se o outro usu�rio j� for �dolo.
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
     * Retorna a conjunto de f�s de um usu�rio.
     *
     * @param login o login do usu�rio.
     * @return a conjunto de f�s como uma string.
     * @throws UsuarioNaoCadastradoException se o usu�rio n�o existir.
     * @see MiscUtils#collectionToString(Collection)
     */
    public String getFas(String login) throws UsuarioNaoCadastradoException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuario(login);
        return MiscUtils.collectionToString(usuario.getFas());
    }

    /**
     * Retorna se um usu�rio � paquera de outro.
     *
     * @param sessao um id de sess�o.
     * @param paquera o login do poss�vel paquera.
     * @return true se o usu�rio for paquera do outro, false caso contr�rio.
     * @throws UsuarioNaoCadastradoException se a sess�o for inv�lida ou o paquera n�o existir.
     */
    public boolean ehPaquera(String sessao, String paquera) throws UsuarioNaoCadastradoException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuarioPorSessao(sessao);
        return usuario.getPaqueras().contains(paquera);
    }

    /**
     * Adiciona um usu�rio como paquera de outro.
     *
     * @param sessao um id de sess�o.
     * @param paquera o login do novo paquera.
     * @throws UsuarioNaoCadastradoException se a sess�o for inv�lida ou o paquera n�o existir.
     * @throws NaoPodeSeAdicionarComoPaqueraException se o usu�rio tentar adicionar a si mesmo.
     * @throws NaoPodeUsarFuncaoEmInimigoException se o outro usu�rio for inimigo.
     * @throws JaEhPaqueraException se o outro usu�rio j� for paquera.
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
            var msg1 = usuarioPaquera.getAtributos().get("nome") + " � seu paquera - Recado do Jackut.";
            var msg2 = usuario.getAtributos().get("nome") + " � seu paquera - Recado do Jackut.";
            usuario.getRecados().add(new Mensagem(null, msg1));
            usuarioPaquera.getRecados().add(new Mensagem(null, msg2));
        }
    }

    /**
     * Retorna a conjunto de paqueras de um usu�rio.
     *
     * @param sessao um id de sess�o.
     * @return a conjunto de paqueras como uma string.
     * @throws UsuarioNaoCadastradoException se a sess�o for inv�lida.
     * @see MiscUtils#collectionToString(Collection)
     */
    public String getPaqueras(String sessao) throws UsuarioNaoCadastradoException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuarioPorSessao(sessao);
        return MiscUtils.collectionToString(usuario.getPaqueras());
    }

    /**
     * Adiciona um usu�rio como inimigo de outro.
     *
     * @param sessao um id de sess�o.
     * @param inimigo o login do novo inimigo.
     * @throws UsuarioNaoCadastradoException se a sess�o for inv�lida ou o �dolo n�o existir.
     * @throws NaoPodeSeAdicionarComoInimigoException se o usu�rio tentar adicionar a si mesmo.
     * @throws JaEhInimigoException se o outro usu�rio j� for inimigo.
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
