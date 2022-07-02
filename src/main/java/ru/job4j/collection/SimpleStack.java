package ru.job4j.collection;

/**
 * 4. Используя контейнер на базе связанного списка создать контейнер Stack [#71474]
 * @param <T>
 */
public class SimpleStack<T> {

   private ForwardLinked<T> linked = new ForwardLinked<T>();

    /**
     * Метод pop() - должен возвращать значение и удалять его из коллекции.
     * @return возвращает значение и удаляет его из коллекции
     */
    public T pop() {
        return linked.deleteFirst();
    }

    /**
     * Метод push(T value) - помещает значение в коллекцию.
     * @param value значение, добавленное в коллекцию
     */
    public void push(T value) {
        linked.addFirst(value);
    }

}
