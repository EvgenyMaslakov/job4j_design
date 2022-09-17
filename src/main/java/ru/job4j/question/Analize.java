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
     * Добавляем все элементы в один список HeshSet,
     * из этого списка удалились все дубликаты
     * Добавляем все ID общего списка без дубликатов в список HeshSet,
     * из этого списка удалились все измененные элементы
     * Зная размеры начальных списков, общего списка без дубликатов,
     * списка без дубликатов и измененных элементов, вычисляем
     * количество добавленных, измененных и удаленных элементов
     */
    public static Info diff(Set<User> previous, Set<User> current) {
        int previousSize = previous.size();
        int currentSize = current.size();
        Set<User> allElems = new HashSet<>(previous);
        allElems.addAll(current);
        int allEl = allElems.size();
        Set<Integer> ids = new HashSet<>();
        for (User el : allElems) {
            ids.add(el.getId());
        }
        int allId = ids.size();
        int changed = allEl - allId;
        int repeat = previousSize + currentSize - allEl;
        int added = currentSize - changed - repeat;
        int deleted = previousSize - changed - repeat;
        return new Info(added, changed, deleted);
    }

}
