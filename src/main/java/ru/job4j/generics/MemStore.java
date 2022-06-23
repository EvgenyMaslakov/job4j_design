package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

/**
 * Каркас универсального хранилища
 */
public final class MemStore<T extends Base> implements Store<T> {

    /**
     * Хранение данных
     */
    private final Map<String, T> storage = new HashMap<>();

    /**
     * метод добавляет в хранилище объект типа T, при этом метод ничего не возвращает
     * @param model объект типа T
     */
    @Override
    public void add(T model) {
        if (!storage.containsKey(model.getId())) {
            storage.put(model.getId(), model);
        }
    }

    /**
     * метод выполняет замену объекта по id,
     * на объект который передается в параметрах метода
     * и возвращает true, если замена выполнена успешно
     * @param id ключ типа String
     * @param model объект типа T
     * @return возвращает true, если замена выполнена успешно
     */
    @Override
    public boolean replace(String id, T model) {
        boolean rsl = false;
        if (storage.containsKey(id)) {
            storage.put(id, model);
            rsl = true;
        }
        return rsl;
    }

    /**
     * метод удаляет пару ключ-значение по id,
     * который передается в метод и возвращает true,
     * если удаление выполнено успешно
     * @param id
     * @return возвращает true, если удаление выполнено успешно
     */
    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        if (storage.containsKey(id)) {
            storage.remove(id);
            rsl = true;
        }
        return rsl;

    }

    /**
     * метод возвращает объект по ключу, который равен id,
     * передаваемый в качестве параметра метода
     * или возвращает null если по такому ключу
     * в Map еще нет пары ключ-значение
     * @param id ключ типа String
     * @return возвращает объект по ключу, который равен id
     */
    @Override
    public T findById(String id) {
        return storage.containsKey(id) ? storage.get(id) : null;
    }
}
