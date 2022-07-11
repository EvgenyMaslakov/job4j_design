package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    /**
     * Хранилище данных
     */
    private SimpleArrayList<T> set = new SimpleArrayList<T>(10);

    /**
     * add - Добавляет указанный элемент в конец этого списка (дополнительная операция).
     * @param value добавляемый элемент
     */
    @Override
    public boolean add(T value) {
        boolean rsl = false;
        if (!contains(value)) {
            set.add(value);
            rsl = true;
        }
        return rsl;
    }

    /**
     * contains - проверяет наличие элемента в множестве.
     * @param value добавляемый элемент
     */
    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T el : set) {
            if (value == null || value.equals(el)) {
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
