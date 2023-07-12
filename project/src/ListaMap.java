import java.util.Collection;
import java.util.Iterator;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListaMap<K extends Comparable<K>, V> implements Map<K, V>{

    private Noh<K,V> head;

    @Override
    public int size() {
        if(head == null){
            return 0;
        
        }else{
            int cont = 0;
            Noh<K,V> aux = head;
            while(aux != null){
                cont++;
                aux = aux.getProx();
            }
            return cont;
        }

        
    }

    @Override
    public boolean isEmpty() {
        if(head == null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean containsKey(Object key) {
      
        if(head == null){
            return false;
        }else{
            Noh<K,V> aux = head;
            while(aux != null){
                if(aux.getKey().equals(key)){
                    return true;
                }
                aux = aux.getProx();
            }
            return false;
        }
    }

    @Override
    public boolean containsValue(Object value) {
        
        if(head == null){
            return false;
        }else{
            Noh<K,V> aux = head;
            while(aux != null){
                if(aux.getValue().equals(value)){
                    return true;
                }
                aux = aux.getProx();
            }
            return false;
        }
    }

    @Override
    public V get(Object key) {
        
        if(head == null){
            return null;
        }else{
            Noh<K,V> aux = head;
            while(aux != null){
                if(aux.getKey().equals(key)){
                    return aux.getValue();
                }
                aux = aux.getProx();
            }
            return null;
        }
    }

    @Override
    public V put(K key, V value) {
        if(head == null){
            head = new Noh<K,V>(key,value);
            return null;
        }else{
            Noh<K,V> aux = head;
            while(aux != null){
                if(aux.getKey().equals(key)){
                    V auxValue = aux.getValue();
                    aux.setValue(value);
                    return auxValue;
                }
                aux = aux.getProx();
            }
            Noh<K,V> novo = new Noh<K,V>(key,value);
            novo.setProx(head);
            head.setAnt(novo);
            head = novo;
            return null;
        }
    }

    @Override
    public V remove(Object key) {
       Noh <K,V> aux = head;

         if(head == null){
              return null;
            }else{
                while(aux != null){
                    if(aux.getKey().equals(key)){
                        if(aux.getAnt() == null){
                            head = aux.getProx();
                            return aux.getValue();
                        }else{
                            aux.getAnt().setProx(aux.getProx());
                            return aux.getValue();
                        }
                    }
                    aux = aux.getProx();
                }
                return null;
            }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'putAll'");
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'clear'");
    }

    @Override
    public Set<K> keySet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keySet'");
    }

    @Override
    public Collection<V> values() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'values'");
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'entrySet'");
    }


 public void heapSort() {
    // Construir um heap máximo
    buildMaxHeap();

    // Extrair o elemento máximo do heap repetidamente para obter a lista ordenada
    int heapSize = size();
    for (int i = heapSize - 1; i > 0; i--) {
        // Trocar o elemento máximo (raiz do heap) com o último elemento não ordenado
        swap(0, i);

        // Reduzir o tamanho do heap em 1
        heapSize--;

        // Restaurar a propriedade de heap máximo
        maxHeapify(0, heapSize);
    }
}

private void buildMaxHeap() {
    int heapSize = size();

    // Começar a partir do último nó não-folha e ir até a raiz do heap
    for (int i = heapSize / 2 - 1; i >= 0; i--) {
        maxHeapify(i, heapSize);
    }
}

private void maxHeapify(int index, int heapSize) {
    int largest = index; // Inicialmente, considera-se o nó atual como o maior
    int leftChild = 2 * index + 1; // Índice do filho esquerdo
    int rightChild = 2 * index + 2; // Índice do filho direito

    // Verificar se o filho esquerdo é maior que o nó atual
    if (leftChild < heapSize && getNode(leftChild).getKey().compareTo(getNode(largest).getKey()) > 0) {
        largest = leftChild;
    }

    // Verificar se o filho direito é maior que o maior atual
    if (rightChild < heapSize && getNode(rightChild).getKey().compareTo(getNode(largest).getKey()) > 0) {
        largest = rightChild;
    }

    // Se o maior não é o nó atual, trocar os valores e chamar maxHeapify recursivamente no maior
    if (largest != index) {
        swap(index, largest);
        maxHeapify(largest, heapSize);
    }
}

private void swap(int i, int j) {
    Noh<K, V> nodeI = getNode(i);
    Noh<K, V> nodeJ = getNode(j);

    // Trocar as chaves dos nós
    K tempKey = nodeI.getKey();
    nodeI.setKey(nodeJ.getKey());
    nodeJ.setKey(tempKey);

    // Trocar os valores dos nós
    V tempValue = nodeI.getValue();
    nodeI.setValue(nodeJ.getValue());
    nodeJ.setValue(tempValue);
}

private Noh<K, V> getNode(int index) {
    // Percorrer a lista até o nó de índice especificado
    Noh<K, V> node = head;
    for (int i = 0; i < index; i++) {
        node = node.getProx();
    }
    return node;
}

public void mostraMarca(String marca) {
    if (head == null) {
        System.out.println("Lista vazia");
    } else {
        Noh<K, V> aux = head;
        while (aux != null) {
            if (aux.getValue() instanceof Veiculo && ((Veiculo) aux.getValue()).getMarca().equals(marca)) {
                System.out.println(aux.getKey());
            }
            aux = aux.getProx();
        }
    }
}

public void mostrarChassis() {
    Noh<K, V> aux = head;
    boolean foundFord = false;

    while (aux != null) {
        if (aux.getValue() instanceof Veiculo && ((Veiculo) aux.getValue()).getMarca().equals("Ford")) {
            System.out.println(aux.getKey());
            foundFord = true;
        }

        aux = aux.getProx();
    }

    if (!foundFord) {
        System.out.println("Não há chassis da Ford.");
    }
}



public void removerVeiculosAbaixoDoChassi(K chassi) {
    Noh<K, V> current = head;
    Noh<K, V> prev = null;

    while (current != null) {
        if (current.getKey().compareTo(chassi) <= 0) {
            Noh<K, V> next = current.getProx(); // Salva a referência ao próximo nó antes de remover o nó atual

            // Atualiza as referências para remover o nó atual
            if (prev != null) {
                prev.setProx(next);
                if (next != null) {
                    next.setAnt(prev);
                }
            } else {
                head = next;
                if (next != null) {
                    next.setAnt(null);
                }
            }

            current = next; // Atualiza o nó atual para o próximo nó
        } else {
            prev = current;
            current = current.getProx();
        }
    }
}


public void executa() {
    long startTime = System.nanoTime();
    ListaMap<Integer, Veiculo> mapaVeiculo = new ListaMap<>();

    Runtime runtime = Runtime.getRuntime();
    long initialMemory = runtime.totalMemory() - runtime.freeMemory();

    for (int i = 0; i < 100000; i++) {
        Veiculo v = new Veiculo();
        mapaVeiculo.put(v.getChassi(), v);
    }

    mapaVeiculo.removerVeiculosAbaixoDoChassi(202050000);
    mapaVeiculo.heapSort();

    long endTime = System.nanoTime();
    long duration = (endTime - startTime);
    double seconds = duration / 1_000_000_000.0;

    // Verificar o uso de memória após a execução das operações
    long finalMemory = runtime.totalMemory() - runtime.freeMemory();
    long usedMemory = finalMemory - initialMemory;

    long marcaStartTime = System.nanoTime();
    mapaVeiculo.mostraMarca("Ford");
    long marcaEndTime = System.nanoTime();
    long marcaDuration = (marcaEndTime - marcaStartTime);
    double marcaSeconds = marcaDuration / 1_000_000_000.0;
    System.out.println("Tempo para mostrar a marca: " + marcaSeconds + " segundos");

    System.out.println("Fim da lista");
    System.out.println("Tamanho do mapa: " + mapaVeiculo.size());
    System.out.println("Mapa vazio? " + mapaVeiculo.isEmpty());
    System.out.println("Mapa contém a chave 202050000? " + mapaVeiculo.containsKey(202050000));
    System.out.println("Tempo de execução: " + seconds + " segundos");
    System.out.println("Uso de memória total: " + usedMemory + " bytes");
    System.out.println("Uso de memória total: " + bytesToMegabytes(usedMemory) + " MB");
}


private static long bytesToMegabytes(long bytes) {
    return bytes / (1024 * 1024);
}


}
