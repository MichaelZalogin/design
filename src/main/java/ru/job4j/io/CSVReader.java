package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.StringJoiner;
import java.util.function.Predicate;

public class CSVReader {

    private final List<String> stringsCSV = new ArrayList<>();

    public static void handle(ArgsName argsName) {

    }

    private Predicate<String> filter(String answer) {
        return null;
    }

    private static ArgsName validateArg(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        ArgsName argsName = ArgsName.of(args);
        File firstArg = new File(argsName.get("path"));
        if (!firstArg.exists()) {
            throw new IllegalArgumentException(
                    String.format("Not exist %s", firstArg.getAbsoluteFile()));
        }
        if (!firstArg.isFile()) {
            throw new IllegalArgumentException(
                    String.format("Second arg %s is not file", firstArg.getAbsoluteFile()));
        }
        return argsName;
    }

//    public void readCSV(String file) {
//        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                stringsCSV.add(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void readCSV(String file) throws FileNotFoundException {
        File file1 = new File(file);
        try (Scanner scanner = new Scanner(file1).useDelimiter(";")) {
            while (scanner.hasNextLine()) {
                stringsCSV.add(scanner.nextLine());
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
//        ArgsName argsName = CSVReader.validate(args);
//        handle(argsName);
        CSVReader csvReader = new CSVReader();
        csvReader.readCSV("C:\\projects\\job4j_design\\data\\table.csv");
    }
}