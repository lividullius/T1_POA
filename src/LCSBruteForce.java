import java.util.Scanner;

/**
 * LCS - Longest Common Subsequence
 * Algoritmo recursivo (força bruta)
 */
public class LCSBruteForce {

    // contador global de iterações (chamadas recursivas)
    static long iteracoes = 0;

    // m e n = quantos caracteres de s1 e s2 ainda faltam analisar
    static int lcs(String s1, String s2, int m, int n) {
        iteracoes++;

        // caso base: uma das strings acabou
        if (m == 0 || n == 0) {
            return 0;
        }

        // ultimos caracteres iguais -> fazem parte da LCS
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            return 1 + lcs(s1, s2, m - 1, n - 1);
        } else {
            // caracteres diferentes -> tenta descartar de s1 ou de s2
            // e fica com o melhor resultado (aqui a recursao se ramifica)
            return Math.max(lcs(s1, s2, m - 1, n), lcs(s1, s2, m, n - 1));
        }
    }

    static void executarTeste(String s1, String s2) {
        // zera o contador antes de cada teste
        iteracoes = 0;

        long inicio = System.nanoTime();
        int resultado = lcs(s1, s2, s1.length(), s2.length());
        long fim = System.nanoTime();

        double tempoMs = (fim - inicio) / 1_000_000.0;

        System.out.println("S1: " + s1);
        System.out.println("S2: " + s2);
        System.out.println("Tamanho da LCS: " + resultado);
        System.out.println("Numero de iteracoes (chamadas recursivas): " + iteracoes);
        System.out.println("Tempo de execucao: " + tempoMs + " ms");
        System.out.println("-----------------------------------------");
    }

    public static void main(String[] args) {
        // casos de teste simples
        executarTeste("ABCBDAB", "BDCABA");
        executarTeste("AGGTAB", "GXTXAYB");
        executarTeste("ABC", "AC");

        // casos de teste mais elaborados (cuidado: complexidade exponencial O(2^(m+n)))
        executarTeste("ABCBDABAB", "BDCABABAB");

        // permite testar strings digitadas pelo usuario
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite S1 (ou ENTER para sair):");
        String s1 = scanner.nextLine();
        if (!s1.isEmpty()) {
            System.out.println("Digite S2:");
            String s2 = scanner.nextLine();
            executarTeste(s1, s2);
        }
        scanner.close();
    }
}
