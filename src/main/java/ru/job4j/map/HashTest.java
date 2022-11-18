package ru.job4j.map;

public class HashTest {
    public static void main(String[] args) {
        int b;
        int h = 5;
        System.out.println(binary(h));
        b = h >>> 16;
        System.out.println(binary(b));
        h = h ^ h >>> 16;
        System.out.println(binary(h));
    }

    public static String binary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            sb.append(num % 2 == 0 ? 0 : 1);
            sb.append((i + 1) % 8 == 0 ? " " : "");
            num /= 2;
        }
        return sb.reverse().toString();
    }
}
