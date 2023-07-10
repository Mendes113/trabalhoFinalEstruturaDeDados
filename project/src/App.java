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

        mapaVeiculo.mostraMarca("Ford");

        // System.out.println("Mapa contém o valor Ford? " + mapaVeiculo.containsValue(new Veiculo("Ford")));

    //     Set<Integer> chassiSet = mapaVeiculo.keySet();
    // for (Integer chassi : chassiSet) {
    //     System.out.println("Chassi: " + chassi);
    // }   
//         String marcaDesejada = "Ford"; // Defina a marca desejada

// System.out.println("Carros da marca " + marcaDesejada + ":");

// for (Veiculo veiculo : mapaVeiculo.values()) {
//     if (veiculo.getMarca().equals(marcaDesejada)) {
//         System.out.println(veiculo); // Ou qualquer atributo específico do veículo que deseja imprimir
//     }
// }

//       mapaVeiculo.quantidadePorMarca();




    // // aqui começa a lista
    //  long startTime = System.nanoTime();
    //     ListaMap<Integer, Veiculo> mapaVeiculo = new ListaMap<>();

    //     for (int i = 0; i < 100000; i++) {
    //         Veiculo v = new Veiculo();
    //         mapaVeiculo.put(v.getChassi(), v);
    //     }

    //     mapaVeiculo.removerVeiculosAbaixoDoChassi(202050000);
    //     mapaVeiculo.quickSort();

    //     long endTime = System.nanoTime();
    //     long duration = (endTime - startTime);
    //     System.out.println("Tempo de execução: " + duration + " nanosegundos");
    //     double seconds = duration / 1_000_000_000.0;
    //     System.out.println("Tempo de execução: " + seconds + " segundos");

    //     System.out.println("Tamanho do mapa: " + mapaVeiculo.size());

    //     System.out.println("Mapa vazio? " + mapaVeiculo.isEmpty());

    //     System.out.println("Mapa contém a chave 202050000? " + mapaVeiculo.containsKey(202050000));
    //     mapaVeiculo.mostraMarca("Ford");
    //     System.out.println("FIm da lista");










//  //aqui começa mapa arvore

//        long startTime = System.nanoTime();
//         ArvoreMap<Integer, Veiculo> mapaVeiculo = new ArvoreMap<>();
//         for (int i = 0; i < 100000; i++) {
//             Veiculo v = new Veiculo();
//             mapaVeiculo.put(v.getChassi(), v);
//         }
//         mapaVeiculo.removerVeiculosAbaixoDoChassi(202050000);
//         long endTime = System.nanoTime();
//         long duration = (endTime - startTime);
//         System.out.println("Tempo de execução: " + duration + " nanosegundos");
//         double seconds = duration / 1_000_000_000.0;
//         System.out.println("Tempo de execução: " + seconds + " segundos");

//         System.out.println("Tamanho do mapa: " + mapaVeiculo.size());

//         System.out.println("Mapa vazio? " + mapaVeiculo.isEmpty());

//         System.out.println("Mapa contém a chave 202050000? " + mapaVeiculo.containsKey(202050000));
//         mapaVeiculo.mostraMarcaModelo("Ford");


    }


    
}
