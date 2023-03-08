package ru.job4j.tdd;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        T result = value.get(0);
        for (T t : value) {
            if (comparator.compare(t, result) > 0) {
                result = t;
            }
        }
        return result;
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return null;
    }
}