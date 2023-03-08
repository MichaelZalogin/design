package ru.job4j.tdd;

import org.junit.jupiter.api.Test;
import ru.job4j.gc.GCTypeDemo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaxMinTest {

    @Test
    void thenSearchMaxValue() {
        List<String> strings = new ArrayList<>();
        List<GCTypeDemo> gcTypes = List.of(
                new GCTypeDemo("lin3e1"),
                new GCTypeDemo("2line2"),
                new GCTypeDemo("line3"),
                new GCTypeDemo("l3ine4"),
                new GCTypeDemo("l3ine4"));
        String expected = "2line2";
        String result = MaxMin.max(gcTypes, (o2, o1) -> o1.getField().compareTo(o2.getField()))
                .getField();
        assertEquals(expected, result);
    }

    @Test
    void thenSearchMinValue() {
        List<String> strings = new ArrayList<>();
        List<GCTypeDemo> gcTypes = List.of(
                new GCTypeDemo("lin3e1"),
                new GCTypeDemo("2line2"),
                new GCTypeDemo("line3"),
                new GCTypeDemo("l3ine4"),
                new GCTypeDemo("l3ine4"));
        String expected = "line3";
        String result = MaxMin.min(gcTypes, (o2, o1) -> o1.getField().compareTo(o2.getField()))
                .getField();
        assertEquals(expected, result);
    }
}