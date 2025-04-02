package br.ufal.ic.p2.jackut.services;

import br.ufal.ic.p2.jackut.entities.Dados;

/**
 * Classe contendo os dados e as instâncias de todos os serviços.
 */
public final class Sistema {
    /**
     * O serviço de usuários.
     */
    private final UsuarioService usuarioService;

    /**
     * O serviço de perfis.
     */

    private final PerfilService perfilService;
    /**
     * O serviço de amizades.
     */

    private final AmizadeService amizadeService;

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
     * Retorna o serviço de amizades.
     *
     * @return o serviço.
     */
    public AmizadeService getAmizadeService() {
        return this.amizadeService;
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
