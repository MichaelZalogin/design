package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesFinder {

    private final Map<FileProperty, ArrayList<Path>> duplicates = new HashMap<>();

    public Map<FileProperty, ArrayList<Path>> getDuplicates() {
        return this.duplicates;
    }

    public static void main(String[] args) throws IOException {
        DuplicatesFinder duplicatesFinder = new DuplicatesFinder();
        duplicatesFinder.searchDuplicates(Path.of("./"));
        duplicatesFinder.showDuplicates();
    }

    public void searchDuplicates(Path file) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(file, duplicatesVisitor);
        duplicatesVisitor.getFilePropertyMap().entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> duplicates.put(entry.getKey(), entry.getValue()));
    }

    public void showDuplicates() {
        duplicates.entrySet().stream().forEach(entry -> {
            System.out.printf("%s - %dB%n", entry.getKey().getName(),
                    entry.getKey().getSize());
            entry.getValue().forEach(System.out::println);
        });
    }
}