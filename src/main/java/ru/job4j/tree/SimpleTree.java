package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

/**
 * 1. Создать элементарную структуру дерева [#1711]
 * 2. Добавить метод boolean isBinary() [#1712]
 * @param <E>
 */
public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Метод add - Должен находить узел по значению parent и добавлять в него
     * дочерний узел со значением child.
     * В этом методе нужно проверить, что значения child еще нет в дереве,
     * а parent есть.
     * Если child есть, то метод должен вернуть false.
     * @param parent узел родитель
     * @param child дочерний узел
     * @return
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> parents = findBy(parent);
        Optional<Node<E>> childs = findBy(child);
        if (childs.isEmpty() && parents.isPresent()) {
            rsl = true;
            parents.get().children.add(new Node<>(child));
        }
        return rsl;
    }

    /**
     * Метод ищет оптионал по значению
     * В оптионале может быть либо Node<E>, либо null
     * @param value
     * @return
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(s -> s.value.equals(value));
    }

    /**
     * Метод должен проверять количество дочерних элементов в дереве.
     * Если их > 2 - то дерево не бинарное
     * Метод должен циклически пройти по всем элементам дерева, аналогично методу findBy().
     * @return
     */
    @Override
    public boolean isBinary() {
        return findByPredicate(s -> s.children.size() > 2).isEmpty();
    }

    /**
     * Predicate (BiPredicate) - проверяет соблюдение некоторого условия.
     * Если оно соблюдается, то возвращается значение true.
     * В качестве параметра лямбда-выражение принимает объект типа T;
     * @param condition
     * @return
     */
    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
