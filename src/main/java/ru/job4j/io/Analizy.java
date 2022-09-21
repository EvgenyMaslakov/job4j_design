package ru.job4j.io;

import java.io.*;

/**
 * 2. Анализ доступности сервера. [#859]
 */
public class Analizy {
    /**
     * Метод unavailable() должен находить диапазоны, когда сервер не работал
     * Сервер не работал, если status = 400 или 500.
     * Результат анализа нужно записать в файл target.
     * Формат файла: начала периода;конец периода;
     * Диапазоном считается последовательность статусов 400 и 500
     * @param source - путь к файлу лога
     * @param target - имя путь к файлу результата анализа
     */
    public void unavailable(String source, String target) {

        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(
                     new BufferedOutputStream(new FileOutputStream(target)))) {
            Boolean errorServer = true;
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                    if (errorServer && (line.startsWith("400") || line.startsWith("500"))) {
                        out.print(line.split(" ")[1] + ";");
                        errorServer = false;
                    }
                    if (!errorServer && (line.startsWith("200") || line.startsWith("300"))) {
                        out.print(line.split(" ")[1] + ";" + System.lineSeparator());
                        errorServer = true;
                    }
                }
        } catch (IOException e) {
            System.out.println("Ошибка при выводе/выводе данных");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy test1 = new Analizy();
        test1.unavailable("source1.csv", "target1.cvs");
        Analizy test2 = new Analizy();
        test2.unavailable("source2.csv", "target2.cvs");
    }
}
