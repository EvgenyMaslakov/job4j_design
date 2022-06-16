package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для одномерного массива отдает элементы в обратном порядке
 */
public class BackwardArrayIt implements Iterator<Integer> {
    private final int[] data;
    private int point;

    public BackwardArrayIt(int[] data) {
        this.data = data;
        this.point = data.length - 1;
    }

    /**
     * Метод проверяет есть ли следующий элемент
     * @return
     */
    @Override
    public boolean hasNext() {
        return point >= 0;
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
        return data[point--];
    }
}
