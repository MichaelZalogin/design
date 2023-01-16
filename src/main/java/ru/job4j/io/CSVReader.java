package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.StringJoiner;

public class CSVReader {

    private final List<String> inputCSV = new ArrayList<>();
    private final List<String> outputCSV = new ArrayList<>();

    public void handle(ArgsName argsName) throws IOException {
        this.readInputCSV(argsName.get("path"), argsName.get("delimiter"));
        this.filter(argsName.get("filter"));
        this.writeOutputCSV(argsName.get("out"));
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

    private void filter(String filterArgs) {
        String[] args = filterArgs.split(",");
        for (String arg : args) {
            int index = inputCSV.indexOf(arg);
            if (index == -1) {
                throw new IllegalArgumentException(
                        String.format("Field %s not exist", arg));
            }
            outputCSV.add(arg);
//            System.out.println(inputCSV);
//            System.out.println(inputCSV.indexOf(args));
//            outputCSV.add(inputCSV.get());
        }
    }

    public void readInputCSV(String path, String delimiter) throws FileNotFoundException {
        File file = new File(path);
        try (Scanner scanner = new Scanner(file).useDelimiter(delimiter)) {
            while (scanner.hasNext()) {
                inputCSV.add(scanner.next());
            }
//            for (String arg : inputCSV) {
//                System.out.println(arg);
//            }
            System.out.println(inputCSV.get(4));
        }
    }

    public void writeOutputCSV(String file) throws IOException {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file))) {
            for (String data : outputCSV) {
                br.write(data);
                br.write(" ");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        CSVReader csvReader = new CSVReader();
        ArgsName argsName = validateArg(args);
        csvReader.handle(argsName);
    }
}