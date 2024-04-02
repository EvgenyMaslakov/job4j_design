package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Что такое Socket?
 * Создаем серверный сокет, привязанный к указанному порту.
 * Программа ждет подключений по указанному порту, работа программы продолжится только после подключения клиента.
 * После успешного подключения, метод возвращает объект Socket, который используется для взаимодействия с клиентом.
 * С помощью объекта Socket программа может получить входной поток и может отправить данные в выходной поток.
 * Записываем ответ.
 * В программе читается весь входной поток.
 * После чтения отправляем ответ окончательно.
 * Сервер работает, пока его принудительно не закроют.
 * Метод ассеpt принимает один запрос от клиента, чтобы отправить второй, программа должна снова получить объект socket.
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {

                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        if (string.contains("msg=Bye")) {
                            System.out.println("Работа сервера завершена");
                            break;
                        }
                        System.out.println(string);
                    }
                    output.flush();
                }
            }
        }
    }
}
