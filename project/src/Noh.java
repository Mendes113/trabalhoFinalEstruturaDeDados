public class Noh<K, V> {

    private K key;
    private V value;
    private Noh<K, V> ant;
    private Noh<K, V> prox;

    public Noh(K key, V value) {
        this.key = key;
        this.value = value;
        this.ant = null;
        this.prox = null;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setKey(K k) {
        this.key = k;
    }
    public void setValue(V v) {
        this.value = v;
    }

    public Noh<K, V> getProx() {
        return prox;
    }

    public void setProx(Noh<K, V> n) {
        this.prox = n;
    }

    public Noh<K, V> getAnt() {
        return ant;
    }

    public void setAnt(Noh<K, V> n) {
        this.ant = n;
    }
}
