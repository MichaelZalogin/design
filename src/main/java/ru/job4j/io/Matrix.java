package ru.job4j.io;

import java.util.StringJoiner;

public class Matrix {
    public static String multiple(int size) {
        int[][] array = new int[size][size];
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                array[i][j] = (i + 1) * (j + 1);
                stringJoiner.add(String.valueOf(array[i][j]));
            }
            stringJoiner.add(System.lineSeparator());
        }
        String matrix = stringJoiner.toString();
        return matrix;
    }
}