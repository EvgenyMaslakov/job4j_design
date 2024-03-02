package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 0.4. BufferedOutputStream [#252490]
 */
public class LogFilter {

    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    /**
     * Метод filter должен прочитать файл и вернуть строки, где предпоследнее значение - это 404.
     * @param file исходный файл
     * @return возвращает строки, где предпоследнее значение - это 404
     */
    public List<String> filter() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] words = line.split(" ");
                if ("404".equals(words[words.length - 2])) {
                    rsl.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при выводе данных из файла!");
            e.printStackTrace();
        }

        return rsl;
    }

    /**
     * Метод должен записывать результат фильтрации в файл
     * @param log отфильтрованный список строк
     * @param file файл, в который записываем результат фильтрации
     */
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
        LogFilter logFilter = new LogFilter("data/log.txt");
        List<String> log = logFilter.filter();
        for (String l : log) {
            System.out.println(l);
        }
        save(log, "data/404.txt");
    }
}
