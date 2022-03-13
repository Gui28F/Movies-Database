package dataStructure;

import dataStructure.exceptions.NoSuchElementException;

public class BreadthFirstIterator<K, V> implements Iterator<Entry<K, V>> {
    private BSTNode<K, V> root;
    private Queue<BSTNode<K, V>> tempSave;

    public BreadthFirstIterator(BSTNode<K, V> root) {
        this.root = root;
        this.rewind();
    }

    @Override
    public boolean hasNext() {
        return !tempSave.isEmpty();
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if(!hasNext())
            throw new NoSuchElementException();
        BSTNode<K,V> node = tempSave.dequeue();
        if(node.getLeft() != null)
            tempSave.enqueue(node.getLeft());
        if(node.getRight() != null)
            tempSave.enqueue(node.getRight());
        return node.getEntry();
    }

    @Override
    public void rewind() {
        this.tempSave = new QueueInList<>();
        tempSave.enqueue(root);
    }
}
