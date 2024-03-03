package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.nio.file.Path;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AbuseTest {
    /**
     * 1. Создаем временный файл source
     * 2. Записываем предложения с запрещенными словами во временный файл source
     * 3. Создаем временный файл target
     * 4. Используем метод Abuse.drop с временными файлами source, target и списком запрещенных слов
     * 5. Считываем результат из временного файла target
     * 6. Сверяем результат с ожиданием
     * @param tempDir Путь к временным файлам
     * @throws IOException ошибка ввода-вывода
     */
    @Test
    void drop(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("hello foolish dude");
            output.println("java job4j php");
        }
        File target  = tempDir.resolve("target.txt").toFile();
        Abuse.drop(source.getAbsolutePath(), target.getAbsolutePath(), List.of("foolish", "php"));

        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat("hello dude java job4j ").hasToString(result.toString());
    }
}