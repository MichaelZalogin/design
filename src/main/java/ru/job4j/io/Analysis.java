package ru.job4j.io;

import java.io.*;
import java.util.Objects;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            boolean flag = true;
            String[] trimLine;
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                trimLine = line.split(" ", 2);
                if (flag && (Objects.equals(trimLine[0], "500")
                        || Objects.equals(trimLine[0], "400"))) {
                    writer.write((trimLine[1]));
                    writer.write(";");
                    flag = false;
                }
                if (!flag && (Objects.equals(trimLine[0], "200")
                        || Objects.equals(trimLine[0], "300"))) {
                    writer.write((trimLine[1]));
                    writer.write(";");
                    flag = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Analysis().unavailable(".\\src\\main\\java\\ru\\job4j\\io\\server.log",
                ".\\src\\main\\java\\ru\\job4j\\io\\target.log");
    }
}