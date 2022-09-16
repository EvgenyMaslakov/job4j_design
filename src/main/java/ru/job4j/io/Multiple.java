package ru.job4j.io;

import java.io.FileOutputStream;

public class Multiple {
    public static void main(String[] args) {
        System.out.println("1 * 1 = 1");
        System.out.println("1 * 2 = 2");
        System.out.println("1 * 3 = 3");
        System.out.println("1 * 4 = 4");
        System.out.println("1 * 5 = 5");
        System.out.println("1 * 6 = 6");
        System.out.println("1 * 7 = 7");
        System.out.println("1 * 8 = 8");
        System.out.println("1 * 9 = 9");
        try (FileOutputStream out = new FileOutputStream("Multiple.txt")) {
            out.write("1 * 1 = 1".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("1 * 2 = 2".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("1 * 3 = 3".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("1 * 4 = 4".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("1 * 5 = 5".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("1 * 6 = 6".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("1 * 7 = 7".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("1 * 8 = 8".getBytes());
            out.write(System.lineSeparator().getBytes());
            out.write("1 * 9 = 9".getBytes());
            out.write(System.lineSeparator().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
