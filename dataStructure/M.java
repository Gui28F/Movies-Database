package dataStructure;

public class M {
    public static void main(String[] args) {
       /* Dictionary<Integer,Character>a = new SepChainHashTable<>(7);
        a.insert(7,'X');
        a.insert(11,'B');
        a.insert(18,'H');
        a.insert(12,'5');
        a.remove(0);
        a.insert(18,'A');
        a.insert(5,'A');
        a.remove(11);

        Iterator<Entry<Integer,Character>> it = a.iterator();
        while(it.hasNext()) {
            Entry<Integer, Character> n = it.next();
            System.out.println("["+n.getKey()+", " + n.getValue()+"]");
        }

        */


        BinarySearchTree<Integer, Character> b = new BinarySearchTree<>();
        b.insert(8, 'A');

        b.insert(5, 'A');

        b.insert(49, '1');
        b.insert(1, 'A');
        b.insert(7, 'A');
        //System.out.println( b.insertNode(b.root, new BSTNode<>(18,'A')));
        Iterator<Entry<Integer, Character>> it1 = b.iterator();
        while (it1.hasNext())
            System.out.println(it1.next().getKey());
        System.out.println("______________________________________");

        BinarySearchTree<Integer, Boolean> bool = genBooleanTree(b.root);
        Iterator<Entry<Integer, Boolean>> it4 = bool.iterator();
        while (it4.hasNext()) {
            Entry<Integer,Boolean> n = it4.next();
            System.out.println(n.getKey()+ " "+n.getValue());
        }
        System.out.println();
/*
        List<Integer> c = new DoubleList<>();
        c.addLast(5);
        c.addLast(13);
        c.addLast(-4);
        c.addLast(0);
        int sum = 0;
        Iterator<Integer> it2 = c.iterator();
        System.out.println(sum(it2));
        System.out.println(it2.hasNext());


 */

    }


    private static  BinarySearchTree<Integer,Boolean> rec(BinarySearchTree<Integer,Boolean> newN,BSTNode<Integer, Character> node){
        if(node == null)
            return newN;
        if(newN == null)
            newN = new BinarySearchTree<>();
        if(node.getKey().compareTo((int)node.getValue())<0)
            newN.insert(node.getKey(),true);
        else
            newN.insert(node.getKey(),false);

        rec(newN,  node.getRight());
        rec(newN,  node.getLeft());
        return newN;
    }

    private static BinarySearchTree<Integer,Boolean> genBooleanTree(BSTNode<Integer, Character> node){
        BinarySearchTree<Integer,Boolean> newRoot = rec(null, node);
        return newRoot;
    }

    private static int sum(Iterator<Integer> a) {
        int sum = 0;
        while (a.hasNext())
            sum += a.next();
        return sum;
    }

}
