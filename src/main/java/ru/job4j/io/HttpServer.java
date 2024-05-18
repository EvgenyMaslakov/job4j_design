package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServer {
    /**
     * ожидаем подключения
     * для подключившегося клиента открываем потоки чтения и записи
     * ждем первой строки запроса
     * считываем и печатаем все что было отправлено клиентом
     * отправляем ответ
     * по окончанию выполнения блока try-with-resources потоки,
     * а вместе с ними и соединение будут закрыты
     * @param args
     */
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started!");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                try (BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                     PrintWriter output = new PrintWriter(socket.getOutputStream())) {
                    while (!input.ready()) {
                        System.out.println();
                        while (input.ready()) {
                            System.out.println(input.readLine());
                        }
                        output.println("HTTP/1.1 200 OK");
                        output.println("Content-Type: text/html; charset=utf-8");
                        output.println();
                        output.println("<p>Привет всем!</p>");
                        output.flush();
                        System.out.println("Client disconnected!");
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
