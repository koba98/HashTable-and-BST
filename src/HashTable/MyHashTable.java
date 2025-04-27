package HashTable;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyHashTable<K, V> implements Iterable<MyHashTable.HashNode<K, V>> {

    public static class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        chainArray = new HashNode[M];
        size = 0;
    }

    public MyHashTable(int M) {
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }

    public void put(K key, V value) {
        int bucketIndex = hash(key);
        HashNode<K, V> newNode = new HashNode<>(key, value);

        HashNode<K, V> head = chainArray[bucketIndex];
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        newNode.next = chainArray[bucketIndex];
        chainArray[bucketIndex] = newNode;
        size++;
    }

    public V get(K key) {
        int bucketIndex = hash(key);
        HashNode<K, V> head = chainArray[bucketIndex];

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {
        int bucketIndex = hash(key);
        HashNode<K, V> head = chainArray[bucketIndex];
        HashNode<K, V> prev = null;

        while (head != null) {
            if (head.key.equals(key)) {
                if (prev != null) {
                    prev.next = head.next;
                } else {
                    chainArray[bucketIndex] = head.next;
                }
                size--;
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        return null;
    }

    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return true;
                }
                head = head.next;
            }
        }
        return false;
    }

    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> head = chainArray[i];
            while (head != null) {
                if (head.value.equals(value)) {
                    return head.key;
                }
                head = head.next;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<HashNode<K, V>> iterator() {
        return new Iterator<HashNode<K, V>>() {
            private int bucketIndex = 0;
            private HashNode<K, V> current = null;

            @Override
            public boolean hasNext() {
                while (bucketIndex < M) {
                    if (current != null) {
                        return true;
                    }
                    current = chainArray[bucketIndex++];
                }
                return false;
            }

            @Override
            public HashNode<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                HashNode<K, V> node = current;
                current = current.next;
                return node;
            }
        };
    }
}
