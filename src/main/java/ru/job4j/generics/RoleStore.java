package ru.job4j.generics;

/**
 * реализацию для роли
 */
public class RoleStore implements Store<Role> {

    private final Store<Role> store = new MemStore<>();

    /**
     * метод добавляет в хранилище объект типа Role, при этом метод ничего не возвращает
     * @param model объект типа Role
     */
    @Override
    public void add(Role model) {
        store.add(model);
    }
    /**
     * метод выполняет замену объекта по id,
     * на объект который передается в параметрах метода
     * и возвращает true, если замена выполнена успешно
     * @param id ключ типа String
     * @param model объект типа Role
     * @return возвращает true, если замена выполнена успешно
     */

    @Override
    public boolean replace(String id, Role model) {
        return store.replace(id, model);
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
        return store.delete(id);
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
    public Role findById(String id) {
        return store.findById(id);
    }
}
