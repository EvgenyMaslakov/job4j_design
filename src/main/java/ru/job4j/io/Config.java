package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

/**
 * 1. Читаем файл конфигурации [#858]
 */
public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Метод load() - должен считать все ключи в карту values.
     * Важно в файле могут быть пустые строки и комментарии их нужно пропускать.
     * Для считывания файлов нужно использовать
     * import java.io.BufferedReader;
     * import java.io.FileReader;
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                if (line != null && line.length() != 0 && !"#".contains(line)) {
                    String[] keyEndValue = line.split("=", 2);
                    if (keyEndValue[0].isEmpty() || keyEndValue[1].isEmpty()) {
                        throw new IllegalArgumentException("Нарушение шаблона");
                    }
                    values.put(keyEndValue[0], keyEndValue[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при выводе данных из файла!");
            e.printStackTrace();
        }
    }

    /**
     * метод value(String key) должен возвращать значение ключа
     * @param key ключ
     * @return возвращает значение по ключу
     */
    public String value(String key) {
        if (!values.containsKey(key)) {
            throw new NoSuchElementException("Ключ отсутствует");
        }
         return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            System.out.println("Ошибка при выводе данных из файла!");
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
