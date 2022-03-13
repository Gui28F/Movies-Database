package dataStructure;

import dataStructure.exceptions.NoSuchElementException;


public class BSTKeyOrderIterator<K, V> implements Iterator<Entry<K, V>> {
    private Stack<BSTNode<K, V>> tempSave;
    private BSTNode<K, V> root;
    private BSTNode<K, V> current;

    public BSTKeyOrderIterator(BSTNode<K, V> root) {
        this.root = root;
        this.rewind();
    }

    @Override
    public boolean hasNext() {
        return !this.tempSave.isEmpty();
    }

    @Override
    public Entry<K, V> next() throws NoSuchElementException {
        if (!hasNext())
            throw new NoSuchElementException();
        BSTNode<K, V> toReturn = this.tempSave.pop();
        this.current = toReturn.getRight();
        this.goLeft();
        return toReturn.getEntry();
    }

    @Override
    public void rewind() {
        this.tempSave = new StackInList<>();
        this.current = root;
        this.goLeft();
    }

    /**
     * go the left while it´s possible in the tree
     */
    private void goLeft() {
        while (current != null) {
            this.tempSave.push(current);
            this.current = this.current.getLeft();
        }
    }
}
