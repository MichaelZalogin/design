package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    private static void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        validateArg(args);
    }

    private static void validateArg(String[] args) {
        File firstArg = new File(args[0]);
        File secondArg = new File(args[1]);
        if (!firstArg.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", firstArg.getAbsoluteFile()));
        }
        if (!firstArg.isDirectory()) {
            throw new IllegalArgumentException(
                    String.format("Not directory %s", firstArg.getAbsoluteFile()));
        }
        if (!secondArg.isFile()) {
            throw new IllegalArgumentException(
                    String.format("Second arg %s is not file", secondArg.getAbsoluteFile()));
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}