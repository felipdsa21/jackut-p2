package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.entities.Dados;

/**
 * Classe contendo os dados e as inst�ncias de todos os servi�os.
 */
public final class Sistema {
    /**
     * O servi�o de usu�rios.
     */
    private final UsuarioService usuarioService;

    /**
     * O servi�o de perfis.
     */

    private final PerfilService perfilService;
    /**
     * O servi�o de amizades.
     */

    private final AmizadeService amizadeService;

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
     * @param dados o banco de dados.
     */
    public Sistema(Dados dados) {
        this.dados = dados;
        this.usuarioService = new UsuarioService(() -> this.dados);
        this.perfilService = new PerfilService(() -> this.dados);
        this.amizadeService = new AmizadeService(() -> this.dados);
        this.recadoService = new RecadoService(() -> this.dados);
    }

    /**
     * Retorna o banco de dados usado.
     *
     * @return o banco.
     */
    public Dados getDados() {
        return this.dados;
    }

    /**
     * Troca o banco de dados usado.
     */
    public void setDados(Dados dados) {
        this.dados = dados;
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
     * Retorna o servi�o de amizades.
     *
     * @return o servi�o.
     */
    public AmizadeService getAmizadeService() {
        return this.amizadeService;
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
