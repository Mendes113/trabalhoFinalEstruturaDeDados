import java.util.Collection;
import java.util.Set;

public class ListaMap<K, V> implements Map<K, V> {

    private Noh inicio;
    private Noh fim;

    public ListaMap() {
        this.inicio = null;
        this.fim = null;
    }

    @Override
    public int size() {
        int count = 0;
        Noh current = inicio;

        while (current != null) {
            count++;
            current = current.getProx();
        }

        return count;
    }

    @Override
    public boolean isEmpty() {
        return inicio == null;
    }

    @Override
    public boolean containsKey(Object key) {
        if (key instanceof Integer) {
            int chassi = (int) key;
            Noh current = inicio;

            while (current != null) {
                if (((Veiculo) current.getInfo()).getChassi() == chassi) {
                    return true;
                }
                current = current.getProx();
            }
        }

        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value instanceof String) {
            String marca = (String) value;
            Noh current = inicio;

            while (current != null) {
                if (((Veiculo) current.getInfo()).getMarca().equals(marca)) {
                    return true;
                }
                current = current.getProx();
            }
        }

        return false;
    }

    @Override
    public V get(Object key) {
        if (key instanceof Integer) {
            int chassi = (int) key;
            Noh current = inicio;

            while (current != null) {
                if (((Veiculo) current.getInfo()).getChassi() == chassi) {
                    return (V) current.getInfo();
                }
                current = current.getProx();
            }
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        if (key instanceof Integer) {
            int chassi = (int) key;
            Veiculo veiculo = (Veiculo) value;
            Noh current = inicio;
            Noh previous = null;

            while (current != null) {
                if (((Veiculo) current.getInfo()).getChassi() == chassi) {
                    V oldValue = (V) current.getInfo();
                    current.setInfo(veiculo);
                    return oldValue;
                }
                previous = current;
                current = current.getProx();
            }

            Noh newNoh = new Noh(veiculo);
            if (previous == null) {
                inicio = newNoh;
            } else {
                previous.setProx(newNoh);
            }
        }

        return null;
    }

    @Override
    public V remove(Object key) {
        if (key instanceof Integer) {
            int chassi = (int) key;
            Noh current = inicio;
            Noh previous = null;

            while (current != null) {
                if (((Veiculo) current.getInfo()).getChassi() == chassi) {
                    V oldValue = (V) current.getInfo();
                    if (previous == null) {
                        inicio = current.getProx();
                    } else {
                        previous.setProx(current.getProx());
                    }
                    return oldValue;
                }
                previous = current;
                current = current.getProx();
            }
        }

        return null;
    }

    @Override
    public void clear() {
        inicio = null;
        fim = null;
    }

    @Override
    public Set<K> keySet() {
        // TODO: Implement keySet method
        throw new UnsupportedOperationException("Unimplemented method 'keySet'");
    }

    @Override
    public Collection<V> values() {
        // TODO: Implement values method
        throw new UnsupportedOperationException("Unimplemented method 'values'");
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        // TODO: Implement entrySet method
        throw new UnsupportedOperationException("Unimplemented method 'entrySet'");
    }

}
