package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Утверждения с коллекциями [#504885]
 */
class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert
                .toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .containsExactly("first", "second", "three", "four", "five")
                .hasSize(5);
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert
                .toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1))
                .containsExactly("first", "second", "three", "four", "five")
                .hasSize(5);
        assertThat(list).element(2).isNotNull()
                .isEqualTo("three");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert
                .toSet("first", "second", "three", "four", "five");
        assertThat(set).hasSize(5)
                .contains("second")
                .containsAnyOf("zero", "second", "six")
                .hasSize(5)
                .allSatisfy(e ->
                    assertThat(e).doesNotContain("Jon")
                );
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert
                .toMap("first", "second", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("second", "first", "four")
                .containsValues(2, 0, 1)
                .doesNotContainKey("Jon")
                .doesNotContainValue(6)
                .containsEntry("four", 3);
    }
}