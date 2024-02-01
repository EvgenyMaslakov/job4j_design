package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class NonNullIterator implements Iterator<Integer> {

    private Integer[] data;
    private int index;

    public NonNullIterator(Integer[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (index < data.length && data[index] == null) {
            index++;
        }
        return index < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[index++];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /**
     * Выполняет заданное действие для каждого оставшегося элемента,
     * пока все элементы не будут обработаны или пока действие не
     * вызовет исключение.
     * Действия выполняются в порядке итерации, если этот порядок указан.
     * Исключения, созданные действием, передаются вызывающему объекту.
     * В данном итераторе не поддерживается.
     * @param action Действие, которое необходимо выполнить для каждого элемента
     */
    @Override
    public void forEachRemaining(Consumer<? super Integer> action) {
        while (hasNext()) {
            action.accept(next());
        }
    }
}