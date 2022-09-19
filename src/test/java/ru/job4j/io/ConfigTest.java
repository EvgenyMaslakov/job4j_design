package ru.job4j.io;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertThrows;

class ConfigTest {

    /**
     * Чтение файла без комментариев
     * Строка вида "ключ=значение=1" или "ключ=значение="
     * должна быть обработана и распознана как ключ "ключ" и
     * значение "значение=1" или "значение=" соответственно.
     */
    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("1")).isEqualTo("A");
        assertThat(config.value("2")).isEqualTo("B=B");
        assertThat(config.value("3")).isEqualTo("C=");
    }

    /**
     * Чтение файла с комментариями
     */
    @Test
    void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        String key = "1";
        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> {
                    config.value(key);
                });
        config.load();
        assertThat(exception.getMessage()).isEqualTo("Отсутствует ключ " + key);
        assertThat(config.value("2")).isEqualTo("B=B");
        assertThat(config.value("3")).isEqualTo("C=");
    }

    /**
     * Чтение файла с пустыми строками
     */
    @Test
    void whenTheStringIsEmpty() {
        String path = "./data/when_the_string_is_empty.properties";
        Config config = new Config(path);
        String key = "5";
        NoSuchElementException exception = assertThrows(
                NoSuchElementException.class,
                () -> {
                    config.value(key);
                });
        config.load();
        assertThat(exception.getMessage()).isEqualTo("Отсутствует ключ " + key);
        assertThat(config.value("2")).isEqualTo("B=B");
        assertThat(config.value("3")).isEqualTo("C=");
    }

    /**
     * чтение файла с шаблоном =значение
     * в этом случае нужно ожидать исключение IllegalArgumentException
     */
    @Test
    public void whenPatternEqualsValue() {
        String path = "./data/when_pattern_equals_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }
}