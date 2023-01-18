package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.StringJoiner;

public class CSVReader {

    public static void handle(ArgsName argsName) {
        String[] filter = argsName.get("filter").split(",");
        List<String> readInputCSV = readInputCSV(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        String output = argsName.get("out");
        String filterData = filter(readInputCSV, filter, delimiter);
        if ("stdout".equals(output)) {
            System.out.println(filterData);
        }
        if (!"stdout".equals(output)) {
            writeOutputCSV(output, filterData);
        }
    }

    public static String filter(List<String> readInputCSV, String[] filter, String delimiter) {
        List<Integer> indexes = new ArrayList<>();
        StringJoiner joiner = new StringJoiner(delimiter);
        boolean flag = true;
        for (String data : readInputCSV) {
            List<String> columns = Arrays.asList(data.split(delimiter));
            if (flag) {
                for (var criteria : filter) {
                    for (var column : columns) {
                        if (Objects.equals(criteria, column)) {
                            indexes.add(columns.indexOf(column));
                        }
                    }
                }
            }
            for (var index : indexes) {
                joiner.add(columns.get(index));
            }
            joiner.add(System.lineSeparator());
            flag = false;
        }
        return joiner.toString();
    }

    public static List<String> readInputCSV(String path) {
        List<String> inputCSV = new ArrayList<>();
        File file = new File(path);
        try (Scanner scanner = new Scanner(file).useDelimiter(System.lineSeparator())) {
            while (scanner.hasNext()) {
                inputCSV.add(scanner.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputCSV;
    }

    public static void writeOutputCSV(String path, String outputCSV) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(path)))) {
            writer.print(outputCSV);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArgsName validateArg(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        ArgsName argsName = ArgsName.of(args);
        File firstArg = new File(argsName.get("path"));
        if (!firstArg.exists()) {
            throw new IllegalArgumentException(
                    String.format("Does not exist %s", firstArg.getAbsoluteFile()));
        }
        if (!firstArg.isFile()) {
            throw new IllegalArgumentException(
                    String.format("Second arg %s is not file", firstArg.getAbsoluteFile()));
        }
        return argsName;
    }

    public static void main(String[] args) {
        ArgsName argsName = validateArg(args);
        handle(argsName);
    }
}