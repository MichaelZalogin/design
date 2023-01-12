package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, ArrayList<Path>> filePropertyMap = new HashMap<>();

    public Map<FileProperty, ArrayList<Path>> getFilePropertyMap() {
        return this.filePropertyMap;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
        filePropertyMap.putIfAbsent(fileProperty, new ArrayList<>());
        filePropertyMap.get(fileProperty).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }
}
