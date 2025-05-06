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
 * Classe contendo os dados e as inst�ncias de todos os servi�os.
 */
public final class Sistema {
    /**
     * O caminho do arquivo onde o banco de dados est�/ser� salvo.
     */
    private final Path caminho;

    /**
     * O servi�o de usu�rios.
     */
    private final UsuarioService usuarioService;

    /**
     * O servi�o de perfis.
     */
    private final PerfilService perfilService;

    /**
     * O servi�o de relacionamentos.
     */
    private final RelacionamentoService relacionamentoService;

    /**
     * O servi�o de recados.
     */
    private final RecadoService recadoService;

    /**
     * O banco de dados usado.
     */
    private Dados dados;

    /**
     * Cria inst�ncias dos servi�os.
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
     * @throws ClassNotFoundException se um arquivo inv�lido/corrompido for passado.
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
     * Retorna o servi�o de usu�rios.
     *
     * @return o servi�o.
     */
    public UsuarioService getUsuarioService() {
        return this.usuarioService;
    }

    /**
     * Retorna o servi�o de perfis.
     *
     * @return o servi�o.
     */
    public PerfilService getPerfilService() {
        return this.perfilService;
    }

    /**
     * Retorna o servi�o de relacionamentos.
     *
     * @return o servi�o.
     */
    public RelacionamentoService getRelacionamentoService() {
        return this.relacionamentoService;
    }

    /**
     * Retorna o servi�o de recados.
     *
     * @return o servi�o.
     */
    public RecadoService getRecadosService() {
        return this.recadoService;
    }
}
