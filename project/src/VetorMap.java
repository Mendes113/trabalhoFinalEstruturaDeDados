import java.util.*;
import java.util.Map;


public class VetorMap<K, V> implements Map<K, V> {
    private int tamanho;
    private int numElementos;
    private Veiculo[] vetor;

    public VetorMap(int tamanhoInicial) {
        this.tamanho = tamanhoInicial;
        this.numElementos = 0;
        this.vetor = new Veiculo[tamanhoInicial];
    }   
    
    //   @Override
    // public void putAll(Map<? extends K, ? extends V> map) {
    //     for (Entry<? extends K, ? extends V> entry : map.entrySet()) {
    //         K key = entry.getKey();
    //         V value = entry.getValue();
    //         put(key, value);
    //     }
    // }

    @Override
public void putAll(Map<? extends K, ? extends V> map) {
    throw new UnsupportedOperationException("putAll operation is not supported.");
}


    @Override
    public int size() {
        return numElementos;
    }

    @Override
    public boolean isEmpty() {
        return numElementos == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (!(key instanceof Integer)) {
            return false;
        }
        int chassi = (int) key;
        for (int i = 0; i < numElementos; i++) {
            if (vetor[i].getChassi() == chassi) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        if (!(value instanceof Veiculo)) {
            return false;
        }
        Veiculo veiculo = (Veiculo) value;
        for (int i = 0; i < numElementos; i++) {
            if (vetor[i].equals(veiculo)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        if (!(key instanceof Integer)) {
            return null;
        }
        int chassi = (int) key;
        for (int i = 0; i < numElementos; i++) {
            if (vetor[i].getChassi() == chassi) {
                return (V) vetor[i];
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (!(key instanceof Integer) || !(value instanceof Veiculo)) {
            throw new IllegalArgumentException("Invalid key or value type");
        }
        int chassi = (int) key;
        Veiculo veiculo = (Veiculo) value;

        // Redimensiona o vetor se necessário
        if (numElementos == tamanho) {
            redimensionarVetor();
        }

        // Encontra a posição correta para inserir o veículo com base no número de chassi
        int posicao = 0;
        while (posicao < numElementos && vetor[posicao].getChassi() < chassi) {
            posicao++;
        }

        // Desloca os elementos à direita para abrir espaço para o novo veículo
        for (int i = numElementos; i > posicao; i--) {
            vetor[i] = vetor[i - 1];
        }

        // Insere o novo veículo na posição correta
        vetor[posicao] = veiculo;
        numElementos++;

        return value;
    }

    @Override
    public V remove(Object key) {
        if (!(key instanceof Integer)) {
            return null;
        }
        int chassi = (int) key;
        int posicao = -1;
        for (int i = 0; i < numElementos; i++) {
            if (vetor[i].getChassi() == chassi) {
                posicao = i;
                break;
            }
        }
        if (posicao != -1) {
            Veiculo veiculoRemovido = vetor[posicao];
            // Desloca os elementos à esquerda para preencher o espaço deixado pela remoção
            for (int i = posicao; i < numElementos - 1; i++) {
                vetor[i] = vetor[i + 1];
            }
            vetor[numElementos - 1] = null; // Define a última posição como null
            numElementos--;
            return (V) veiculoRemovido;
        }
        return null;
    }

    @Override
    public void clear() {
        for (int i = 0; i < numElementos; i++) {
            vetor[i] = null;
        }
        numElementos = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (int i = 0; i < numElementos; i++) {
            keys.add((K) Integer.valueOf(vetor[i].getChassi()));
        }
        return keys;
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        for (int i = 0; i < numElementos; i++) {
            values.add((V) vetor[i]);
        }
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entries = new HashSet<>();
        for (int i = 0; i < numElementos; i++) {
            Entry<K, V> entry = new VetorEntry<>((K) Integer.valueOf(vetor[i].getChassi()), (V) vetor[i]);
            entries.add(entry);
        }
        return entries;
    }

    private void redimensionarVetor() {
        int novoTamanho = tamanho * 2;
        Veiculo[] novoVetor = new Veiculo[novoTamanho];
        System.arraycopy(vetor, 0, novoVetor, 0, tamanho);
        vetor = novoVetor;
        tamanho = novoTamanho;
    }

    private static class VetorEntry<K, V> implements Entry<K, V> {
        private final K key;
        private V value;

        public VetorEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof VetorEntry) {
                VetorEntry<?, ?> other = (VetorEntry<?, ?>) obj;
                return Objects.equals(key, other.key) && Objects.equals(value, other.value);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }


// Enquanto vet[esq] < vet[pivo]
// esq++
// Enquanto vet[dir] >= vet[pivo]
// dir--
// Se esq < dir
// troca(vet[esq], vet[dir])
// Repetir enquanto esq < dir

 public void heapSort() {
        // Build max heap
        for (int i = numElementos / 2 - 1; i >= 0; i--) {
            heapify(numElementos, i);
        }

        // Extract elements from the heap in sorted order
        for (int i = numElementos - 1; i > 0; i--) {
            // Move current root (maximum element) to the end
            trocar(0, i);
            // Call max heapify on the reduced heap
            heapify(i, 0);
        }
    }

    private void heapify(int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1; // Left child
        int right = 2 * i + 2; // Right child

        // If left child is larger than root
        if (left < n && vetor[left].getChassi() > vetor[largest].getChassi()) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && vetor[right].getChassi() > vetor[largest].getChassi()) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            trocar(i, largest);
            // Recursively heapify the affected sub-tree
            heapify(n, largest);
        }
    }

    private void trocar(int i, int j) {
        Veiculo temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }

 public void removerVeiculosAbaixoDoChassi(int chassiLimite) {
    int[] veiculosRemovidos = new int[numElementos];
    int numVeiculosRemovidos = 0;

    for (int i = 0; i < numElementos; i++) {
        int chassi = vetor[i].getChassi();
        if (chassi <= chassiLimite) {
            veiculosRemovidos[numVeiculosRemovidos] = chassi;
            numVeiculosRemovidos++;
            vetor[i] = null;
        }
    }

    Veiculo[] novoVetor = new Veiculo[numElementos - numVeiculosRemovidos];
    int index = 0;
    for (int i = 0; i < numElementos; i++) {
        if (vetor[i] != null) {
            novoVetor[index] = vetor[i];
            index++;
        }
    }

    vetor = novoVetor;
    numElementos = vetor.length;

    System.out.println("Quantidade de Veículos removidos: " + numVeiculosRemovidos);
}



   public void quantidadePorMarca() {
    Map<String, Integer> quantidadePorMarca = new HashMap<>();

    for (int i = 0; i < numElementos; i++) {
        String marca = vetor[i].getMarca();
        if (quantidadePorMarca.containsKey(marca)) {
            quantidadePorMarca.put(marca, quantidadePorMarca.get(marca) + 1);
        } else {
            quantidadePorMarca.put(marca, 1);
        }
    }

    System.out.println("Quantidade de veículos por marca: " + quantidadePorMarca);
}

public void mostraMarca(String marcaEspecifica) {
    System.out.println("Chassis dos veículos da marca " + marcaEspecifica + ":");

    for (int i = 0; i < numElementos; i++) {
        String marcaAtual = vetor[i].getMarca();
        if (marcaEspecifica.equals(marcaAtual)) {
            int chassi = vetor[i].getChassi();
            System.out.println("Numero chssi:" + chassi);
        }
    }
}

public void imprimirVeiculosEmOrdemCrescente() {
    System.out.println("Veículos em ordem crescente de número de chassi:");
    Arrays.sort(vetor, 0, numElementos);
    for (int i = 0; i < numElementos; i++) {
        System.out.println(vetor[i]);
    }
}
public void executa() {
    long startTime = System.nanoTime();
    VetorMap<Integer, Veiculo> mapaVeiculo = new VetorMap<>(100000);

    Runtime runtime = Runtime.getRuntime();
    long initialMemory = runtime.totalMemory() - runtime.freeMemory();

    // Inserção de veículos
    long insercaoStartTime = System.nanoTime();
    for (int i = 0; i < 100000; i++) {
        Veiculo v = new Veiculo();
        mapaVeiculo.put(v.getChassi(), v);
    }
    long insercaoEndTime = System.nanoTime();
    long insercaoDuration = (insercaoEndTime - insercaoStartTime);
    double insercaoSeconds = insercaoDuration / 1_000_000_000.0;

    // Remoção de veículos
    long remocaoStartTime = System.nanoTime();
    mapaVeiculo.removerVeiculosAbaixoDoChassi(202050000);
    long remocaoEndTime = System.nanoTime();
    long remocaoDuration = (remocaoEndTime - remocaoStartTime);
    double remocaoSeconds = remocaoDuration / 1_000_000_000.0;

    // Ordenação dos veículos
    long ordenacaoStartTime = System.nanoTime();
    mapaVeiculo.heapSort();
    long ordenacaoEndTime = System.nanoTime();
    long ordenacaoDuration = (ordenacaoEndTime - ordenacaoStartTime);
    double ordenacaoSeconds = ordenacaoDuration / 1_000_000_000.0;

    // Impressão dos veículos em ordem crescente de número de chassi
    long impressaoStartTime = System.nanoTime();
    mapaVeiculo.imprimirVeiculosEmOrdemCrescente();
    long impressaoEndTime = System.nanoTime();
    long impressaoDuration = (impressaoEndTime - impressaoStartTime);
    double impressaoSeconds = impressaoDuration / 1_000_000_000.0;

    // Mostrar número de veículos da marca Ford
    long marcaStartTime = System.nanoTime();
    mapaVeiculo.mostraMarca("Ford");
    long marcaEndTime = System.nanoTime();
    long marcaDuration = (marcaEndTime - marcaStartTime);
    double marcaSeconds = marcaDuration / 1_000_000_000.0;

    long endTime = System.nanoTime();
    long duration = (endTime - startTime);
    double totalSeconds = duration / 1_000_000_000.0;

    // Verificar o uso de memória após a execução das operações
    long finalMemory = runtime.totalMemory() - runtime.freeMemory();
    long usedMemory = finalMemory - initialMemory;

    // Exibir todos os tempos ao final da execução
    System.out.println("Tempo de inserção: " + insercaoSeconds + " segundos");
    System.out.println("Tempo de remoção: " + remocaoSeconds + " segundos");
    System.out.println("Tempo de ordenação: " + ordenacaoSeconds + " segundos");
    System.out.println("Tempo de impressão: " + impressaoSeconds + " segundos");
    System.out.println("Tempo para mostrar a marca Ford: " + marcaSeconds + " segundos");
    System.out.println("Tempo total de execução: " + totalSeconds + " segundos");

    System.out.println("Tamanho do mapa: " + mapaVeiculo.size());
    System.out.println("Mapa vazio? " + mapaVeiculo.isEmpty());
    System.out.println("Mapa contém a chave 202050000? " + mapaVeiculo.containsKey(202050000));

    mapaVeiculo.quantidadePorMarca();

    System.out.println("Uso de memória total: " + usedMemory + " bytes");
    System.out.println("Uso de memória total: " + bytesToMegabytes(usedMemory) + " MB");
}


    




private static long bytesToMegabytes(long bytes) {
    return bytes / (1024 * 1024);
}


}


