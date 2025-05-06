package br.ufal.ic.p2.jackut.services;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.function.Supplier;

import br.ufal.ic.p2.jackut.entities.Dados;

/**
 * Classe contendo os dados e as instâncias de todos os serviços.
 */
public final class Sistema {
    /**
     * O caminho do arquivo onde o banco de dados está/será salvo.
     */
    private final Path caminho;

    /**
     * O serviço de usuários.
     */
    private final UsuarioService usuarioService;

    /**
     * O serviço de perfis.
     */
    private final PerfilService perfilService;

    /**
     * O serviço de relacionamentos.
     */
    private final RelacionamentoService relacionamentoService;

    /**
     * O serviço de recados.
     */
    private final RecadoService recadoService;

    /**
     * O banco de dados usado.
     */
    private Dados dados;

    /**
     * Cria instâncias dos serviços.
     *
     * @param caminho o caminho do banco de dados.
     */
    public Sistema(Path caminho) {
        this.caminho = caminho;
        Supplier<Dados> dadosSupplier = () -> this.dados;
        this.usuarioService = new UsuarioService(dadosSupplier);
        this.perfilService = new PerfilService(dadosSupplier);
        this.relacionamentoService = new RelacionamentoService(dadosSupplier);
        this.recadoService = new RecadoService(dadosSupplier);
    }

    /**
     * Carrega o banco de dados do arquivo.
     *
     * @throws IOException se houver um erro ao ler o arquivo.
     * @throws ClassNotFoundException se um arquivo inválido/corrompido for passado.
     */
    public void carregarDados() throws IOException, ClassNotFoundException {
        try (var ois = new ObjectInputStream(Files.newInputStream(this.caminho))) {
            this.dados = (Dados)ois.readObject();
        } catch (NoSuchFileException e) {
            this.dados = new Dados();
        }
    }

    /**
     * Salva o banco de dados no arquivo.
     *
     * @throws IOException se houver um erro ao salvar o arquivo.
     */
    public void salvarDados() throws IOException {
        try (var oos = new ObjectOutputStream(Files.newOutputStream(this.caminho))) {
            oos.writeObject(this.dados);
        }
    }

    /**
     * Apaga todos os dados mantidos no sistema.
     */
    public void zerarDados() {
        this.dados = new Dados();
    }

    /**
     * Retorna o serviço de usuários.
     *
     * @return o serviço.
     */
    public UsuarioService getUsuarioService() {
        return this.usuarioService;
    }

    /**
     * Retorna o serviço de perfis.
     *
     * @return o serviço.
     */
    public PerfilService getPerfilService() {
        return this.perfilService;
    }

    /**
     * Retorna o serviço de relacionamentos.
     *
     * @return o serviço.
     */
    public RelacionamentoService getRelacionamentoService() {
        return this.relacionamentoService;
    }

    /**
     * Retorna o serviço de recados.
     *
     * @return o serviço.
     */
    public RecadoService getRecadosService() {
        return this.recadoService;
    }
}
