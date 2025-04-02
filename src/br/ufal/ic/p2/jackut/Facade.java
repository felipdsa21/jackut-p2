package br.ufal.ic.p2.jackut;

import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import br.ufal.ic.p2.jackut.entities.Dados;
import br.ufal.ic.p2.jackut.exceptions.AtributoNaoPreenchidoException;
import br.ufal.ic.p2.jackut.exceptions.ConviteAindaNaoAceitoException;
import br.ufal.ic.p2.jackut.exceptions.JaEhAmigoException;
import br.ufal.ic.p2.jackut.exceptions.LoginInvalidoException;
import br.ufal.ic.p2.jackut.exceptions.LoginOuSenhaInvalidosException;
import br.ufal.ic.p2.jackut.exceptions.NaoHaRecadosException;
import br.ufal.ic.p2.jackut.exceptions.NaoPodeSeAdicionarComoAmigoException;
import br.ufal.ic.p2.jackut.exceptions.NaoPodeSeEnviarRecadoException;
import br.ufal.ic.p2.jackut.exceptions.SenhaInvalidaException;
import br.ufal.ic.p2.jackut.exceptions.UsuarioJaCadastradoException;
import br.ufal.ic.p2.jackut.exceptions.UsuarioNaoCadastradoException;
import br.ufal.ic.p2.jackut.services.AmizadeService;
import br.ufal.ic.p2.jackut.services.PerfilService;
import br.ufal.ic.p2.jackut.services.RecadoService;
import br.ufal.ic.p2.jackut.services.Sistema;
import br.ufal.ic.p2.jackut.services.UsuarioService;

/**
 * Classe façade a ser usada pelos testes.
 */
public final class Facade {
    /**
     * O caminho do arquivo onde os dados serão salvos.
     */
    private static final Path ARQUIVO_DADOS = Path.of("dados.ser");

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
        Dados dados;
        try (var ois = new ObjectInputStream(Files.newInputStream(ARQUIVO_DADOS))) {
            dados = (Dados)ois.readObject();
        } catch (InvalidClassException | NoSuchFileException e) {
            dados = new Dados();
        }

        this.sistema = new Sistema(dados);
    }

    /**
     * Apaga todos os dados mantidos no sistema.
     */
    public void zerarSistema() {
        this.sistema.setDados(new Dados());
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
    public void editarPerfil(String id, String atributo, String valor) throws UsuarioNaoCadastradoException {
        this.sistema.getPerfilService().editarPerfil(id, atributo, valor);
    }

    /**
     * Delega para {@link AmizadeService#adicionarAmigo}. Veja o Javadoc lá.
     */
    public void adicionarAmigo(String id, String amigo) throws
        UsuarioNaoCadastradoException,
        ConviteAindaNaoAceitoException,
        JaEhAmigoException,
        NaoPodeSeAdicionarComoAmigoException {
        this.sistema.getAmizadeService().adicionarAmigo(id, amigo);
    }

    /**
     * Delega para {@link AmizadeService#ehAmigo}. Veja o Javadoc lá.
     */
    public boolean ehAmigo(String login, String amigo) throws UsuarioNaoCadastradoException {
        return this.sistema.getAmizadeService().ehAmigo(login, amigo);
    }

    /**
     * Delega para {@link AmizadeService#getAmigos}. Veja o Javadoc lá.
     */
    public String getAmigos(String login) throws UsuarioNaoCadastradoException {
        return this.sistema.getAmizadeService().getAmigos(login);
    }

    /**
     * Delega para {@link RecadoService#enviarRecado}. Veja o Javadoc lá.
     */
    public void enviarRecado(String id, String destinatario, String mensagem)
        throws UsuarioNaoCadastradoException, NaoPodeSeEnviarRecadoException {
        this.sistema.getRecadosService().enviarRecado(id, destinatario, mensagem);
    }

    /**
     * Delega para {@link RecadoService#lerRecado}. Veja o Javadoc lá.
     */
    public String lerRecado(String id) throws UsuarioNaoCadastradoException, NaoHaRecadosException {
        return this.sistema.getRecadosService().lerRecado(id);
    }

    /**
     * Salva os dados do sistema em um arquivo.
     *
     * @throws IOException se não for possível salvar os dados.
     */
    public void encerrarSistema() throws IOException {
        try (var oos = new ObjectOutputStream(Files.newOutputStream(ARQUIVO_DADOS))) {
            oos.writeObject(this.sistema.getDados());
        }
    }
}
