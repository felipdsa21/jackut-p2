package br.ufal.ic.p2.jackut.entities;

import java.io.Serializable;

/**
 * Classe representando um recado/mensagem enviado entre usu�rios.
 *
 * @param remetente o usu�rio remetente da mensagem.
 * @param mensagem o conte�do da mensagem.
 */
public record Mensagem(String remetente, String mensagem) implements Serializable {
}
