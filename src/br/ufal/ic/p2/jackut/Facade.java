package br.ufal.ic.p2.jackut;

import java.io.IOException;
import java.nio.file.Path;

import br.ufal.ic.p2.jackut.exceptions.comunidade.ComunidadeJaExisteException;
import br.ufal.ic.p2.jackut.exceptions.comunidade.ComunidadeNaoCriadaException;
import br.ufal.ic.p2.jackut.exceptions.comunidade.JaEhParteDaComunidadeException;
import br.ufal.ic.p2.jackut.exceptions.comunidade.NaoHaMensagensException;
import br.ufal.ic.p2.jackut.exceptions.recado.NaoHaRecadosException;
import br.ufal.ic.p2.jackut.exceptions.recado.NaoPodeSeEnviarRecadoException;
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
import br.ufal.ic.p2.jackut.exceptions.usuario.AtributoNaoPreenchidoException;
import br.ufal.ic.p2.jackut.exceptions.usuario.LoginInvalidoException;
import br.ufal.ic.p2.jackut.exceptions.usuario.LoginOuSenhaInvalidosException;
import br.ufal.ic.p2.jackut.exceptions.usuario.SenhaInvalidaException;
import br.ufal.ic.p2.jackut.exceptions.usuario.UsuarioJaCadastradoException;
import br.ufal.ic.p2.jackut.exceptions.usuario.UsuarioNaoCadastradoException;
import br.ufal.ic.p2.jackut.services.ComunidadeService;
import br.ufal.ic.p2.jackut.services.PerfilService;
import br.ufal.ic.p2.jackut.services.RecadoService;
import br.ufal.ic.p2.jackut.services.RelacionamentoService;
import br.ufal.ic.p2.jackut.services.Sistema;
import br.ufal.ic.p2.jackut.services.UsuarioService;

/**
 * Classe façade a ser usada pelos testes.
 */
public final class Facade {
    /**
     * Uma instância do sistema.
     */
    private final Sistema sistema;

    /**
     * Cria novo façade e inicializa o sistema.
     *
     * @throws IOException se ocorrer um erro ao ler os dados salvos.
     * @throws ClassNotFoundException se os dados usarem um formato não reconhecido.
     */
    public Facade() throws IOException, ClassNotFoundException {
        this.sistema = new Sistema(Path.of("dados.ser"));
        this.sistema.carregarDados();
    }

    /**
     * Apaga todos os dados mantidos no sistema.
     */
    public void zerarSistema() {
        this.sistema.zerarDados();
    }

    /**
     * Salva os dados do sistema em um arquivo.
     *
     * @throws IOException se não for possível salvar os dados.
     */
    public void encerrarSistema() throws IOException {
        this.sistema.salvarDados();
    }

    /**
     * Delega para {@link UsuarioService#criarUsuario}. Veja o Javadoc lá.
     */
    public void criarUsuario(String login, String senha, String nome)
        throws UsuarioJaCadastradoException, LoginInvalidoException, SenhaInvalidaException {
        this.sistema.getUsuarioService().criarUsuario(login, senha, nome);
    }

    /**
     * Delega para {@link UsuarioService#abrirSessao}. Veja o Javadoc lá.
     */
    public String abrirSessao(String login, String senha) throws LoginOuSenhaInvalidosException {
        return this.sistema.getUsuarioService().abrirSessao(login, senha);
    }

    /**
     * Delega para {@link PerfilService#getAtributoUsuario}. Veja o Javadoc lá.
     */
    public String getAtributoUsuario(String login, String atributo)
        throws UsuarioNaoCadastradoException, AtributoNaoPreenchidoException {
        return this.sistema.getPerfilService().getAtributoUsuario(login, atributo);
    }

    /**
     * Delega para {@link PerfilService#editarPerfil}. Veja o Javadoc lá.
     */
    public void editarPerfil(String sessao, String atributo, String valor) throws UsuarioNaoCadastradoException {
        this.sistema.getPerfilService().editarPerfil(sessao, atributo, valor);
    }

    /**
     * Delega para {@link RelacionamentoService#adicionarAmigo}. Veja o Javadoc lá.
     */
    public void adicionarAmigo(String sessao, String amigo) throws
        UsuarioNaoCadastradoException,
        ConviteAindaNaoAceitoException,
        JaEhAmigoException,
        NaoPodeSeAdicionarComoAmigoException,
        NaoPodeUsarFuncaoEmInimigoException {
        this.sistema.getRelacionamentoService().adicionarAmigo(sessao, amigo);
    }

    /**
     * Delega para {@link RelacionamentoService#ehAmigo}. Veja o Javadoc lá.
     */
    public boolean ehAmigo(String login, String amigo) throws UsuarioNaoCadastradoException {
        return this.sistema.getRelacionamentoService().ehAmigo(login, amigo);
    }

    /**
     * Delega para {@link RelacionamentoService#getAmigos}. Veja o Javadoc lá.
     */
    public String getAmigos(String login) throws UsuarioNaoCadastradoException {
        return this.sistema.getRelacionamentoService().getAmigos(login);
    }

    /**
     * Delega para {@link RecadoService#enviarRecado}. Veja o Javadoc lá.
     */
    public void enviarRecado(String sessao, String destinatario, String mensagem)
        throws UsuarioNaoCadastradoException, NaoPodeSeEnviarRecadoException, NaoPodeUsarFuncaoEmInimigoException {
        this.sistema.getRecadosService().enviarRecado(sessao, destinatario, mensagem);
    }

    /**
     * Delega para {@link RecadoService#lerRecado}. Veja o Javadoc lá.
     */
    public String lerRecado(String sessao) throws UsuarioNaoCadastradoException, NaoHaRecadosException {
        return this.sistema.getRecadosService().lerRecado(sessao);
    }

    /**
     * Delega para {@link ComunidadeService#criarComunidade}. Veja o Javadoc lá.
     */
    public void criarComunidade(String sessao, String nome, String descricao)
        throws UsuarioNaoCadastradoException, ComunidadeJaExisteException {
        this.sistema.getComunidadeService().criarComunidade(sessao, nome, descricao);
    }

    /**
     * Delega para {@link ComunidadeService#getDescricaoComunidade}. Veja o Javadoc lá.
     */
    public String getDescricaoComunidade(String nome) throws ComunidadeNaoCriadaException {
        return this.sistema.getComunidadeService().getDescricaoComunidade(nome);
    }

    /**
     * Delega para {@link ComunidadeService#getDonoComunidade}. Veja o Javadoc lá.
     */
    public String getDonoComunidade(String nome) throws ComunidadeNaoCriadaException {
        return this.sistema.getComunidadeService().getDonoComunidade(nome);
    }

    /**
     * Delega para {@link ComunidadeService#getMembrosComunidade}. Veja o Javadoc lá.
     */
    public String getMembrosComunidade(String nome) throws ComunidadeNaoCriadaException {
        return this.sistema.getComunidadeService().getMembrosComunidade(nome);
    }

    /**
     * Delega para {@link ComunidadeService#getComunidades}. Veja o Javadoc lá.
     */
    public String getComunidades(String login) throws UsuarioNaoCadastradoException {
        return this.sistema.getComunidadeService().getComunidades(login);
    }

    /**
     * Delega para {@link ComunidadeService#adicionarComunidade}. Veja o Javadoc lá.
     */
    public void adicionarComunidade(String sessao, String nome)
        throws ComunidadeNaoCriadaException, UsuarioNaoCadastradoException, JaEhParteDaComunidadeException {
        this.sistema.getComunidadeService().adicionarComunidade(sessao, nome);
    }

    /**
     * Delega para {@link ComunidadeService#lerMensagem}. Veja o Javadoc lá.
     */
    public String lerMensagem(String sessao) throws UsuarioNaoCadastradoException, NaoHaMensagensException {
        return this.sistema.getComunidadeService().lerMensagem(sessao);
    }

    /**
     * Delega para {@link ComunidadeService#enviarMensagem}. Veja o Javadoc lá.
     */
    public void enviarMensagem(String sessao, String comunidade, String mensagem)
        throws ComunidadeNaoCriadaException, UsuarioNaoCadastradoException {
        this.sistema.getComunidadeService().enviarMensagem(sessao, comunidade, mensagem);
    }

    /**
     * Delega para {@link RelacionamentoService#ehFa}. Veja o Javadoc lá.
     */
    public boolean ehFa(String login, String idolo) throws UsuarioNaoCadastradoException {
        return this.sistema.getRelacionamentoService().ehFa(login, idolo);
    }

    /**
     * Delega para {@link RelacionamentoService#adicionarIdolo}. Veja o Javadoc lá.
     */
    public void adicionarIdolo(String sessao, String idolo) throws
        UsuarioNaoCadastradoException,
        NaoPodeUsarFuncaoEmInimigoException,
        JaEhIdoloException,
        NaoPodeSeAdicionarComoIdoloException {
        this.sistema.getRelacionamentoService().adicionarIdolo(sessao, idolo);
    }

    /**
     * Delega para {@link RelacionamentoService#getFas}. Veja o Javadoc lá.
     */
    public String getFas(String login) throws UsuarioNaoCadastradoException {
        return this.sistema.getRelacionamentoService().getFas(login);
    }

    /**
     * Delega para {@link RelacionamentoService#ehPaquera}. Veja o Javadoc lá.
     */
    public boolean ehPaquera(String sessao, String paquera) throws UsuarioNaoCadastradoException {
        return this.sistema.getRelacionamentoService().ehPaquera(sessao, paquera);
    }

    /**
     * Delega para {@link RelacionamentoService#adicionarPaquera}. Veja o Javadoc lá.
     */
    public void adicionarPaquera(String sessao, String paquera) throws
        UsuarioNaoCadastradoException,
        NaoPodeSeAdicionarComoPaqueraException,
        NaoPodeUsarFuncaoEmInimigoException,
        JaEhPaqueraException {
        this.sistema.getRelacionamentoService().adicionarPaquera(sessao, paquera);
    }

    /**
     * Delega para {@link RelacionamentoService#getPaqueras}. Veja o Javadoc lá.
     */
    public String getPaqueras(String sessao) throws UsuarioNaoCadastradoException {
        return this.sistema.getRelacionamentoService().getPaqueras(sessao);
    }

    /**
     * Delega para {@link RelacionamentoService#adicionarInimigo}. Veja o Javadoc lá.
     */
    public void adicionarInimigo(String sessao, String inimigo)
        throws UsuarioNaoCadastradoException, NaoPodeSeAdicionarComoInimigoException, JaEhInimigoException {
        this.sistema.getRelacionamentoService().adicionarInimigo(sessao, inimigo);
    }

    /**
     * Delega para {@link UsuarioService#removerUsuario}. Veja o Javadoc lá.
     */
    public void removerUsuario(String sessao) throws UsuarioNaoCadastradoException {
        this.sistema.getUsuarioService().removerUsuario(sessao);
    }
}
