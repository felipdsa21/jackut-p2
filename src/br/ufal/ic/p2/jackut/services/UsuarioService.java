package br.ufal.ic.p2.jackut.services;

import java.util.function.Supplier;

import br.ufal.ic.p2.jackut.entities.Dados;
import br.ufal.ic.p2.jackut.entities.Usuario;
import br.ufal.ic.p2.jackut.exceptions.LoginInvalidoException;
import br.ufal.ic.p2.jackut.exceptions.LoginOuSenhaInvalidosException;
import br.ufal.ic.p2.jackut.exceptions.SenhaInvalidaException;
import br.ufal.ic.p2.jackut.exceptions.UsuarioJaCadastradoException;
import br.ufal.ic.p2.jackut.exceptions.UsuarioNaoCadastradoException;
import br.ufal.ic.p2.jackut.utils.MiscUtils;

/**
 * Serviço implementando de cadastro e login de usuários.
 */
public final class UsuarioService {
    /**
     * O banco de dados.
     */
    private final Supplier<Dados> dados;

    /**
     * Cria nova instância do serviço.
     *
     * @param dados o banco de dados.
     */
    public UsuarioService(Supplier<Dados> dados) {
        this.dados = dados;
    }

    /**
     * Cria um usuário com os dados da conta fornecidos.
     *
     * @param login o login do usuário.
     * @param senha a senha do usuário.
     * @param nome o valor para o atributo "nome" do usuário.
     * @throws UsuarioJaCadastradoException se um usuário com o mesmo login já existir.
     * @throws LoginInvalidoException se o login for null ou uma string vazia.
     * @throws SenhaInvalidaException se o login for null ou uma string vazia.
     */
    public void criarUsuario(String login, String senha, String nome)
        throws UsuarioJaCadastradoException, SenhaInvalidaException, LoginInvalidoException {
        var dados = this.dados.get();
        dados.adicionarUsuario(new Usuario(login, senha, nome));
    }

    /**
     * Abre uma sessão para um usuário com o login e a senha fornecidos.
     *
     * @param login o login do usuário.
     * @param senha a senha do usuário.
     * @return um id para esta sessão.
     * @throws LoginOuSenhaInvalidosException se o usuário não existir ou a senha for incorreta.
     */
    public String abrirSessao(String login, String senha) throws LoginOuSenhaInvalidosException {
        var dados = this.dados.get();

        Usuario usuario;
        try {
            usuario = dados.encontrarUsuarioPorLogin(login);
        } catch (UsuarioNaoCadastradoException e) {
            throw new LoginOuSenhaInvalidosException();
        }

        if (usuario.getSenha().equals(senha)) {
            var id = MiscUtils.generateToken();
            dados.getSessoes().put(id, usuario);
            return id;
        } else {
            throw new LoginOuSenhaInvalidosException();
        }
    }
}
