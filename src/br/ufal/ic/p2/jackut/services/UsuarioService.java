package br.ufal.ic.p2.jackut.services;

import java.util.function.Supplier;

import br.ufal.ic.p2.jackut.entities.Dados;
import br.ufal.ic.p2.jackut.entities.Usuario;
import br.ufal.ic.p2.jackut.exceptions.usuario.LoginInvalidoException;
import br.ufal.ic.p2.jackut.exceptions.usuario.LoginOuSenhaInvalidosException;
import br.ufal.ic.p2.jackut.exceptions.usuario.SenhaInvalidaException;
import br.ufal.ic.p2.jackut.exceptions.usuario.UsuarioJaCadastradoException;
import br.ufal.ic.p2.jackut.exceptions.usuario.UsuarioNaoCadastradoException;
import br.ufal.ic.p2.jackut.utils.MiscUtils;

/**
 * Servi�o implementando cadastro e login de usu�rios.
 */
public final class UsuarioService {
    /**
     * O banco de dados.
     */
    private final Supplier<Dados> dados;

    /**
     * Cria nova inst�ncia do servi�o.
     *
     * @param dados o banco de dados.
     */
    public UsuarioService(Supplier<Dados> dados) {
        this.dados = dados;
    }

    /**
     * Cria um usu�rio com os dados da conta fornecidos.
     *
     * @param login o login do usu�rio.
     * @param senha a senha do usu�rio.
     * @param nome o valor para o atributo "nome" do usu�rio.
     * @throws UsuarioJaCadastradoException se um usu�rio com o mesmo login j� existir.
     * @throws LoginInvalidoException se o login for null ou uma string vazia.
     * @throws SenhaInvalidaException se o login for null ou uma string vazia.
     */
    public void criarUsuario(String login, String senha, String nome)
        throws UsuarioJaCadastradoException, SenhaInvalidaException, LoginInvalidoException {
        var dados = this.dados.get();
        dados.adicionarUsuario(new Usuario(login, senha, nome));
    }

    /**
     * Abre uma sess�o para um usu�rio com o login e a senha fornecidos.
     *
     * @param login o login do usu�rio.
     * @param senha a senha do usu�rio.
     * @return um id para esta sess�o.
     * @throws LoginOuSenhaInvalidosException se o usu�rio n�o existir ou a senha for incorreta.
     */
    public String abrirSessao(String login, String senha) throws LoginOuSenhaInvalidosException {
        var dados = this.dados.get();

        Usuario usuario;
        try {
            usuario = dados.encontrarUsuario(login);
        } catch (UsuarioNaoCadastradoException e) {
            throw new LoginOuSenhaInvalidosException();
        }

        if (usuario.getSenha().equals(senha)) {
            var sessao = MiscUtils.generateToken();
            dados.getSessoes().put(sessao, login);
            return sessao;
        } else {
            throw new LoginOuSenhaInvalidosException();
        }
    }
}
