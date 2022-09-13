package ru.job4j.set;

import java.util.HashSet;
import java.util.Set;

public class Jackpot {
    public static boolean checkYourWin(String[] combination) {
        boolean rsl = false;
        HashSet<String> hs = new HashSet<>();
        for (String c : combination) {
            hs.add(c);
        }
        if (hs.size() == 1) {
            rsl = true;
        }
        return rsl;
    }
}
