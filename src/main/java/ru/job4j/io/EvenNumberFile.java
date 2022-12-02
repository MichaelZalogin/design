package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream readFile =
                     new FileInputStream("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io\\even.txt")) {
            StringBuilder sb = new StringBuilder();
            int read;
            while ((read = readFile.read()) != -1) {
                sb.append((char) read);
            }
            String[] text = sb.toString().split(System.lineSeparator());
            for (String str : text) {
                int i = Integer.parseInt(str);
                if (i % 2 == 0) {
                    System.out.println(i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}