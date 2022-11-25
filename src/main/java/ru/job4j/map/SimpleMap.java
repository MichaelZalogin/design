package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if (count == capacity * LOAD_FACTOR) {
            expand();
        }
        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
            result = true;
        }
        return result;
    }

    private int getIndex(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newHashTable = new MapEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            MapEntry<K, V> mapEntry = table[i];
            if (mapEntry != null) {
                newHashTable[getIndex(mapEntry.key)] = mapEntry;
            }
        }
        table = newHashTable;
    }

    @Override
    public V get(K key) {
        V result = null;
        int index = indexFor(hash(Objects.hashCode(key)));
        if (compareKey(key)) {
                result = table[index].value;
            }
        return result;
    }

    private boolean compareKey(K key) {
            int index = getIndex(key);
            return table[index] != null
                    && hash(Objects.hashCode(key)) == hash(Objects.hashCode(table[index].key))
                    && Objects.equals(key, table[index].key);
    }

    @Override
    public boolean remove(K key) {
        boolean result = false;
        int index = getIndex(key);
        if (compareKey(key)) {
                table[index] = null;
                modCount++;
                count--;
                result = true;
            }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int cursor = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (cursor < capacity && table[cursor] == null) {
                    cursor++;
                }
                return cursor < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[cursor++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
