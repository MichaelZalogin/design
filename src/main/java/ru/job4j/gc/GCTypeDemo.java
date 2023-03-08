package ru.job4j.gc;

import java.util.Objects;
import java.util.Random;

public class GCTypeDemo {
    private String field;

    public GCTypeDemo(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int length = 100;
        String[] data = new String[1_000_000];
        for (int i = 0; ; i = (i + 1) % data.length) {
            System.out.println("PID процесса: " + ProcessHandle.current().pid());
            data[i] = String.valueOf(
                    (char) random.nextInt(255)
            ).repeat(length);
        }
    }
}
