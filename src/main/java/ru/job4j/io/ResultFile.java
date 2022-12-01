package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out =
                     new FileOutputStream("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io\\result.txt")) {
            out.write(Matrix.multiple(9).getBytes());
            out.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}