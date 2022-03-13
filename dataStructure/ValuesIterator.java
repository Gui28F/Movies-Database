package dataStructure;

/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 **/

import dataStructure.exceptions.NoSuchElementException;
// Iterator that only returns the entry values
public class ValuesIterator<K, V> implements Iterator<V> {
    private Iterator<Entry<K, V>> iterator;

    public ValuesIterator(Iterator<Entry<K, V>> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public V next() throws NoSuchElementException {
        return this.iterator.next().getValue();
    }

    @Override
    public void rewind() {
        this.iterator.rewind();
    }
}
