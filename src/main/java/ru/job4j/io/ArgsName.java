package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Arg are not contained");
        }
        return values.get(key);
    }

    private static void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Args is null. Run with \"-key = value\" arg");
        }
        for (String argument : args) {
            if (!argument.startsWith("-") || !argument.contains("=")
                    || argument.indexOf("=") == argument.length() - 1) {
                throw new IllegalArgumentException("the args must start with \"-\" and contains \"=\"");
            }
        }
    }

    private void parse(String[] args) {
        validate(args);
        for (String argument : args) {
            String[] lines = argument.split("=", 2);
            values.put(lines[0], lines[1]);
            String[] tmp = argument.substring(1).split("=", 2);
            this.values.put(tmp[0], tmp[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}