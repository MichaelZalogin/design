package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> optional = findBy(parent);
        boolean rsl = optional.isPresent() && findBy(child).isEmpty();
        if (rsl) {
            Node<E> tmp = optional.get();
            tmp.children.add(new Node<>(child));
        }
        return rsl;
    }

    @Override
    public boolean isBinary() {
        boolean rsl = true;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.children.size() > 2) {
                rsl = false;
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

//    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
//    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}