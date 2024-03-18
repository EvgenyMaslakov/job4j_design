package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Консольный чат
 */
public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String chat;
    private final String botAnswers;

    /**
     * Конструктор
     * @param chat имя файла, в который будет записан весь диалог между ботом и пользователем
     * @param botAnswers имя файла в котором находятся строки с ответами, которые будет использовать бот
     */
    public ConsoleChat(String chat, String botAnswers) {
        this.chat = chat;
        this.botAnswers = botAnswers;
    }

    /**
     * Метод содержит логику чата
     * - пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
     * - программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
     * - если пользователь вводит слово «продолжить», программа снова начинает отвечать.
     * - при вводе слова «закончить» программа прекращает работу.
     */
    public void run() {
        List<String> listBotAnswers = readPhrases();
        List<String> log = new ArrayList<>();
        boolean getAnswers = true;
        Scanner input = new Scanner(System.in);
        String consoleText = "";
        while (!OUT.equals(consoleText)) {
            consoleText = input.nextLine();
            log.add(consoleText);
            if (STOP.equals(consoleText) || OUT.equals(consoleText)) {
                getAnswers = false;
            } else if (CONTINUE.equals(consoleText)) {
                getAnswers = true;
            }
            if (getAnswers) {
                int answer = new Random().nextInt(listBotAnswers.size());
                System.out.println(listBotAnswers.get(answer));
                log.add(listBotAnswers.get(answer));
            }
        }
        saveLog(log);
    }

    /**
     * Метод читает фразы из файла
     * @return возвращает список строк прочитанных из файла
     */
    private List<String> readPhrases() {
        List<String> listBotAnswers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                listBotAnswers.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listBotAnswers;
    }

    /**
     * Метод сохраняет лог чата в файл
     * - запись диалога, включая слова-команды стоп/продолжить/закончить должны быть записаны в текстовый лог.
     * @param log лог чата
     */
    private void saveLog(List<String> log)  {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(chat)
                ))) {
            for (String str : log) {
                out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("./data/chat.txt", "./data/botAnswers.txt");
        consoleChat.run();
    }
}
