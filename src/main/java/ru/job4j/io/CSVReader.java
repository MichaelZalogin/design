package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.StringJoiner;

/**
 * Класс позволяет работать с файлами расширения CSV и им подобным.
 * На вход принимает данные, обрабатывает их с помощью заданных фильтров
 * и записывает новый результирующий файл.
 * Параметры запуска:
 * -path=input.csv -delimiter=;  -out=output.csv -filter=name,age
 *
 * @author Michael
 * @version 1.0
 */

public class CSVReader {

    /**
     * Лист для хранения строк считанных из файла.
     */
    private final List<String> inputCSV = new ArrayList<>();

    /**
     * Лист для хранения строк, предназначенных для записи в файл.
     */
    private final List<String> outputCSV = new ArrayList<>();

    /**
     * В поле хранится количество столбцов передаваемой таблицы.
     */
    private int fieldCount;

    /**
     * @param argsName экземпляр класса который принимает массив параметров, осуществляет валидацию
     *                 и разбивает их на пары ключ-значение.
     *                 Метод последовательно выполняет чтение, получение данных по заданным фильтрам
     *                 и дальнейшую запись в указанный файл.
     */
    public void handle(ArgsName argsName) throws IOException {
        this.readInputCSV(argsName.get("path"));
        this.filter(argsName.get("filter"), argsName.get("delimiter"));
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

    private void filter(String filterArgs, String delimiter) {
        String[] args = this.separator(inputCSV, delimiter);
        String[] args2 = filterArgs.split(",");
        int index = -1;
        for (int i = 0; i < args2.length; i++) {
            for (int j = 0; j < args.length; j++) {
                if (Objects.equals(args[j], args2[i])) {
                    index = i;
                }
            }
            if (index == -1) {
                throw new IllegalArgumentException(
                        String.format("Field %s not exist", args2[i]));
            }
            System.out.println(args[index + fieldCount]);
//            outputCSV.add(arg);
//            System.out.println(inputCSV);
//            System.out.println(inputCSV.indexOf(args));
//            outputCSV.add(inputCSV.get());
//        }
        }
    }

    private String[] separator(List<String> ab, String delimiter) {
        StringJoiner stringJoiner = new StringJoiner(delimiter);
        String[] array;
        this.fieldCount = inputCSV.get(0).split(delimiter).length;
        for (int i = 0; i < ab.size(); i++) {
            stringJoiner.add(ab.get(i));
        }
        array = stringJoiner.toString().split(delimiter);
        return array;
    }

    /**
     * @param path путь к указанному файлу.
     *             Считывание входящего файла .CSV в лист. В качестве шаблона
     *             используется имволы перехода на новую строку и пробел.
     */
    public void readInputCSV(String path) throws FileNotFoundException {
        File file = new File(path);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                inputCSV.add(scanner.next());
            }
        }
    }

    /**
     * @param path путь к указанному файлу.
     *             Запись конечных данных из листа в файл .CSV
     */
    public void writeOutputCSV(String path) throws IOException {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(path))) {
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