package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static java.util.Calendar.OCTOBER;

/**
 * 2. Без переопределения equals и hashCode [#1005]
 */
public class UserMap {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1991, 10, 13);
        User user1 = new User("Evgeny", 1, calendar);
        User user2 = new User("Evgeny", 1, calendar);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        for (Map.Entry<User, Object> pair : map.entrySet()) {
            String key = pair.getKey().toString();
            String value = pair.getValue().toString();
            System.out.println(key + ":" + value);
        }
    }
}
