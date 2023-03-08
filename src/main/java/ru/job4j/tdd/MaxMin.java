package ru.job4j.tdd;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {

    public static <T> T max(List<T> value, Comparator<T> comparator) {
        return getValue(value, (t, e) -> comparator.compare(t, e) > 0);
    }

    public static <T> T min(List<T> value, Comparator<T> comparator) {
        return getValue(value, (t, e) -> comparator.compare(t, e) < 0);
    }

    private static <T> T getValue(List<T> value, BiPredicate<T, T> predicate) {
        T result = value.get(0);
        for (T t : value) {
            if (predicate.test(t, result)) {
                result = t;
            }
        }
        return result;
    }
}