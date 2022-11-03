package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size;

    private Node<E> first;

    private Node<E> last;

    private int modCount;

    @Override
    public void add(E value) {
        Node<E> tmp = last;
        Node<E> newNode = new Node<>(null, value);
        last = newNode;
        if (tmp == null) {
            first = last;
        } else {
            tmp.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        if (index == size) {
            return last.item;
        }
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            Node<E> nodeIter = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return nodeIter != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> result = nodeIter;
                nodeIter = nodeIter.next;
                return result.item;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(Node<E> next, E item) {
            this.item = item;
            this.next = next;
        }
    }
}