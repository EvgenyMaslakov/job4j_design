package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Утилита для архивации папки
 * Техническое задание.
 * 1. При запуске указывается папка, которую мы хотим архивировать, например: c:\project\job4j\
 * 2. В качестве ключа передаётся расширение файлов, которые не нужно включать в архив.
 * 3. Архив должен сохранять структуру проекта. То есть содержать подпапки.
 * 4. Запуск проекта.
 */
public class Zip {

    /**
     * Метод упаковывает список файлов
     * Для поиска и фильтрации файлов используется класс Search
     * @param sources список файлов, которые нужно заархивировать
     * @param target место, куда нужно заархивировать (папка)
     */
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(path.toString()))) {
                    zip.putNextEntry(new ZipEntry(path.toString()));
                    zip.write(output.readAllBytes());
                } catch (Exception e) {
                  e.printStackTrace();
                }
            }
       } catch (Exception e) {
            e.printStackTrace();
       }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        ZipVisitor zipVisitor = new ZipVisitor(condition);
        Files.walkFileTree(root, zipVisitor);
        return zipVisitor.getPaths();
    }

    /**
     * Метод упаковывает один файл
     * @param source файл, который нужно заархивировать
     * @param target место, куда нужно заархивировать (папка)
     */
    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validation(String[] parameters) throws IOException {
        if (parameters.length < 3) {
            throw new IllegalArgumentException("Program arguments are empty or incomplete");
        }
        if (!Files.isDirectory(Paths.get(parameters[0]))) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        if (parameters[1].lastIndexOf(".") != 0) {
            throw new IllegalArgumentException("The file extension is unspecified or incorrect");
        }
        if (!parameters[2].endsWith(".zip")) {
            throw new IllegalArgumentException("Target file does not have .zip permissions");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        String[] parameters = {argsName.get("d"), argsName.get("e"), argsName.get("o")};
        validation(parameters);
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        Zip zip1 = new Zip();
        List<Path> pathList = search(Paths.get(argsName.get("d")), path -> path.toFile()
                .getName()
                .endsWith(argsName.get("e")));
        zip1.packFiles(pathList, Paths.get(argsName.get("o")).toFile());
    }

}
