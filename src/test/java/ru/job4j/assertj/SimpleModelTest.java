package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SimpleModelTest {

    /**
     * проверка генерации исключения, когда метод класса не принимает аргументы
     */
    @Test
    void checkGetName() {
        SimpleModel sm = new SimpleModel();
        assertThatThrownBy(sm::getName)
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * проверяется метод, принимающий аргументы
     */
    @Test
    void  checkName() {
        SimpleModel sm = new SimpleModel();
        assertThatThrownBy(() -> sm.setName("name", 5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    /**
     * проверка наличие текстового информационного сообщения, сопровождающее исключение
     * isInstanceOf(IllegalArgumentException.class) - проверяет класс исключения
     * message() - проверяем факт наличия сообщения
     * isNotEmpty() - проверяем что сообщение не пустое
     */
    @Test
    void  checkMessage() {
        SimpleModel sm = new SimpleModel();
        String word = "name";
        int number = 5;
        assertThatThrownBy(() -> sm.setName(word, number))
                .isInstanceOf(IllegalArgumentException.class)
                .message()
                .isNotEmpty();
    }

    /**
     * проверка содержания текстового информационного сообщения, сопровождающее исключение
     * isInstanceOf(IllegalArgumentException.class) - проверяет класс исключения
     * hasMessageMatching("^.+") - с помощью регулярного выражения проверяется
     * факт наличия сообщения
     * hasMessageContaining(word, number) - проверяется, что в сообщении есть
     * соответствующие параметры
     * hasMessageContaining("name") - проверяется наличие конкретного слова в сообщении
     */
    @Test
    void  checkWordMessage() {
        SimpleModel sm = new SimpleModel();
        String word = "name";
        int number = 5;
        assertThatThrownBy(() -> sm.setName(word, number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(word, number)
                .hasMessageContaining("name");
    }
}