import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ListaMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Noh<K, V> inicio;
    private Noh<K, V> fim;
    private LinkedList<Entry<K, V>> entries;

    public ListaMap() {
        this.inicio = null;
        this.fim = null;
        entries = new LinkedList<>();
    }

    @Override
    public int size() {
        int count = 0;
        Noh<K, V> current = inicio;

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
        if (key != null) {
            K searchKey = (K) key;
            Noh<K, V> current = inicio;

            while (current != null) {
                if (searchKey.equals(current.getInfo().getKey())) {
                    return true;
                }
                current = current.getProx();
            }
        }

        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        if (value != null) {
            V searchValue = (V) value;
            Noh<K, V> current = inicio;

            while (current != null) {
                if (searchValue.equals(current.getInfo().getValue())) {
                    return true;
                }
                current = current.getProx();
            }
        }

        return false;
    }

    @Override
    public V get(Object key) {
        if (key != null) {
            K searchKey = (K) key;
            Noh<K, V> current = inicio;

            while (current != null) {
                if (searchKey.equals(current.getInfo().getKey())) {
                    return current.getInfo().getValue();
                }
                current = current.getProx();
            }
        }

        return null;
    }

    @Override
    public V put(K key, V value) {
        if (key != null) {
            Entry<K, V> newEntry = new Entry<>(key, value);
            Noh<K, V> current = inicio;
            Noh<K, V> previous = null;

            while (current != null) {
                if (key.equals(current.getInfo().getKey())) {
                    V oldValue = current.getInfo().getValue();
                    current.getInfo().setValue(value);
                    return oldValue;
                }
                previous = current;
                current = current.getProx();
            }

            Noh<K, V> newNoh = new Noh<>(newEntry);
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
        if (key != null) {
            K searchKey = (K) key;
            Noh<K, V> current = inicio;
            Noh<K, V> previous = null;

            while (current != null) {
                if (searchKey.equals(current.getInfo().getKey())) {
                    V oldValue = current.getInfo().getValue();
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

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
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
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'putAll'");
    }

    public void quickSort() {
        quickSortRec(inicio, fim);
    }

    private void quickSortRec(Noh<K, V> left, Noh<K, V> right) {
        if (left != null && right != null && left != right && left != right.getProx()) {
            Noh<K, V> pivot = partition(left, right);
            quickSortRec(left, pivot.getAnt());
            quickSortRec(pivot.getProx(), right);
        }
    }

    private Noh<K, V> partition(Noh<K, V> left, Noh<K, V> right) {
        K pivotKey = right.getInfo().getKey();
        Noh<K, V> i = left.getAnt();

        for (Noh<K, V> j = left; j != right; j = j.getProx()) {
            if (j.getInfo().getKey().compareTo(pivotKey) <= 0) {
                i = (i == null) ? left : i.getProx();
                swapEntries(i, j);
            }
        }

        i = (i == null) ? left : i.getProx();
        swapEntries(i, right);

        return i;
    }

    private void swapEntries(Noh<K, V> noh1, Noh<K, V> noh2) {
        Entry<K, V> entry1 = noh1.getInfo();
        Entry<K, V> entry2 = noh2.getInfo();

        K tempKey = entry1.getKey();
        V tempValue = entry1.getValue();

        entry1 = new Entry<>(entry2.getKey(), entry2.getValue());
        entry2 = new Entry<>(tempKey, tempValue);

        noh1.setInfo(entry1);
        noh2.setInfo(entry2);
    }

    public void removerVeiculosAbaixoDoChassi(int chassiLimite) {
        Iterator<Entry<K, V>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            K key = entry.getKey();
            if (key instanceof Integer && ((Integer) key).compareTo(chassiLimite) < 0) {
                iterator.remove();
            }
        }
    }

}