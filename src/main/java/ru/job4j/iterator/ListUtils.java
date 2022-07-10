package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

/**
 * 7. ListIterator [#350217]
 */
public class ListUtils {

    /**
     * addBefore() вставляет элемент до индекса
     *
     * Objects.checkIndex Проверяет, находится ли index
     * в пределах диапазона от index(включительно) до list.size()(исключительно)
     * и возвращает index если он находится в пределах диапазона
     *
     * @param list список, в который вставляем элемент
     * @param index индекс вставленного элемента
     * @param value вставляемый элемент
     * @param <T> любой тип
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    /**
     * addAfter() вставляет элемент после индекса
     *
     * @param list список, в который вставляем элемент
     * @param index индекс вставленного элемента
     * @param value вставляемый элемент
     * @param <T> любой тип
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.next();
                iterator.add(value);
                iterator.previous();
                break;
            }
            iterator.next();
        }
    }

    /**
     * removeIf() удаляет все элементы, которые удовлетворяют предикату.
     * Запрещено использовать метод List.removeIf
     *
     * Objects.checkIndex Проверяет, находится ли index
     * в пределах диапазона от 0(включительно) до length(исключительно)
     * и возвращает index если он находится в пределах диапазона
     *
     * @param list список, в который вставляем элемент
     * @param filter предикат
     * @param <T> любой тип
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
            }
        }
    }

    /**
     * replaceIf() заменяет все элементы, которые удовлетворяют предикату
     *
     * @param list список, в который вставляем элемент
     * @param filter предикат
     * @param value вставляемый элемент вместо элемента, удовлетворяющему предикату
     * @param <T> любой тип
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.set(value);
            }
        }
    }

    /**
     * removeAll() удаляет из списка те элементы, которые есть в elements.
     * Запрещено использовать метод List.removeAll().
     *
     * @param list список, в который вставляем элемент
     * @param elements список удаляемых элементов
     * @param <T> любой тип
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        HashSet<T> rsl = new HashSet<>(elements);
        List<T> rsl2 = new ArrayList<T>(rsl);
        ListIterator<T> listIterator = list.listIterator();
        ListIterator<T> elementsIterator = rsl2.listIterator();
        int counter = 0;
        while (listIterator.hasNext()) {
            T l = listIterator.next();
            while (elementsIterator.hasNext()) {
                T e = elementsIterator.next();
                counter++;
                if (l == e) {
                    listIterator.remove();
                }
            }
            for (int i = counter; i > 0; i--) {
                counter--;
                elementsIterator.previous();
            }

        }
    }

}
