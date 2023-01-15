package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean working = true;
        boolean flag = true;
        List<String> log = new ArrayList<>();
        List<String> botAnswer = readPhrases();
        Random randomAnswer = new Random();
        Scanner scanner = new Scanner(System.in);
        while (working) {
            String answer = scanner.nextLine();
            log.add(answer);
            if (STOP.equals(answer)) {
                flag = false;
            }
            if (CONTINUE.equals(answer)) {
                flag = true;
            }
            if (OUT.equals(answer)) {
                working = false;
            }
            if (flag) {
                String logLine = botAnswer.get(randomAnswer.nextInt(0, botAnswer.size()));
                System.out.println(logLine);
                log.add(logLine);
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> botAnswer = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.botAnswers,
                Charset.forName("UTF-8")))) {
            String st;
            while ((st = br.readLine()) != null) {
                botAnswer.add(st);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return botAnswer;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.path,
                Charset.forName("UTF-8")))) {
            for (String line : log) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("C:\\projects\\job4j_design\\data\\log1.txt",
                "C:\\projects\\job4j_design\\data\\botAnswers.txt");
        cc.run();
    }
}