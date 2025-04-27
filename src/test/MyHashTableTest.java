package test;

import HashTable.MyHashTable;

public class MyHashTableTest {
    public static void main(String[] args) {
        MyHashTable<Integer, String> table  = new MyHashTable<>();

        table.put(1, "One");
        table.put(2, "Two");
        table.put(3, "Three");

        System.out.println("Get key 2: " + table.get(2));

        table.remove(2);
        System.out.println("Get key 2 after remove: " + table.get(2));

        table.put(2, "TwoAgain");
        System.out.println("Get key 2 after re-put: " + table.get(2));

        System.out.println("Contains 'Three'? " + table.contains("Three"));

        System.out.println("\nIterating over table:");
        for (var entry : table) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}
