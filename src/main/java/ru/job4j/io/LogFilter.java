package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 0.3. BufferedReader. [#252489]
 */
public class LogFilter {
    /**
     * Метод filter должен прочитать файл и вернуть строки, где предпоследнее значение - это 404.
     * @param file исходный файл
     * @return возвращает строки, где предпоследнее значение - это 404
     */
    public List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] words = line.split(" ");
                if (words[words.length - 2].equals("404")) {
                    rsl.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при выводе данных из файла!");
            e.printStackTrace();
        }

        return rsl;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);

    }
}
