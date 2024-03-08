package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Класс ищет дубликаты файлов в подпапках.
 * Дубликаты – это два файла с одинаковым именем и размером.
 * Результат выводится на консоль
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Map<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        if (Files.isRegularFile(file)) {  // файл является обычным файлом?
            FileProperty fileProperti = new FileProperty(attributes.size(), file.getFileName().toString()); // создаем объект FileProperty
            // с указанием размера и имени файла
            if (!map.keySet().contains(fileProperti)) {
                map.put(fileProperti, new ArrayList<>()); // помещаем в мапу FileProperty и добавляем в лист его абсолютный путь
                map.get(fileProperti).add(file.toAbsolutePath());
            } else {
                map.get(fileProperti).add(file.toAbsolutePath());
            }
        }
        return FileVisitResult.CONTINUE;
    }

    public void printDublicate() {
        for (FileProperty  key : map.keySet()) {
            if (map.get(key).size() > 1) {
                System.out.println(key.getName());
                for (Path path : map.get(key)) {
                    System.out.println(path);
                }
            }
        }
    }
}
