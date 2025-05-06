package br.ufal.ic.p2.jackut.services;

import java.util.Collection;
import java.util.function.Supplier;

import br.ufal.ic.p2.jackut.entities.Comunidade;
import br.ufal.ic.p2.jackut.entities.Dados;
import br.ufal.ic.p2.jackut.entities.Mensagem;
import br.ufal.ic.p2.jackut.exceptions.comunidade.ComunidadeJaExisteException;
import br.ufal.ic.p2.jackut.exceptions.comunidade.ComunidadeNaoCriadaException;
import br.ufal.ic.p2.jackut.exceptions.comunidade.JaEhParteDaComunidadeException;
import br.ufal.ic.p2.jackut.exceptions.comunidade.NaoHaMensagensException;
import br.ufal.ic.p2.jackut.exceptions.usuario.UsuarioNaoCadastradoException;
import br.ufal.ic.p2.jackut.utils.MiscUtils;

/**
 * Serviço implementando comunidades.
 */
public final class ComunidadeService {
    /**
     * O banco de dados.
     */
    private final Supplier<Dados> dados;

    /**
     * Cria nova instância do serviço.
     *
     * @param dados o banco de dados.
     */
    public ComunidadeService(Supplier<Dados> dados) {
        this.dados = dados;
    }

    /**
     * Cria uma nova comunidade.
     *
     * @param sessao um id de sessão.
     * @param nome o nome da comunidade.
     * @param descricao a descrição da comunidade.
     * @throws UsuarioNaoCadastradoException se a sessão for inválida.
     * @throws ComunidadeJaExisteException se uma comunidade com o mesmo nome já existir.
     */
    public void criarComunidade(String sessao, String nome, String descricao)
        throws UsuarioNaoCadastradoException, ComunidadeJaExisteException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuarioPorSessao(sessao);
        var login = usuario.getLogin();

        var comunidade = new Comunidade(nome, descricao, login);
        dados.adicionarComunidade(comunidade);
        usuario.getComunidades().add(nome);
    }

    /**
     * Retorna a descrição da comunidade especificada.
     *
     * @param nome o nome da comunidade.
     * @return a descrição.
     * @throws ComunidadeNaoCriadaException se a comunidade não existir.
     */
    public String getDescricaoComunidade(String nome) throws ComunidadeNaoCriadaException {
        var dados = this.dados.get();
        return dados.encontrarComunidade(nome).getDescricao();
    }

    /**
     * Retorna o dono da comunidade especificada.
     *
     * @param nome o nome da comunidade.
     * @return o login do dono.
     * @throws ComunidadeNaoCriadaException se a comunidade não existir.
     */
    public String getDonoComunidade(String nome) throws ComunidadeNaoCriadaException {
        var dados = this.dados.get();
        return dados.encontrarComunidade(nome).getDono();
    }

    /**
     * Retorna a conjunto de membros de uma comunidade.
     *
     * @param nome o nome da comunidade.
     * @return a conjunto de membros como uma string.
     * @throws ComunidadeNaoCriadaException se a comunidade não existir.
     * @see MiscUtils#collectionToString(Collection)
     */
    public String getMembrosComunidade(String nome) throws ComunidadeNaoCriadaException {
        var dados = this.dados.get();
        return MiscUtils.collectionToString(dados.encontrarComunidade(nome).getMembros());
    }

    /**
     * Retorna a conjunto de comunidades das quais um usuário faz parte.
     *
     * @param login o login do usuário.
     * @return a conjunto de comunidades como uma string.
     * @throws UsuarioNaoCadastradoException se o usuário não existir.
     * @see MiscUtils#collectionToString(Collection)
     */
    public String getComunidades(String login) throws UsuarioNaoCadastradoException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuario(login);
        return MiscUtils.collectionToString(usuario.getComunidades());
    }

    /**
     * Adiciona um usuário aos membros da comunidade especificada.
     *
     * @param sessao um id de sessão.
     * @param nome o nome da comunidade.
     * @throws UsuarioNaoCadastradoException se a sessão for inválida.
     * @throws ComunidadeNaoCriadaException se a comunidade não existir.
     * @throws JaEhParteDaComunidadeException se o usuário já for membro da comunidade.
     */
    public void adicionarComunidade(String sessao, String nome)
        throws UsuarioNaoCadastradoException, ComunidadeNaoCriadaException, JaEhParteDaComunidadeException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuarioPorSessao(sessao);
        var login = usuario.getLogin();

        if (dados.encontrarComunidade(nome).getMembros().add(login)) {
            usuario.getComunidades().add(nome);
        } else {
            throw new JaEhParteDaComunidadeException();
        }
    }

    /**
     * Retorna a primeira mensagem da fila de mensagens enviados via comunidade(s) do usuário.
     *
     * @param sessao um id de sessão.
     * @return a mensagem.
     * @throws UsuarioNaoCadastradoException se a sessão for inválida.
     * @throws NaoHaMensagensException se não houver mensagens não lidas.
     */
    public String lerMensagem(String sessao) throws UsuarioNaoCadastradoException, NaoHaMensagensException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuarioPorSessao(sessao);
        var mensagens = usuario.getMensagens();

        if (mensagens.isEmpty()) {
            throw new NaoHaMensagensException();
        } else {
            return mensagens.remove(0).mensagem();
        }
    }

    /**
     * Envia a mensagem especificada a todos os membros da comunidade especificada.
     *
     * @param sessao um id de sessão.
     * @param comunidade o nome da comunidade.
     * @param mensagem o conteúdo da mensagem.
     * @throws UsuarioNaoCadastradoException se a sessão for inválida.
     * @throws ComunidadeNaoCriadaException se a comunidade não existir.
     */
    public void enviarMensagem(String sessao, String comunidade, String mensagem)
        throws UsuarioNaoCadastradoException, ComunidadeNaoCriadaException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuarioPorSessao(sessao);
        var login = usuario.getLogin();
        var aComunidade = dados.encontrarComunidade(comunidade);

        for (var membro : aComunidade.getMembros()) {
            dados.encontrarUsuario(membro).getMensagens().add(new Mensagem(login, mensagem));
        }
    }
}
