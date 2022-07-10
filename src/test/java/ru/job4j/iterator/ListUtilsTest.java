package ru.job4j.iterator;

import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    /**
     * когда AddBefore
     */
    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);

        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    /**
     * когда AddBefore перед первым
     */
    @Test
    public void whenAddBeforeBeforeTheFirst() {
        List<Integer> input = new ArrayList<>(Arrays.asList(2, 3));
        ListUtils.addBefore(input, 0, 1);

        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    /**
     * когда AddBefore с недопустимым индексом
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    /**
     * когда AddAfter
     */
    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 0, 2);

        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    /**
     * когда AddAfter после последнего
     */
    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);

        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    /**
     * когда AddAfter с недопустимым индексом
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    /**
     * когда removeIf удаляет четные элементы
     */
    @Test
    public void whenRemoveIfEvenElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 4));
        Predicate<Integer> filter = x -> x % 2 == 0;
        ListUtils.removeIf(input, filter);

        assertThat(input, is(Arrays.asList(1, 3, 3)));
    }

    /**
     * когда removeIf отсутствуют четные элементы для удаления
     */
    @Test
    public void whenRemoveIfNoEvenElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 5, 3, 3, 7));
        Predicate<Integer> filter = x -> x % 2 == 0;
        ListUtils.removeIf(input, filter);

        assertThat(input, is(Arrays.asList(1, 5, 3, 3, 7)));
    }

    /**
     * когда removeIf удаляет нечетные элементы
     */
    @Test
    public void whenRemoveIfNotEvenElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 4));
        Predicate<Integer> filter = x -> x % 2 != 0;
        ListUtils.removeIf(input, filter);

        assertThat(input, is(Arrays.asList(2, 4)));
    }

    /**
     * когда replaceIf() заменяет четные элементы
     */
    @Test
    public void whenReplaceIfEvenElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 4));
        Predicate<Integer> filter = x -> x % 2 == 0;
        ListUtils.replaceIf(input, filter, 0);

        assertThat(input, is(Arrays.asList(1, 0, 3, 3, 0)));
    }

    /**
     * когда replaceIf заменяет нечетные элементы
     */
    @Test
    public void whenReplaceIfNotEvenElements() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 4));
        Predicate<Integer> filter = x -> x % 2 != 0;
        ListUtils.replaceIf(input, filter, 0);

        assertThat(input, is(Arrays.asList(0, 2, 0, 0, 4)));
    }

    /**
     * когда removeAll
     */
    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> delete = new ArrayList<>(Arrays.asList(2, 3, 4, 5));
        ListUtils.removeAll(input, delete);

        assertThat(input, is(Arrays.asList(1)));
    }

    /**
     * когда removeAll с дублированием элементов в исходном списке
     */
    @Test
    public void whenRemoveAllDuplicationOfElementsInput() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 2));
        List<Integer> delete = new ArrayList<>(Arrays.asList(2, 5));
        ListUtils.removeAll(input, delete);

        assertThat(input, is(Arrays.asList(1)));
    }

    /**
     * когда removeAll с дублированием элементов в списке удаляемых элементов
     */
    @Test
    public void whenRemoveAllDuplicationOfElementsDelete() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2));
        List<Integer> delete = new ArrayList<>(Arrays.asList(1, 1));
        ListUtils.removeAll(input, delete);

        assertThat(input, is(Arrays.asList(2)));
    }
}