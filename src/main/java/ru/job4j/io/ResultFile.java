package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ResultFile {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io\\result.txt")))) {
            out.println("Hello, world!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}