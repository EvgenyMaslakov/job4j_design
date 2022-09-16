package ru.job4j.io;

import java.io.FileOutputStream;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
