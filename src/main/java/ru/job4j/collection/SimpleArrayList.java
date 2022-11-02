package ru.job4j.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            container = grow();
        }
        container[size] = value;
        size++;
    }

    private T[] grow() {
        T[] copy = (T[]) new Object[container.length * 2];
        System.arraycopy(container, 0, copy, 0, container.length);
        return copy;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        modCount++;
        T copy;
        int tmp = size - 1;
        if (tmp > index) {
            System.arraycopy(container, 0, container, 0, tmp);
        }
        return container[index];
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            //
            @Override
            public boolean hasNext() {
                return true;
            }

            //
            @Override
            public T next() {
                return null;
            }
//
//        };
        };
    }
}