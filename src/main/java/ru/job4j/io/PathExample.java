package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {
    public static void main(String[] args) throws IOException {
        Path directory = Path.of("path/paths");
        Files.createDirectories(directory);
        Path path = Path.of("path/paths/path.txt");
        Files.createFile(path);
    }
}
