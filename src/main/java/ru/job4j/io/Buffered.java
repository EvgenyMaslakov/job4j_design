package ru.job4j.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Buffered {
    public static void main(String[] args) {
        try (BufferedInputStream input1 = new BufferedInputStream(new FileInputStream("data/input.txt"));
             BufferedInputStream input2 = new BufferedInputStream(new FileInputStream("data/newData.txt"));
             BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("data/output.txt", true))) {
            output.write(input1.readAllBytes());
            output.write(input2.readAllBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
