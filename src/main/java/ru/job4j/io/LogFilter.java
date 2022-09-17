package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 0.4. BufferedOutputStream [#252490]
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

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String s : log) {
                out.println(s);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при вводе данных в файл!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        save(log, "404.txt");

    }
}
