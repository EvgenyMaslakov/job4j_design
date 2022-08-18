package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface Tree<E> {

    /**
     * Метод add - Должен находить узел по значению parent и добавлять в него
     * дочерний узел со значением child.
     * В этом методе нужно проверить, что значения child еще нет в дереве,
     * а parent есть.
     * Если child есть, то метод должен вернуть false.
     * @param parent значение
     * @param child дочерний узел
     * @return
     */
    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);

    /**
     * Класс Node описывает узел дерева.
     * Узел содержит хранимое значение и ссылки на дочерние узлы.
     * @param <E> хранимое значение
     */
    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }
    }
}
