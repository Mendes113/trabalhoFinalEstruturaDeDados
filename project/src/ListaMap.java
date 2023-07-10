import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListaMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Noh inicio;
    private Noh fim;
       private LinkedList<Entry<K, V>> entries;
    public ListaMap() {
        this.inicio = null;
        this.fim = null;
        entries = new LinkedList<>();
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
        if (key != null) {
            K searchKey = (K) key;
            Noh current = inicio;

            while (current != null) {
                if (searchKey.equals(((Entry<K, V>) current.getInfo()).getKey())) {
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
            Noh current = inicio;

            while (current != null) {
                if (searchValue.equals(((Entry<K, V>) current.getInfo()).getValue())) {
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
            Noh current = inicio;

            while (current != null) {
                if (searchKey.equals(((Entry<K, V>) current.getInfo()).getKey())) {
                    return ((Entry<K, V>) current.getInfo()).getValue();
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
            Noh current = inicio;
            Noh previous = null;

            while (current != null) {
                if (key.equals(((Entry<K, V>) current.getInfo()).getKey())) {
                    V oldValue = ((Entry<K, V>) current.getInfo()).getValue();
                    ((Entry<K, V>) current.getInfo()).setValue(value);
                    return oldValue;
                }
                previous = current;
                current = current.getProx();
            }

            Noh newNoh = new Noh(newEntry);
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
            Noh current = inicio;
            Noh previous = null;

            while (current != null) {
                if (searchKey.equals(((Entry<K, V>) current.getInfo()).getKey())) {
                    V oldValue = ((Entry<K, V>) current.getInfo()).getValue();
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

    private void quickSortRec(Noh left, Noh right) {
        if (left != null && right != null && left != right && left != right.getProx()) {
            Noh pivot = partition(left, right);
            quickSortRec(left, pivot.getAnt());
            quickSortRec(pivot.getProx(), right);
        }
    }

    private Noh partition(Noh left, Noh right) {
        K pivotKey = ((Entry<K, V>) right.getInfo()).getKey();
        Noh i = left.getAnt();

        for (Noh j = left; j != right; j = j.getProx()) {
            if (((Entry<K, V>) j.getInfo()).getKey().compareTo(pivotKey) <= 0) {
                i = (i == null) ? left : i.getProx();
                swapEntries(i, j);
            }
        }

        i = (i == null) ? left : i.getProx();
        swapEntries(i, right);

        return i;
    }

private void swapEntries(Noh noh1, Noh noh2) {
    Entry<K, V> entry1 = (Entry<K, V>) noh1.getInfo();
    Entry<K, V> entry2 = (Entry<K, V>) noh2.getInfo();

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
