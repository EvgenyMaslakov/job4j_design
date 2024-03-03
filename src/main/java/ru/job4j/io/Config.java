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
     * isBlank - возвращает true только если в строке есть только пробел(-ы)
     * (и нет других символов) или строка пустая ("") или имеет значение null.
     * Метод startsWith() в Java имеет два варианта и проверяет начинается ли строка
     * с указанного префикса, начиная с указанного индекса или с начала (по умолчанию).
     * import java.io.BufferedReader;
     * import java.io.FileReader;
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                if (!line.isBlank() && !line.startsWith("#")) {
                    String[] keyAndValue = line.split("=", 2);
                    if (keyAndValue.length != 2
                            || keyAndValue[0].isEmpty()
                            || keyAndValue[1].isEmpty()) {
                        throw new IllegalArgumentException("Нарушение шаблона в строке: " + line);
                    }
                    values.put(keyAndValue[0], keyAndValue[1]);
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
            throw new NoSuchElementException("Отсутствует ключ " + key);
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
        System.out.println(new Config("data/app.properties"));
    }
}
