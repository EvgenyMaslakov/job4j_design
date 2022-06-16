package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор для двухмерного массива int[][]
 */
public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    /**
     * Метод проверяет есть ли следующий элемент
     * @return
     */
    @Override
    public boolean hasNext() {
        while (column == data[row].length || data[row].length == 0) {
            row++;
            column = 0;
        }
            return row < data.length;
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
        return data[row][column++];
    }
}
