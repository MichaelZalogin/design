package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<File> searchFiles(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths().stream().map(Path::toFile).collect(Collectors.toList());
    }

    public void packFilesWithArgs(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Args is null. Run with \"-key = value\" arg");
        }
        ArgsName jvm = ArgsName.of(args);
        if (!new File(jvm.get("d")).isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", jvm.get("d")));
        }
        List<File> sources = searchFiles(Paths.get(jvm.get("d")),
                filter -> !filter.toFile().getName().endsWith(jvm.get("e")));
        this.packFiles(sources, new File(jvm.get("o")));
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        zip.packFilesWithArgs(args);
    }
}