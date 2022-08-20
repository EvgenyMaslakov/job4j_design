package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Утверждения с исключениями [#504886]
 */
class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkArrayOfNamesIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    /**
     * проверка содержания текстового информационного сообщения, сопровождающее исключение
     * isInstanceOf(IllegalArgumentException.class) - проверяет класс исключения
     * hasMessageMatching("^.+") - с помощью регулярного выражения проверяется
     * факт наличия сообщения
     * hasMessageContaining(name) - проверяется, что в сообщении есть
     * соответствующие параметры
     * hasMessageContaining("=Jon") - проверяется наличие конкретного слова в сообщении
     */
    @Test
    void checkNameDoesNotContainTheSymbol() {
        NameLoad nameLoad = new NameLoad();
        String name = "Jon";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(name)
                .hasMessageContaining("=");
    }

    @Test
    void checkNameDoesNotContainAKey() {
        NameLoad nameLoad = new NameLoad();
        String name = "=Jon";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(name)
                .hasMessageContaining("key");
    }

    @Test
    void checkNameDoesNotContainAValue() {
        NameLoad nameLoad = new NameLoad();
        String name = "Bob=";
        assertThatThrownBy(() -> nameLoad.parse(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+")
                .hasMessageContaining(name)
                .hasMessageContaining("value");
    }

}