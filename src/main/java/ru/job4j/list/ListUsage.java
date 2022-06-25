package ru.job4j.list;

import java.util.*;

/**
 * 0.1. Интерфейс List [#4953]
 */
public class ListUsage {
    public static void main(String[] args) {
        List<String> rsl = new ArrayList<>();

        /**
         * 1.1. boolean add(E e) – добавляет элемент e в конец списка.
         */
        rsl.add("one");
        rsl.add("two");
        rsl.add("three");

        rsl.sort(Comparator.reverseOrder());

    }
}
