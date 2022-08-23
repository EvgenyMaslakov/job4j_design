package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Утверждения с коллекциями [#504885]
 */
class SimpleCollectionTest {

    /**
     * показывает основные операции проверки содержания коллекции
     * hasSize(5) - размер
     * contains(1, 5, 4) - содержит элементы
     * containsOnly(1, 5, 4, 3) - содержит это в любом порядке, дубликаты не важны
     * containsExactly(1, 1, 3, 4, 5) - содержит только это и только в указанном порядке
     * containsExactlyInAnyOrder(5, 1, 3, 4, 1) - содержит только это в любом порядке
     * containsAnyOf(200, 100, 3) - содержит хотя бы один из
     * doesNotContain(0, 6) - не содержит ни одного из
     * startsWith(1, 1) - начинается с последовательности
     * endsWith(4, 5) - заканчивается на последовательность
     * containsSequence(1, 3) - содержит последовательность
     */
    @Test
    void generalAssert() {
        SimpleCollection<Integer> sc = new SimpleCollection<>(1, 1, 3, 4, 5);
        assertThat(sc).isNotEmpty()
                .hasSize(5)
                .contains(1, 5, 4)
                .containsOnly(1, 5, 4, 3)
                .containsExactly(1, 1, 3, 4, 5)
                .containsExactlyInAnyOrder(5, 1, 3, 4, 1)
                .containsAnyOf(200, 100, 3)
                .doesNotContain(0, 6)
                .startsWith(1, 1)
                .endsWith(4, 5)
                .containsSequence(1, 3);
    }

    /**
     * показывает, как можно проверить выполнение элементами коллекции нужных условий
     * allSatisfy(e -> {
     *      assertThat(e).isLessThan(10);
     *      assertThat(e).isGreaterThan(0);
     *      }) - все элементы выполняют условие
     * anySatisfy(e -> {
     *      assertThat(e).isLessThan(5);
     *      assertThat(e).isEqualTo(3);
     *      }) - хотя бы один элемент выполняет условие
     *
     */
    @Test
    void satisfyAssert() {
        SimpleCollection<Integer> sc = new SimpleCollection<>(1, 1, 3, 4, 5);
        assertThat(sc).isNotNull()
                .allSatisfy(e -> {
                    assertThat(e).isLessThan(10);
                    assertThat(e).isGreaterThan(0);
                })
                .anySatisfy(e -> {
                    assertThat(e).isLessThan(5);
                    assertThat(e).isEqualTo(3);
                })
                .allMatch(e -> e < 10)
                .anyMatch(e -> e == 5)
                .noneMatch(e -> e < 1);
    }

    /**
     * показывает, что можно выбрать один элемент из коллекции и дальше проверять только его
     * assertThat(sc).first().isEqualTo(1) - первый элемент
     * assertThat(sc).element(0).isNotNull().isEqualTo(1) - элемент по индексу
     * assertThat(sc).last().isNotNull().isEqualTo(5) - последний элемент
     */
    @Test
    void checkNavigationList() {
        SimpleCollection<Integer> sc = new SimpleCollection<>(1, 1, 3, 4, 5);
        assertThat(sc).first().isEqualTo(1);
        assertThat(sc).element(0).isNotNull()
                .isEqualTo(1);
        assertThat(sc).last().isNotNull()
                .isEqualTo(5);
    }

    /**
     * показывает, как можно выбрать из коллекции группу элементов по некоторому условию
     * и дальше работать с отобранной группой
     * assertThat(sc).filteredOn(e -> e > 1).first().isEqualTo(3) - фильтруем источник
     * по предикату и работаем с результатом фильтрации
     * assertThat(sc).filteredOnAssertions(e -> assertThat(e).isLessThan(3))
     *      .hasSize(2)
     *      .first().isEqualTo(1) - фильтруем с помощью assertThat() и работаем
     * с результатом фильтрации
     */
    @Test
    void checkFilteredList() {
        SimpleCollection<Integer> sc = new SimpleCollection<>(1, 1, 3, 4, 5);
        assertThat(sc).filteredOn(e -> e > 1).first().isEqualTo(3);
        assertThat(sc).filteredOnAssertions(e -> assertThat(e).isLessThan(3))
                .hasSize(2)
                .first().isEqualTo(1);
    }

    /**
     * Библиотека AssertJ может работать с коллекциями, реализующими интерфейс java.util.Map
     * containsKeys(1, 3, 2) - содержит ключи
     * containsValues("3", "1", "2") - содержит значения
     * doesNotContainKey(0) - не содержит ключ
     * doesNotContainValue("0") - не содержит значение
     * containsEntry(2, "2") - содержит пару ключ-значение
     */
    @Test
    void assertMap() {
        Map<Integer, String> map = Map.of(
                1, "1", 2, "2", 3, "3");
        assertThat(map).hasSize(3)
                .containsKeys(1, 3, 2)
                .containsValues("3", "1", "2")
                .doesNotContainKey(0)
                .doesNotContainValue("0")
                .containsEntry(2, "2");
    }
}