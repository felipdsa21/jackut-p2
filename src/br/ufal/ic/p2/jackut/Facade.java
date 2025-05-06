package br.ufal.ic.p2.jackut;

import java.io.IOException;
import java.nio.file.Path;

import br.ufal.ic.p2.jackut.exceptions.recado.NaoHaRecadosException;
import br.ufal.ic.p2.jackut.exceptions.recado.NaoPodeSeEnviarRecadoException;
import br.ufal.ic.p2.jackut.exceptions.relacionamento.ConviteAindaNaoAceitoException;
import br.ufal.ic.p2.jackut.exceptions.relacionamento.JaEhAmigoException;
import br.ufal.ic.p2.jackut.exceptions.relacionamento.NaoPodeSeAdicionarComoAmigoException;
import br.ufal.ic.p2.jackut.exceptions.usuario.AtributoNaoPreenchidoException;
import br.ufal.ic.p2.jackut.exceptions.usuario.LoginInvalidoException;
import br.ufal.ic.p2.jackut.exceptions.usuario.LoginOuSenhaInvalidosException;
import br.ufal.ic.p2.jackut.exceptions.usuario.SenhaInvalidaException;
import br.ufal.ic.p2.jackut.exceptions.usuario.UsuarioJaCadastradoException;
import br.ufal.ic.p2.jackut.exceptions.usuario.UsuarioNaoCadastradoException;
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
        NaoPodeSeAdicionarComoAmigoException {
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
        throws UsuarioNaoCadastradoException, NaoPodeSeEnviarRecadoException {
        this.sistema.getRecadosService().enviarRecado(sessao, destinatario, mensagem);
    }

    /**
     * Delega para {@link RecadoService#lerRecado}. Veja o Javadoc lá.
     */
    public String lerRecado(String sessao) throws UsuarioNaoCadastradoException, NaoHaRecadosException {
        return this.sistema.getRecadosService().lerRecado(sessao);
    }
}
