import br.ufal.ic.p2.jackut.Facade;
import easyaccept.EasyAccept;

/**
 * Classe para executar os testes.
 */
public class Main {
    /**
     * Chama o EasyAccept com o fa�ade para executar os testes.
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
            "tests/us5_1.txt",
            "tests/us5_2.txt",
            "tests/us6_1.txt",
            "tests/us6_2.txt",
            "tests/us7_1.txt",
            "tests/us7_2.txt",
            "tests/us8_1.txt",
            "tests/us8_2.txt",
            "tests/us9_1.txt",
            "tests/us9_2.txt",
        });
    }
}
