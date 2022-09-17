package ru.job4j.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 0.4. BufferedOutputStream [#252490]
 */
public class ResultFile {
    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("result.txt")
                ))) {
            out.println("Hello, world!");
        } catch (IOException e) {
            System.out.println("Ошибка при вводе данных в файл!");
            e.printStackTrace();
        }
    }
}
