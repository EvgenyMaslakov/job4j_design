package ru.job4j.map;

import java.util.*;

/**
 * 8. Реализовать собственную структуру данных - HashMap [#1008]
 * @param <K> ключ
 * @param <V> значение
 */
public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    /**
     * вместимомть таблицы table
     */
    private int capacity = 8;

    /**
     * количество элементов в массиве table
     */
    private int count = 0;

    /**
     * количество изменений в массиве table (добавление и удаление элементов)
     */
    private int modCount = 0;

    /**
     * Хэш-таблица должна быть динамической, т.е. расширяться при достижении значения LOAD_FACTOR=0.75.
     * Хэш-таблица должна уметь работать с ключом null
     */
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * Метод вставки put().
     * if (count == capacity * LOAD_FACTOR) увеличивает размер таблицы
     * в случае заполнения ее на 75%
     *
     * @param key ключ
     * @param value значение
     * @return в случае отсутствия места в ячейке должен возвращать false
     */
    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : (hashCode()) ^ (hashCode >>> 2);
    }

    /**
     * индекс объекта MapEntry<K, V> в таблице table
     * @param hash хэш-код
     * @return индекс
     */
    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        count = 0;
        modCount = 0;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> el: oldTable) {
            if (el != null) {
                put(el.key, el.value);
            }
        }
    }

    /**
     * Метод получения значения get().
     * Сравнение не-null ключей должно производиться сначала на равенство их хэшкодов,
     * а только затем по equals().
     * @param key ключ
     * @return в случае отсутствия значения должен возвращать null, в противном случае само значение
     */
    @Override
    public V get(K key) {
        V rsl = null;
        int index = key == null ? 0 : indexFor(key.hashCode());
        if (table[index] != null && table[index].key.equals(key)) {
            rsl = table[index].value;
        }
        return rsl;
    }

    /**
     * Метод удаления remove().
     * Сравнение не-null ключей должно производиться сначала на равенство их хэшкодов, а только затем по equals().
     * @param key
     * @return в случае успешного удаления должен возвращать true, в противном случае false
     */
    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = key == null ? 0 : indexFor(key.hashCode());
        if (table[index] != null && table[index].key.equals(key)) {
            rsl = true;
            table[index] = null;;
            count--;
            modCount++;
        }
        return rsl;
    }

    public int size () {
        return count;
    }

    /**
     * Итератор должен обладать fail-fast поведением
     * @return
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int point = 0;
            private int expectedModCount = modCount;

            /**
             * Метод проверяет есть ли следующий элемент
             * if (expectedModCount != modCount) проверяет не изменилась ли таблица до
             * завершения работы итератора
             * while (point < capacity && table[point] == null) проверяет не вышел ли итератор
             * за границы таблицы и пропускает бакет, если в нем нет элементов
             * @return возвращает true, если следующий элемент существует
             */
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < capacity && table[point] == null) {
                    point++;
                }
                return point < capacity;
            }

            /**
             * Метод возвращает первый элемент ячейки, второй вызов возвращает второй и т.д.
             *
             * @return при каждом вызове возвращает следующий элемент в списке
             */
            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
