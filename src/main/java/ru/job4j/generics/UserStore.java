package ru.job4j.generics;

/**
 * реализацию для пользователя
 */
public class UserStore implements Store<User> {

    private final Store<User> store = new MemStore<>();

    /**
     * метод добавляет в хранилище объект типа User, при этом метод ничего не возвращает
     * @param model объект типа User
     */
    @Override
    public void add(User model) {
        store.add(model);
    }
    /**
     * метод выполняет замену объекта по id,
     * на объект который передается в параметрах метода
     * и возвращает true, если замена выполнена успешно
     * @param id ключ типа String
     * @param model объект типа User
     * @return возвращает true, если замена выполнена успешно
     */

    @Override
    public boolean replace(String id, User model) {
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
    public User findById(String id) {
       return store.findById(id);
    }
}
