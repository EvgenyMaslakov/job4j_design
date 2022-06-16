package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для одномерного массива чисел
 */
public class ArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public ArrayIt(int[] data) {
        this.data = data;
    }

    /**
     * Метод проверяет есть ли следующий элемент
     * @return
     */
    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    /**
     * Метод возвращает первый элемент ячейки, второй вызов возвращает второй и т.д.
     * @return
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}
