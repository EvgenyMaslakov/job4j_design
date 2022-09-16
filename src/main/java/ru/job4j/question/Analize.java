package ru.job4j.question;

import java.util.HashSet;
import java.util.Set;

/**
 * Сравнение двух состояний множества
 * Главное требование: Задача должна решаться за линейное время
 */
public class Analize {

    /**
     * Разница между начальным и измененным состояниями множества
     * @param previous начальное множество
     * @param current измененное множество
     * Логика:
     * Сравниваем каждый элемент старого списка с каждым элементом нового списка
     * Если индексы равны, а имена нет, увеличиваем счетчик измененных элементов changed
     * Если два объекта равны, увеличиваем счетчик дублируемых элементов repeat
     * Количество добавленных элементов это размер нового списка
     * минус дублированные и измененные элементы
     * Количество удаленных элементов это размер старого списка
     * минус дублированные и измененные элементы
     * @return возвращает объект Info
     */
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        int repeat = 0;
        Set<User> oldElems = new HashSet<>(previous);
        Set<User> newElems = new HashSet<>(current);
        for (User oldEl : oldElems) {
            for (User newEl : newElems) {
                if (newEl.getId() == oldEl.getId()
                        && !newEl.getName().equals(oldEl.getName())) {
                    changed++;
                }
                if (oldEl.equals(newEl)) {
                    repeat++;
                }
            }
        }

        added = current.size() - changed - repeat;
        deleted = previous.size() - changed - repeat;

        return new Info(added, changed, deleted);
    }

}
