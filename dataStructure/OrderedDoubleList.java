package dataStructure;

import dataStructure.exceptions.EmptyListException;
/**
 * @author Guilherme Fernandes 60045 gc.fernandes@campus.fct.unl.pt
 * @author Martim Gamboa 61700 m.gamboa@campus.fct.unl.pt
 * ordered linked list
 */
class OrderedDoubleList<K extends Comparable<K>, V> implements Dictionary<K, V> {
    /**
     * Serial Version UID of the Class
     */
    static final long serialVersionUID = 0L;

    /**
     * Node at the head of the list.
     */
    protected DoubleListNode<Entry<K, V>> head;

    /**
     * Node at the tail of the list.
     */
    protected DoubleListNode<Entry<K, V>> tail;

    /**
     * Number of elements in the list.
     */
    protected int currentSize;

    /**
     * Constructor of an empty double linked list.
     * head and tail are initialized as null.
     * currentSize is initialized as 0.
     */
    public OrderedDoubleList() {
        head = null;
        tail = null;
        currentSize = 0;
    }


    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    /**
     * Returns the first element of the list.
     *
     * @return first element in the list
     * @throws EmptyListException - if size() == 0
     */
    protected Entry<K, V> getFirst() throws EmptyListException {
        if (this.isEmpty())
            throw new EmptyListException();

        return head.getElement();
    }

    /**
     * Returns the last element of the list.
     *
     * @return last element in the list
     * @throws EmptyListException - if size() == 0
     */
    protected Entry<K, V> getLast() throws EmptyListException {
        if (this.isEmpty())
            throw new EmptyListException();
        return tail.getElement();
    }

    @Override
    public V find(K key) {
        DoubleListNode<Entry<K, V>> entry = head;
        while (entry != null && !entry.getElement().getKey().equals(key))
            entry = entry.getNext();
        if (entry == null)
            return null;
        return entry.getElement().getValue();
    }

    /**
     * Inserts the specified element at the last position in the list.
     *
     * @param entry to be inserted
     */
    protected void addLast(Entry<K, V> entry) {
        DoubleListNode<Entry<K, V>> node = new DoubleListNode<>(entry, tail, null);
        if (this.isEmpty())
            head = node;
        else
            tail.setNext(node);
        tail = node;
        currentSize++;
    }

    /**
     * Removes the last node in the list.
     * Pre-condition: the list is not empty.
     */
    protected void removeLastNode() {
        tail = tail.getPrevious();
        if (tail == null)
            head = null;
        else
            tail.setNext(null);
        currentSize--;
    }

    /**
     * Removes the first node in the list.
     * Pre-condition: the list is not empty.
     */
    protected void removeFirstNode() {
        head = head.getNext();
        if (head == null)
            tail = null;
        else
            head.setPrevious(null);
        currentSize--;
    }

    /**
     * Removes and returns the element at the first position in the list.
     *
     * @return element removed from the first position of the list
     * @throws EmptyListException - if size() == 0
     */
    protected Entry<K, V> removeFirst() throws EmptyListException {
        if (this.isEmpty())
            throw new EmptyListException();
        Entry<K, V> entry = getFirst();
        this.removeFirstNode();
        return entry;
    }

    /**
     * Removes and returns the element at the last position in the list.
     *
     * @return element removed from the last position of the list
     * @throws EmptyListException - if size() == 0
     */
    protected Entry<K, V> removeLast() throws EmptyListException {
        if (this.isEmpty())
            throw new EmptyListException();

        Entry<K, V> entry = getLast();
        this.removeLastNode();
        return entry;
    }

    /**
     * removes <code>node</code> from the list
     *
     * @param node to remove
     * @return removed entry
     */
    protected Entry<K, V> removeNode(DoubleListNode<Entry<K, V>> node) {
        if (node.getPrevious() == null)
            return removeFirst();
        else if (node.getNext() == null)
            return removeLast();
        else {
            removeMiddleNode(node);
            return node.getElement();
        }

    }

    /**
     * Inserts the specified element at the first position in the list.
     *
     * @param element to be inserted
     */
    protected void addFirst(Entry<K, V> element) {
        DoubleListNode<Entry<K, V>> newNode = new DoubleListNode<Entry<K, V>>(element, null, head);
        if (this.isEmpty())
            tail = newNode;
        else
            head.setPrevious(newNode);
        head = newNode;
        currentSize++;
    }

    /**
     * Inserts the specified element at the specified node in the list.
     *
     * @param nodeToConnect - node to connect new element
     * @param element       - element to be inserted at middle position
     */
    protected void addMiddle(DoubleListNode<Entry<K, V>> nodeToConnect, Entry<K, V> element) {
        DoubleListNode<Entry<K, V>> prevNode = nodeToConnect.getPrevious();
        DoubleListNode<Entry<K, V>> newNode = new DoubleListNode<Entry<K, V>>(element, prevNode, nodeToConnect);
        prevNode.setNext(newNode);
        nodeToConnect.setPrevious(newNode);
        currentSize++;
    }

    /**
     * returns the node with key <code>key</code>
     *
     * @param key key we are looking for
     * @return the element if found it or null if don´t find it
     */
    protected DoubleListNode<Entry<K, V>> searchPos(K key) {
        DoubleListNode<Entry<K, V>> entry = head;
        while (entry != null && entry.getElement().getKey().compareTo(key) < 0)
            entry = entry.getNext();
        return entry;
    }

    @Override
    public V insert(K key, V value) {
        DoubleListNode<Entry<K, V>> entry = searchPos(key);
        Entry<K, V> removedEntry = null;
        if (entry == null)
            addLast(new EntryClass<>(key, value));
        else if (entry.getElement().getKey().compareTo(key) == 0) {
            removedEntry = entry.getElement();
            entry.setElement(new EntryClass<>(key, value));
        } else if (entry == head)
            addFirst(new EntryClass<>(key, value));
        else
            addMiddle(entry, new EntryClass<>(key, value));

        if (removedEntry == null)
            return null;
        return removedEntry.getValue();
    }

    /**
     * Removes the specified node from the list.
     * Pre-condition: the node is neither the head nor the tail of the list.
     *
     * @param node - middle node to be removed
     */
    protected void removeMiddleNode(DoubleListNode<Entry<K, V>> node) {
        DoubleListNode<Entry<K, V>> prevNode = node.getPrevious();
        DoubleListNode<Entry<K, V>> nextNode = node.getNext();
        prevNode.setNext(nextNode);
        nextNode.setPrevious(prevNode);
        currentSize--;
    }

    @Override
    public V remove(K key) {
        DoubleListNode<Entry<K, V>> entry = searchPos(key);
        if (entry == null)
            return null;
        Entry<K, V> removedEntry = null;
        if (entry.getElement().getKey().equals(key))
            removedEntry = removeNode(entry);
        if (removedEntry == null)
            return null;
        return removedEntry.getValue();
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new DoubleListIterator<>(head, tail);
    }
}
