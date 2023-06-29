import java.util.Set;

public class App {
    public static void main(String[] args) throws Exception {
      

      long startTime = System.nanoTime();
            VetorMap<Integer, Veiculo> mapaVeiculo = new VetorMap<>(100000);
        for(int i = 0; i < 100000; i++){
            Veiculo v = new Veiculo();
            mapaVeiculo.put(v.getChassi(), v);
            
        }
         mapaVeiculo.removerVeiculosAbaixoDoChassi(202050000);
         mapaVeiculo.heapSort();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Tempo de execução: " + duration + " nanosegundos");
        double seconds = duration / 1_000_000_000.0;
        System.out.println("Tempo de execução: " + seconds + " segundos");

        System.out.println("Tamanho do mapa: " + mapaVeiculo.size());

        System.out.println("Mapa vazio? " + mapaVeiculo.isEmpty());

        System.out.println("Mapa contém a chave 202050000? " + mapaVeiculo.containsKey(202050000));

        // System.out.println("Mapa contém o valor Ford? " + mapaVeiculo.containsValue(new Veiculo("Ford")));

    //     Set<Integer> chassiSet = mapaVeiculo.keySet();
    // for (Integer chassi : chassiSet) {
    //     System.out.println("Chassi: " + chassi);
    // }   


     
    }
}
