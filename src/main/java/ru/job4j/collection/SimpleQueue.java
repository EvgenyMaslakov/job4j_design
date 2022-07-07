package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * 5. Очередь на двух стеках [#160]
 * @param <T>
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * счетчик заполнения стека In
     */
    private int sizeIn = 0;

    /**
     * счетчик заполнения стека Out
     */
    private int sizeOut = 0;

    /**
     *
     Метод poll() - должен возвращать первое значение и удалять его из очереди.
     * @return возвращает первое значение
     */
    public T poll() {
        if (sizeIn == 0) {
            throw new NoSuchElementException();
        }
       if (sizeOut == 0) {
           while (!in.isEmpty()) {
               out.push(in.pop());
               sizeOut++;
               sizeIn--;
           }
       }
       sizeOut--;
       return out.pop();
    }

    /**
     * Метод push(T value) - помещает значение в конец очереди.
     * @param value значение, помещаемое в конец
     */
    public void push(T value) {
        in.push(value);
        sizeIn++;
    }
}
