package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        List<Integer> newList = new ArrayList<>(Arrays.asList(1, 3, 4, 5, 8));
        Predicate<Integer> filter = (s -> s <= 3);
        ListUtils.removeIf(newList, filter);
        assertThat(newList).hasSize(3).containsSequence(4, 5, 8);
    }

    @Test
    void whenReplaceIf() {
        List<Integer> newList = new ArrayList<>(Arrays.asList(1, 3, 4, 5, 8, 2));
        Predicate<Integer> filter = (s -> s <= 3);
        ListUtils.replaceIf(newList, filter, 10);
        assertThat(newList).hasSize(6).containsSequence(10, 10, 4, 5, 8, 10);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 4, 5, 8, 10));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 5, 7, 8));
        ListUtils.removeAll(list, elements);
        assertThat(list).hasSize(3).containsSequence(3, 4, 10);
    }
}