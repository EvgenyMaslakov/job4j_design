package ru.job4j.io;

import java.io.*;

/**
 * 2. Анализ доступности сервера. [#859]
 */
public class Analysis {
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
            Boolean changeStatus = true;
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                    if (changeStatus && (line.startsWith("400") || line.startsWith("500"))) {
                        out.print(line.split(" ")[1] + ";");
                        changeStatus = false;
                    }
                    if (!changeStatus && (line.startsWith("200") || line.startsWith("300"))) {
                        out.print(line.split(" ")[1] + ";" + System.lineSeparator());
                        changeStatus = true;
                    }
                }
        } catch (IOException e) {
            System.out.println("Ошибка при выводе/выводе данных");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis test1 = new Analysis();
        test1.unavailable("data/source1.csv", "data/target1.cvs");
        Analysis test2 = new Analysis();
        test2.unavailable("data/source2.csv", "data/target2.cvs");
    }
}
