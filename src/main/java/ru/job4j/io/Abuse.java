package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * Класс удаляет из файла запрещенные слова.
 */
public class Abuse {
    /**
     * Метод drop принимает два файла и список слов для удаления.
     *
     * Результат работы этой программы будет новый файл.
     * @param source исходный файл с запрещенными словами
     * @param target новый файл без запрещенных слов
     * @param words список запрещенных слов
     * @throws IOException ошибка ввода-вывода
     */
    public static void drop(String source, String target, List<String> words) throws IOException {
        try (BufferedReader input = new BufferedReader(new FileReader(source));
             PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            input.lines()
                    .flatMap(line -> Stream.of(line.split("\\s+")))
                    .filter(word -> !words.contains(word)).map(word -> word + " ")
                    .forEach(output::print);
        }
    }
}
