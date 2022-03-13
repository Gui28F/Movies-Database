package dataStructure;

import dataStructure.exceptions.NoSuchElementException;

class HashTableIteratorClass<K,V> implements Iterator<Entry<K,V>> {
    protected Dictionary<K,V>[] table;
    protected Iterator<Entry<K, V>> currentList;
    protected int currentTableIndex;

    public HashTableIteratorClass(Dictionary<K, V>[] table) {
        this.table = table;
        rewind();
    }

    @Override
    public boolean hasNext() {
        return currentList.hasNext();
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        Entry<K, V> toReturn = currentList.next();
        if (toReturn == null)
            throw new NoSuchElementException();
        if (!currentList.hasNext()) {
            nextIndex();
            if (currentTableIndex != table.length)
                currentList = table[currentTableIndex].iterator();
        }
        return toReturn;
    }

    /**
     * update the next table index with elements
     */
    protected void nextIndex() {
        currentTableIndex++;
        updateIndex();
    }

    /**
     * search the next table index with elements
     */
    protected void updateIndex(){
        while (currentTableIndex < table.length && table[currentTableIndex].isEmpty())
            currentTableIndex++;
    }
    @Override
    public void rewind() {
        currentTableIndex = 0;
        if (table.length > 0) {
            currentList = table[0].iterator();
            updateIndex();
            if (currentTableIndex != table.length)
                currentList = table[currentTableIndex].iterator();
        } else
            currentList = null;
    }
}
