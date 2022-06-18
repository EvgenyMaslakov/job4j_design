package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * 5.1.2. Создать итератор четные числа
 */
public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    /**
     * Метод проверяет есть ли следующий элемент
     * @return возвращает true, только если в массиве есть четные элементы справа
     * от указателя (дальше по направлению движения итератора к концу массива).
     * Указатель итератора при создании итератора располагается перед ячейкой с
     * индексом [0]. Например, если мы вызовем два раза метод next(), то указатель
     * сместится вправо на два элемента и расположится между ячейками [1] и [2].
     * При вызове метода hasNext() - он вернет false, т.к. после указателя больше
     * нет четных чисел.
     */
    @Override
    public boolean hasNext() {
        for (int i = index; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Метод при каждом вызове возвращает последующий четный
     * @return возвращает только четные числа
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (data[index] % 2 != 0) {
            index++;
        }
        return data[index++];
    }

    /**
     * Удаляет из базовой коллекции последний элемент, возвращенный
     * этим итератором (дополнительная операция).
     * Этот метод можно вызвать только один раз за вызов next().
     * В данном итераторе не поддерживается.
     */
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
