package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class Multiple {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("Multiple.txt")) {
            for (int i = 1; i <= 9; i++) {
                for (int j = 1; j <= 9; j++) {
                    System.out.println();
                    String s = i + "*" + j + "=" + i * j + " ";
                    out.write(s.getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при вводе данных в файл!");
            e.printStackTrace();
        }
    }
}
