// Nicolas Andreas Jackel
import java.util.Random;

public class QuickSort {

    private int numTrocas;
    private int numIteracoes;

    public void quickSort(int[] vetor, int menor, int maior) {
        if (menor < maior) {
            numIteracoes++;
            int pivo = partition(vetor, menor, maior); // Índice do pivo após chamar partition
            quickSort(vetor, menor, pivo - 1); // Ordenar valores menores antes do pivo
            quickSort(vetor, pivo + 1, maior); // Ordenar valores maiores depois do pivo
        }
    }

    private int partition(int[] vetor, int menor, int maior) {
        int pivo = vetor[maior]; // Escolher o último elemento como pivo
        int indice_menor_valor = (menor - 1); // Índice do menor elemento

        for (int i = menor; i < maior; i++) {
            numIteracoes++;
            if (vetor[i] < pivo) { // Se o elemento atual é menor que o pivo
                indice_menor_valor++;
                // Troca o menor elemento antigo pelo menor elemento novo
                int temp = vetor[indice_menor_valor];
                vetor[indice_menor_valor] = vetor[i];
                vetor[i] = temp;
                numTrocas++; // Conta as trocas
            }
        }
        // Troca o pivo com o elemento i+1
        int temp = vetor[indice_menor_valor + 1];
        vetor[indice_menor_valor + 1] = vetor[maior];
        vetor[maior] = temp;
        numTrocas++; // Conta a troca final

        return indice_menor_valor + 1; // índice do pivo
    }

    public void logs(int[] vetor) {
        numTrocas = 0;
        numIteracoes = 0;
        long tempoInicial = System.nanoTime();
        quickSort(vetor, 0, vetor.length - 1);
        long tempoFinal = System.nanoTime();
        long duracao = (tempoFinal - tempoInicial);
        double duracaoSegundos = duracao / 1_000_000_000.0;
        System.out.println("--Duração: " + duracaoSegundos + " segundos");
        System.out.println(numTrocas+" trocas");
        System.out.println("--"+numIteracoes+ "iterações");
    }

    // main
    public static void main(String[] args) {
        Random rand = new Random(10); // Usando seed fixa para replicabilidade
        int[] tamanhos = {1000, 10000, 100000, 500000, 1000000}; // 5 vetores que serão percorridos, 5 vezes cada

        for (int tamanho : tamanhos) {
            System.out.println("--------QS VETOR TAMANHO " + tamanho);
            for (int i = 0; i < 5; i++) { // Executar 5 vezes para cada tamanho
                System.out.println("----Iteração número "+i+":");
                int[] vetor = rand.ints(tamanho, 0, tamanho).toArray(); // Vetor com números aleatórios
                QuickSort qs = new QuickSort();
                qs.logs(vetor); // Ordenar e medir
            }
        }
    }
}
