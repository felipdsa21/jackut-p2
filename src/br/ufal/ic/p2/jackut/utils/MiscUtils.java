package br.ufal.ic.p2.jackut.utils;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Collection;
import java.util.function.Supplier;

/**
 * Classe contendo v�rias fun��es �teis.
 */
public final class MiscUtils {
    /**
     * Um gerador de n�meros aleat�rios.
     */
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    /**
     * Um codificador Base64.
     */
    private static final Base64.Encoder BASE64_ENCODER = Base64.getUrlEncoder().withoutPadding();

    /**
     * Construtor privado.
     */
    private MiscUtils() {
    }

    /**
     * Junta uma Collection (List, Set etc) de strings em uma �nica string.
     * <br>A string resultante come�a e termina com chaves '{' e '}' e os elementos s�o separados por v�rgula sem
     * espa�os adicionais.
     *
     * @param collection a Collection.
     * @return a string representando a collection.
     */
    public static String collectionToString(Collection<String> collection) {
        return collection.isEmpty() ? "{}" : "{" + String.join(",", collection) + "}";
    }

    /**
     * Cria um token aleat�rio de 32 caracteres.
     *
     * @return o token.
     */
    public static String generateToken() {
        var randomBytes = new byte[24];
        SECURE_RANDOM.nextBytes(randomBytes);
        return BASE64_ENCODER.encodeToString(randomBytes);
    }

    /**
     * Verifica se uma string n�o � null e n�o � vazia e lan�a uma exce��o se ela for.
     *
     * @param value a string.
     * @param e o construtor da exce��o.
     * @param <E> o tipo da exce��o.
     * @return a string passada.
     * @throws E se a string for null ou vazia.
     */
    public static <E extends Throwable> String requireNonEmptyString(String value, Supplier<E> e) throws E {
        if (value == null || value.isEmpty()) {
            throw e.get();
        } else {
            return value;
        }
    }

    /**
     * Verifica se um objeto n�o � null e lan�a uma exce��o se ela for.
     *
     * @param obj o objeto.
     * @param e o construtor da exce��o.
     * @param <T> o tipo do objeto.
     * @param <E> o tipo da exce��o.
     * @return o objeto passado.
     * @throws E se o objeto for null.
     */
    public static <T, E extends Throwable> T requireNonNull(T obj, Supplier<E> e) throws E {
        if (obj == null) {
            throw e.get();
        } else {
            return obj;
        }
    }
}
