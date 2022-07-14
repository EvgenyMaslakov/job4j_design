package ru.job4j.map;

import java.util.Calendar;

public class User {

    /**
     * имя
     */
    private String name;

    /**
     * дети
     */
    private int children;

    /**
     * день рождения
     */
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", children=" + children
                + ", birthday=" + birthday
                + '}';
    }
}
