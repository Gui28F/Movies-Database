package dataStructure;

import java.util.Objects;

public class EntryClass<K, V> implements Entry<K, V> {

    protected K key;
    protected V value;

    public EntryClass(K key, V value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntryClass<?, ?> that = (EntryClass<?, ?>) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public K getKey() {
        return this.key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public void setKey(K key) {
        this.key = key;
    }

    @Override
    public void setValue(V value) {
        this.value = value;
    }

}
