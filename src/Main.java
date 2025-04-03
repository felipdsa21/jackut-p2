import br.ufal.ic.p2.jackut.Facade;
import easyaccept.EasyAccept;

/**
 * Classe para executar os testes.
 */
public class Main {
    /**
     * Chama o EasyAccept com o façade para executar os testes.
     *
     * @param args ignorado.
     */
    public static void main(String[] args) {
        EasyAccept.main(new String[] {
            Facade.class.getName(),
            "tests/us1_1.txt",
            "tests/us1_2.txt",
            "tests/us2_1.txt",
            "tests/us2_2.txt",
            "tests/us3_1.txt",
            "tests/us3_2.txt",
            "tests/us4_1.txt",
            "tests/us4_2.txt",
        });
    }
}
