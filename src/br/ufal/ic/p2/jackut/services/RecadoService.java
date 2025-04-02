package br.ufal.ic.p2.jackut.services;

import java.util.function.Supplier;

import br.ufal.ic.p2.jackut.entities.Dados;
import br.ufal.ic.p2.jackut.exceptions.NaoHaRecadosException;
import br.ufal.ic.p2.jackut.exceptions.NaoPodeSeEnviarRecadoException;
import br.ufal.ic.p2.jackut.exceptions.UsuarioNaoCadastradoException;

/**
 * Servi�o implementando de envio de recados entre usu�rios.
 */
public final class RecadoService {
    /**
     * O banco de dados.
     */
    private final Supplier<Dados> dados;

    /**
     * Cria nova inst�ncia do servi�o.
     *
     * @param dados o banco de dados.
     */
    public RecadoService(Supplier<Dados> dados) {
        this.dados = dados;
    }

    /**
     * Envia o recado especificado ao destinat�rio especificado.
     *
     * @param id um id de sess�o.
     * @param destinatario o usu�rio que deve receber o recado.
     * @param mensagem a mensagem do recado.
     * @throws UsuarioNaoCadastradoException se a sess�o for inv�lida ou o destinat�rio n�o existir.
     * @throws NaoPodeSeEnviarRecadoException se o remetente e o destinat�rio forem o mesmo usu�rio.
     */
    public void enviarRecado(String id, String destinatario, String mensagem)
        throws UsuarioNaoCadastradoException, NaoPodeSeEnviarRecadoException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuarioPorSessao(id);
        var login = usuario.getLogin();

        if (login.equals(destinatario)) {
            throw new NaoPodeSeEnviarRecadoException();
        }

        var usuarioDestinatario = dados.encontrarUsuarioPorLogin(destinatario);
        usuarioDestinatario.getRecados().add(mensagem);
    }

    /**
     * Retorna o primeiro recado da fila de recados do usu�rio.
     *
     * @param id um id de sess�o.
     * @return a mensagem do recado.
     * @throws UsuarioNaoCadastradoException se a sess�o for inv�lida.
     * @throws NaoHaRecadosException se n�o houver recados n�o lidos.
     */
    public String lerRecado(String id) throws UsuarioNaoCadastradoException, NaoHaRecadosException {
        var dados = this.dados.get();
        var usuario = dados.encontrarUsuarioPorSessao(id);
        var recados = usuario.getRecados();

        if (recados.isEmpty()) {
            throw new NaoHaRecadosException();
        } else {
            return recados.remove(0);
        }
    }
}
