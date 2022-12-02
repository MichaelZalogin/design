package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> stringList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                String[] as = line.split(" ");
                if (Objects.equals(as[as.length - 2], "404")) {
                    stringList.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringList;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter print = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            if (log != null) {
                for (String element : log) {
                    print.println(element);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io\\log.txt");
        save(log, "C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io\\404.txt");
    }
}