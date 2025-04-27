package test;

import BST.MyBST;

public class MyBSTTest {
    public static void main(String[] args){
        MyBST<Integer, String> tree = new MyBST<>();
        tree.put(5, "Five");
        tree.put(2, "Two");
        tree.put(8, "Eight");
        tree.put(1, "One");
        tree.put(3, "Three");

        System.out.println("Size: " + tree.size());

        System.out.println("Get key 2: " + tree.get(2));
        System.out.println("Get key 8: " + tree.get(8));

        tree.delete(2);
        System.out.println("Size after deleting 2: " + tree.size());
        System.out.println("Get key 2 after deletion: " + tree.get(2));

        System.out.println("\nIterating over BST (in-order):");
        for (var node : tree) {
            System.out.println("Key: " + node.getKey() + ", Value: " + node.getValue());
        }
    }
}
