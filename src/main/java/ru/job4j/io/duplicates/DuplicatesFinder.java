package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        showDuplicates(Path.of("./"));
    }

    public static void showDuplicates(Path file) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(file, duplicatesVisitor);
        duplicatesVisitor.getFilePropertyMap().entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> {
                    System.out.printf("%s - %dB%n", entry.getKey().getName(),
                            entry.getKey().getSize());
                    entry.getValue().forEach(System.out::println);
                });
    }
}
