import java.util.Collection;
import java.util.Set;

public class ArvoreMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Node<K, V> root;

    // Implementação dos métodos da interface Map

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean containsKey(Object key) {
        K searchKey = (K) key;
        return search(root, searchKey) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public V get(Object key) {
        K searchKey = (K) key;
        Node<K, V> node = search(root, searchKey);
        return node != null ? node.value : null;
    }

    @Override
    public V put(K key, V value) {
        root = insert(root, key, value);
        return null; // Não suporta retorno do valor anterior
    }

    @Override
    public V remove(Object key) {
        K searchKey = (K) key;
        Node<K, V> node = search(root, searchKey);
        if (node != null) {
            root = remove(root, searchKey);
            return node.value;
        }
        return null;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public Collection<V> values() {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        throw new UnsupportedOperationException("Operation not supported");
    }

    // Implementação dos métodos da Árvore AVL

    private Node<K, V> insert(Node<K, V> node, K key, V value) {
        if (node == null) {
            return new Node<>(key, value);
        }

        int compareResult = key.compareTo(node.key);

        if (compareResult < 0) {
            node.left = insert(node.left, key, value);
        } else if (compareResult > 0) {
            node.right = insert(node.right, key, value);
        } else {
            // Chave já existe, atualiza o valor
            node.value = value;
            return node;
        }

        // Atualiza a altura do nó atual
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Verifica o fator de balanceamento e realiza as rotações se necessário
        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1) {
            if (key.compareTo(node.left.key) < 0) {
                // Caso LL: Rotação simples à direita
                return rotateRight(node);
            } else {
                // Caso LR: Rotação dupla: esquerda-direita
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        } else if (balanceFactor < -1) {
            if (key.compareTo(node.right.key) > 0) {
                // Caso RR: Rotação simples à esquerda
                return rotateLeft(node);
            } else {
                // Caso RL: Rotação dupla: direita-esquerda
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }

        int compareResult = key.compareTo(node.key);

        if (compareResult < 0) {
            node.left = remove(node.left, key);
        } else if (compareResult > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                // Nó com 0 ou 1 filho, retorna o filho não nulo (ou null se não tiver filhos)
                node = (node.left != null) ? node.left : node.right;
            } else {
                // Nó com 2 filhos, encontra o sucessor em ordem e o substitui
                Node<K, V> successor = findSuccessor(node.right);
                node.key = successor.key;
                node.value = successor.value;
                node.right = remove(node.right, successor.key);
            }
        }

        if (node != null) {
            // Atualiza a altura do nó atual
            node.height = 1 + Math.max(height(node.left), height(node.right));

            // Verifica o fator de balanceamento e realiza as rotações se necessário
            int balanceFactor = getBalanceFactor(node);

            if (balanceFactor > 1) {
                if (getBalanceFactor(node.left) >= 0) {
                    // Caso LL: Rotação simples à direita
                    return rotateRight(node);
                } else {
                    // Caso LR: Rotação dupla: esquerda-direita
                    node.left = rotateLeft(node.left);
                    return rotateRight(node);
                }
            } else if (balanceFactor < -1) {
                if (getBalanceFactor(node.right) <= 0) {
                    // Caso RR: Rotação simples à esquerda
                    return rotateLeft(node);
                } else {
                    // Caso RL: Rotação dupla: direita-esquerda
                    node.right = rotateRight(node.right);
                    return rotateLeft(node);
                }
            }
        }

        return node;
    }

    private Node<K, V> findSuccessor(Node<K, V> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node<K, V> search(Node<K, V> node, K key) {
        if (node == null || key.equals(node.key)) {
            return node;
        }

        int compareResult = key.compareTo(node.key);

        if (compareResult < 0) {
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    private int height(Node<K, V> node) {
        return node != null ? node.height : 0;
    }

    private int getBalanceFactor(Node<K, V> node) {
        return node != null ? height(node.left) - height(node.right) : 0;
    }

    private Node<K, V> rotateLeft(Node<K, V> node) {
        Node<K, V> newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));
        return newRoot;
    }

    private Node<K, V> rotateRight(Node<K, V> node) {
        Node<K, V> newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));
        return newRoot;
    }

    private int size(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

public void removerVeiculosAbaixoDoChassi(int chassiLimite) {
    root = removerVeiculosAbaixoDoChassi(root, chassiLimite);
}
private Node<K, V> removerVeiculosAbaixoDoChassi(Node<K, V> node, int chassiLimite) {
    if (node == null) {
        return null;
    }

    int compareResult = ((Integer) node.key).compareTo(chassiLimite);

    if (compareResult < 0) {
        node = remove(node, node.key);
    }

    if (node != null) { // Add null check here
        if (node.left != null) {
            node.left = removerVeiculosAbaixoDoChassi(node.left, chassiLimite);
        }

        if (node.right != null) {
            node.right = removerVeiculosAbaixoDoChassi(node.right, chassiLimite);
        }
    }

    return node;
}


public void mostraMarcaModelo(String modelo) {
    mostraMarcaModelo(root, modelo);
}

private void mostraMarcaModelo(Node<K, V> node, String modelo) {
    if (node == null) {
        return;
    }

    mostraMarcaModelo(node.left, modelo);

    if (node.value instanceof Veiculo) {
        Veiculo veiculo = (Veiculo) node.value;
        if (veiculo.getMarca().equals(modelo)) {
            System.out.println(node.key + " " + veiculo);
        }
    }

    mostraMarcaModelo(node.right, modelo);
}

public void executa() {
    // Aqui começa mapa arvore
    long startTime = System.nanoTime();
    ArvoreMap<Integer, Veiculo> mapaVeiculo = new ArvoreMap<>();

    Runtime runtime = Runtime.getRuntime();
    long initialMemory = runtime.totalMemory() - runtime.freeMemory();

    for (int i = 0; i < 100000; i++) {
        Veiculo v = new Veiculo();
        mapaVeiculo.put(v.getChassi(), v);
    }
    
    mapaVeiculo.removerVeiculosAbaixoDoChassi(202050000);

    long endTime = System.nanoTime();
    long duration = (endTime - startTime);
    System.out.println("Tempo de execução: " + duration + " nanosegundos");
    double seconds = duration / 1_000_000_000.0;

    // Verificar o uso de memória após a execução das operações
    long finalMemory = runtime.totalMemory() - runtime.freeMemory();
    long usedMemory = finalMemory - initialMemory;
    System.out.println("Tamanho do mapa: " + mapaVeiculo.size());
    System.out.println("Mapa vazio? " + mapaVeiculo.isEmpty());
    System.out.println("Mapa contém a chave 202050000? " + mapaVeiculo.containsKey(202050000));

    long marcaStartTime = System.nanoTime();
    mapaVeiculo.mostraMarcaModelo("Ford");
    long marcaEndTime = System.nanoTime();
    long marcaDuration = (marcaEndTime - marcaStartTime);
    System.out.println("Tempo para mostrar a marca: " + marcaDuration + " nanosegundos");
    double marcaSeconds = marcaDuration / 1_000_000_000.0;
    System.out.println("Tempo para mostrar a marca: " + marcaSeconds + " segundos");

    System.out.println("Uso de memória total: " + usedMemory + " bytes");
    System.out.println("Uso de memória total: " + bytesToMegabytes(usedMemory) + " MB");
    System.out.println("Tempo de execução: " + seconds + " segundos");
}


private static long bytesToMegabytes(long bytes) {
    return bytes / (1024 * 1024);
}


}

