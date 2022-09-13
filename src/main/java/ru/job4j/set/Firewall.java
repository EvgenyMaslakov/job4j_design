package ru.job4j.set;

import java.util.Set;

public class Firewall {
    public static String checkName(String s, Set<String> words) {
        String rsl = "Вы сделали правильный выбор!";
        String[] str = s.split(" ");
        for (String sWord : str) {
            for (String word : words) {
                if (word.equals(sWord)) {
                    rsl = "Выберите другую статью...";
                }
            }
        }
        return rsl;
    }
}
