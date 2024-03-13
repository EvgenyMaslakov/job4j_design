package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ZipVisitor extends SimpleFileVisitor<Path> {

    public Predicate<Path> condition;
    List<Path> result = new ArrayList<>();

    public ZipVisitor(Predicate<Path> condition) {
        this.condition = condition;
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        if (!condition.test(file)) {
            result.add(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getPaths() {
        return result;
    }
}