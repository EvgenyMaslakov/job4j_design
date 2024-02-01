package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Это класс, который равномерно распределяет данные из итератора по переданным ему спискам.
 */
public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int index = 0;
        while (source.hasNext()) {
            if (index == nodes.size()) {
                index = 0;
            }
            nodes.get(index).add(source.next());
            index++;
        }
    }
}
