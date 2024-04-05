package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Создаем объект.
 * Преобразуем объект person в json-строку.
 * Превращаем json-строку обратно в объект
 */
public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"),
                new String[] {"Worker", "Married"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));
        final Person personMod = gson.fromJson(gson.toJson(person), Person.class);
        System.out.println(personMod);
    }
}
