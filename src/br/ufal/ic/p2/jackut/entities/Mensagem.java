package br.ufal.ic.p2.jackut.entities;

import java.io.Serializable;

/**
 * Classe representando um recado/mensagem enviado entre usuários.
 *
 * @param remetente o usuário remetente da mensagem.
 * @param mensagem o conteúdo da mensagem.
 */
public record Mensagem(String remetente, String mensagem) implements Serializable {
}
