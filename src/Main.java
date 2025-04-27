
import HashTable.MyHashTable;
import test.MyTestingClass;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(100);
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            int f1 = random.nextInt(1000);
            int f2 = random.nextInt(1000);
            String f3 = generateRandomString(random, 5);
            MyTestingClass key = new MyTestingClass(f1, f2, f3);
            Student value = new Student("Student" + i, i);

            table.put(key, value);
        }

        for (int i = 0; i < 100; i++) {
            int count = 0;
            MyHashTable.HashNode<MyTestingClass, Student> head = table.getBucketHead(i);
            while (head != null) {
                count++;
                head = head.getNext();
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }

    }

    private static String generateRandomString(Random random, int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

}
