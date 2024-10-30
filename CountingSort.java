import java.util.Random;

public class CountingSort {

    private int numIteracoes;

    public void countingSort(int[] vetor, int valorMaximo) {
        int[] conta = new int[valorMaximo + 1]; // Array que conta as ocorrências
        int[] output = new int[vetor.length]; // Array de saída
        numIteracoes = 0;

        // Conta as ocorrências de cada número do array
        for (int i = 0; i < vetor.length; i++) {
            numIteracoes++;
            conta[vetor[i]]++;
        }

        // Acumula as contagens
        for (int i = 1; i <= valorMaximo; i++) {
            numIteracoes++;
            conta[i] += conta[i - 1];
        }

        // Monta o array de saída
        for (int i = vetor.length - 1; i >= 0; i--) {
            numIteracoes++;
            output[conta[vetor[i]] - 1] = vetor[i];
            conta[vetor[i]]--;
        }

        // Copia o array de saída de volta original
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = output[i];
        }
    }

    public void logs(int[] vetor, int valorMaximo) {
        numIteracoes = 0;
        long tempoInicial = System.nanoTime();
        countingSort(vetor, valorMaximo);
        long tempoFinal = System.nanoTime();
        long duracao = (tempoFinal - tempoInicial);
        double duracaoSegundos = duracao / 1_000_000_000.0;
        System.out.println("--Duração: " + duracaoSegundos + " segundos");
        System.out.println("--"+numIteracoes+" iterações");
    }

    // main
    public static void main(String[] args) {
        Random rand = new Random(10); // Usando seed fixa para replicabilidade
        int[] tamanhos = {1000, 10000, 100000, 500000, 1000000};
        int valorMaximo = 1000000; // Limite superior dos valores do vetor

        for (int tamanho : tamanhos) {
            System.out.println("-------- CS VETOR TAMANHO: " + tamanho+"--------");
            for (int i = 0; i < 5; i++) { // Executar 5 vezes para cada tamanho
                System.out.println("----Iteração número "+i+":");
                int[] vetor = rand.ints(tamanho, 0, valorMaximo).toArray(); // Vetor com números aleatórios
                CountingSort cs = new CountingSort();
                cs.logs(vetor, valorMaximo);
            }
            System.out.println("\n");
        }
    }
}
