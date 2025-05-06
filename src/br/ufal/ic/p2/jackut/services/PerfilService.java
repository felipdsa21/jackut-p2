package br.ufal.ic.p2.jackut.services;

import java.util.function.Supplier;

import br.ufal.ic.p2.jackut.entities.Dados;
import br.ufal.ic.p2.jackut.exceptions.usuario.AtributoNaoPreenchidoException;
import br.ufal.ic.p2.jackut.exceptions.usuario.UsuarioNaoCadastradoException;
import br.ufal.ic.p2.jackut.utils.MiscUtils;

/**
 * Servi�o implementando perfil e atributos de usu�rios.
 */
public final class PerfilService {
    /**
     * O banco de dados.
     */
    private final Supplier<Dados> dados;

    /**
     * Cria nova inst�ncia do servi�o.
     *
     * @param dados o banco de dados.
     */
    public PerfilService(Supplier<Dados> dados) {
        this.dados = dados;
    }

    /**
     * Retorna o valor do atributo de um usu�rio, armazenado em seu perfil.
     *
     * @param login o login do usu�rio.
     * @param atributo o nome do atributo.
     * @return o valor do atributo.
     * @throws UsuarioNaoCadastradoException se o usu�rio n�o existir.
     * @throws AtributoNaoPreenchidoException se o atributo n�o estiver preenchido.
     */
    public String getAtributoUsuario(String login, String atributo)
        throws UsuarioNaoCadastradoException, AtributoNaoPreenchidoException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuario(login);
        return MiscUtils.requireNonNull(usuario.getAtributos().get(atributo), AtributoNaoPreenchidoException::new);
    }

    /**
     * Modifica o valor de um atributo do perfil de um usu�rio para o valor especificado.
     *
     * @param sessao um id de sess�o.
     * @param atributo o nome do atributo.
     * @param valor o novo valor.
     * @throws UsuarioNaoCadastradoException se a sess�o for inv�lida.
     */
    public void editarPerfil(String sessao, String atributo, String valor) throws UsuarioNaoCadastradoException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuarioPorSessao(sessao);
        usuario.getAtributos().put(atributo, valor);
    }
}
