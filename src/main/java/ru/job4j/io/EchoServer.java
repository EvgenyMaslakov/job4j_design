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
 * msg=Hello > Hello
 * msg=Exit > Завершить работу сервера.
 * msg=Bye > Завершить работу сервера.
 * msg=Any > What.
 *
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                boolean cls = false;
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        String string = input.readLine();
                        if (string.contains("msg=Hello")) {
                            output.write("Hello.".getBytes());
                        } else if (string.contains("msg=Exit")) {
                            cls = true;
                            output.write("Завершить работу сервера.".getBytes());
                        } else if (string.contains("msg=Bye")) {
                            cls = true;
                            output.write("Завершить работу сервера.".getBytes());
                        } else {
                            output.write("What.".getBytes());
                        }
                        System.out.println(string);
                    if (cls) {
                        server.close();
                        break;
                    }
                    output.flush();
                }
            }
        }
    }
}
