package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * 2. Создать контейнер на базе связанного списка [#159]
 * @param <T>
 */
public class SimpleLinkedList<T> implements LinkedList<T> {

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private int size = 0;

    private int modCount = 0;

    private Node<T> first;

    private Node<T> last;

    /**
     * добавляет в конец
     *
     * @param value элемент, добавляемый в конец списка
     */
    @Override
    public void add(T value) {
        final Node<T> l = last;
        final Node<T> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * перебирает элементы до указанного индекса и возвращает значение из найденной ноды
     *
     * @param index индекс, до которого будет осуществлятся перебор
     * @return возвращает значение по индексу
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> value = first;
        for (int i = 1; i <= index; i++) {
            value = value.next;
        }
        return value.item;
    }

    /**
     * Итератор должен реализовывать fail-fast поведение,
     * т.е. если с момента создания итератора коллекция подверглась
     * структурному изменению, итератор должен кидать ConcurrentModificationException.
     * <p>
     * Это достигается через введение счетчика изменений - modCount.
     * Каждая операция, которая структурно модифицирует коллекцию, должна
     * инкрементировать этот счетчик. В свою очередь, итератор запоминает
     * значение этого счетчика на момент своего создания (expectedModCount),
     * а затем, на каждой итерации, сравнивает сохраненное значение с текущим
     * значением поля modCount, если они отличаются, то генерируется исключение.
     * <p>
     * Итератор должен работать без метода get(), потому что получение по
     * индексу долгая операция для связных списков.
     *
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> curent = first;
            private int expectedModCount = modCount;

            /**
             * Метод проверяет есть ли следующий элемент
             * @return возвращает true, если следующий элемент существует
             */
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }

                return curent != null;
            }

            /**
             * Метод возвращает первый элемент ячейки, второй вызов возвращает второй и т.д.
             * @return при каждом вызове возвращает следующий элемент в списке
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = curent.item;
                curent = curent.next;
                return value;
            }

        };
    }
}