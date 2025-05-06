package br.ufal.ic.p2.jackut.services;

import java.util.function.Supplier;

import br.ufal.ic.p2.jackut.entities.Dados;
import br.ufal.ic.p2.jackut.exceptions.recado.NaoHaRecadosException;
import br.ufal.ic.p2.jackut.exceptions.recado.NaoPodeSeEnviarRecadoException;
import br.ufal.ic.p2.jackut.exceptions.usuario.UsuarioNaoCadastradoException;

/**
 * Serviço implementando envio de recados entre usuários.
 */
public final class RecadoService {
    /**
     * O banco de dados.
     */
    private final Supplier<Dados> dados;

    /**
     * Cria nova instância do serviço.
     *
     * @param dados o banco de dados.
     */
    public RecadoService(Supplier<Dados> dados) {
        this.dados = dados;
    }

    /**
     * Envia o recado especificado ao destinatário especificado.
     *
     * @param sessao um id de sessão.
     * @param destinatario o usuário que deve receber o recado.
     * @param mensagem a mensagem do recado.
     * @throws UsuarioNaoCadastradoException se a sessão for inválida ou o destinatário não existir.
     * @throws NaoPodeSeEnviarRecadoException se o remetente e o destinatário forem o mesmo usuário.
     */
    public void enviarRecado(String sessao, String destinatario, String mensagem)
        throws UsuarioNaoCadastradoException, NaoPodeSeEnviarRecadoException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuarioPorSessao(sessao);
        var login = usuario.getLogin();

        if (login.equals(destinatario)) {
            throw new NaoPodeSeEnviarRecadoException();
        }

        var usuarioDestinatario = dados.encontrarUsuario(destinatario);
        usuarioDestinatario.getRecados().add(mensagem);
    }

    /**
     * Retorna o primeiro recado da fila de recados do usuário.
     *
     * @param sessao um id de sessão.
     * @return a mensagem do recado.
     * @throws UsuarioNaoCadastradoException se a sessão for inválida.
     * @throws NaoHaRecadosException se não houver recados não lidos.
     */
    public String lerRecado(String sessao) throws UsuarioNaoCadastradoException, NaoHaRecadosException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuarioPorSessao(sessao);
        var recados = usuario.getRecados();

        if (recados.isEmpty()) {
            throw new NaoHaRecadosException();
        } else {
            return recados.remove(0);
        }
    }
}
