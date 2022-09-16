package ru.job4j.question;

import java.util.Objects;

/**
 * Класс показывает сколько добавили пользователей,
 * удалили пользователей и изменили имя пользователя.
 */
public class Info {

    /**
     * Сколько добавлено новых пользователей.
     * Добавленным считается такой пользователь,
     * что ранее его не было в множестве previous,
     * но в множестве current он есть.
     */
    private int added;

    /**
     * Сколько изменено пользователей.
     * Изменённым считается объект,
     * в котором изменилось имя, а id осталось прежним.
     */
    private int changed;

    /**
     * Сколько удалено пользователей.
     * Удаленным считается такой пользователь,
     * что ранее он был в множестве previous,
     * но теперь в множестве current его нет.
     */
    private int deleted;

    public Info(int added, int changed, int deleted) {
        this.added = added;
        this.changed = changed;
        this.deleted = deleted;
    }

    public int getAdded() {
        return added;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    public int getChanged() {
        return changed;
    }

    public void setChanged(int changed) {
        this.changed = changed;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Info info = (Info) o;
        return added == info.added && changed == info.changed && deleted == info.deleted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(added, changed, deleted);
    }

}
