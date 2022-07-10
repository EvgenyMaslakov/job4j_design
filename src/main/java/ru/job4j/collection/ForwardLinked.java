package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 3. Удалить head в односвязном списке. [#51424]
 * @param <T>
 */
public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * метод должен переставить элементы в обратном порядке
     * 1. Сначала мы делаем валидацию. Пустой список или список
     * из одного элемента не имеет смысла переворачивать,
     * поэтому возвращаем false
     *
     * 2. Далее уже начинаем менять ссылки.
     * Суть алгоритма заключается в установлении текущему
     * ссылки на следующий в качестве предыдущего. Далее мы
     * просто обновляем значения текущего и предыдущего.
     * Наконец, предыдущий ставится головой списка
     * @return возвращает true, если удалось переставить элементы в обратном порядке
     */
    public boolean revert() {
        boolean rsl;
        if (head == null || head.next == null) {
            rsl = false;
        } else {
        Node<T> prev = null;
        Node<T> current = head;
        Node<T> next = head.next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
        rsl = true;
        }
        return rsl;
    }

    public void addFirst(T value) {
        head = new Node<T>(value, head);
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T rsl = head.value;
        Node<T> newHead = head.next;
        head.next = null;
        head.value = null;
        head = newHead;
        return rsl;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
