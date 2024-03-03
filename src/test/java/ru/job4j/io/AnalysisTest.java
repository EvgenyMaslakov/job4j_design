package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {
    /**
     * 1. Создаем временный файл source
     * 2. Записываем статус работы сервера во временный файл source
     * 3. Создаем временный файл target
     * 4. Используем метод unavailable с временными файлами source, target
     * 5. Считываем результат из временного файла target
     * 6. Сверяем результат с ожиданием
     * @param tempDir Путь к временным файлам
     * @throws IOException ошибка ввода-вывода
     */
    @Test
    void unavailable1(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("200 10:59:01");
            output.println("500 11:01:02");
            output.println("200 11:02:02");
            output.println("500 12:01:02");
            output.println("200 12:02:02");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(lin -> result.append(lin + System.lineSeparator()));
        }
        assertThat("10:57:01;10:59:01;" + System.lineSeparator()
                + "11:01:02;11:02:02;" + System.lineSeparator()
                + "12:01:02;12:02:02;" + System.lineSeparator())
                .hasToString(result.toString());
    }

    @Test
    void unavailable2(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("200 10:56:01");
            output.println("500 10:57:01");
            output.println("400 10:58:01");
            output.println("500 10:59:01");
            output.println("400 11:01:02");
            output.println("200 11:02:02");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(lin -> result.append(lin + System.lineSeparator()));
        }
        assertThat("10:57:01;11:02:02;" + System.lineSeparator()).hasToString(result.toString());
    }
}