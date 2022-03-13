package dataStructure;

/**
 * Separate Chaining Hash table implementation
 *
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value
 * @author AED  Team
 * @version 1.0
 */

public class SepChainHashTable<K extends Comparable<K>, V>
        extends HashTable<K, V> {
    /**
     * Serial Version UID of the Class.
     */
    static final long serialVersionUID = 0L;
    /**
     * growth factor of rehash
     */
    static final int GROWTH_FACTOR = 2;
    /**
     * The array of dictionaries.
     */
    protected Dictionary<K, V>[] table;


    /**
     * Constructor of an empty separate chaining hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     *
     * @param capacity defines the table capacity.
     */
    public SepChainHashTable(int capacity) {
        setUp(capacity);
    }

    /**
     * create an empty separate chaining hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    protected void setUp(int capacity) {
        //int arraySize = HashTable.nextPrime((int) (1.1 * capacity));TODO
        int arraySize = capacity;
        table = (Dictionary<K, V>[]) new Dictionary[arraySize];
        for (int i = 0; i < arraySize; i++)
            table[i] = new OrderedDoubleList<K, V>();
        maxSize = capacity;
        currentSize = 0;
    }

    public SepChainHashTable() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Returns the hash value of the specified key.
     *
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    @Override
    public V find(K key) {
        return table[this.hash(key)].find(key);
    }

    @Override
    public V insert(K key, V value) {
        if (this.isFull())
            this.rehash();
        int pos = hash(key);
        V valueToReturn = table[pos].insert(key, value);
        if (valueToReturn == null)
            currentSize++;
        return valueToReturn;
    }

    /**
     * creates a new table with more capacity and
     * copy the elements in old table to the new table
     */
    protected void rehash() {
        Iterator<Entry<K, V>> entryIterator = this.iterator();
        setUp(maxSize * GROWTH_FACTOR);
        while (entryIterator.hasNext()) {
            Entry<K, V> entry = entryIterator.next();
            K key = entry.getKey();
            V value = entry.getValue();
            this.insert(key, value);
        }
    }

    @Override
    public V remove(K key) {
        int pos = hash(key);
        V toReturn = table[pos].remove(key);
        if (toReturn != null)
            currentSize--;
        return toReturn;


    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashTableIteratorClass<>(table);
    }
}
































