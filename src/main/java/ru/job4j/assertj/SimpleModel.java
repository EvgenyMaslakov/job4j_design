package ru.job4j.assertj;

/**
 * Утверждения с исключениями [#504886]
 */
public class SimpleModel {
    private String name = "";

    /**
     * getName() выбросит исключение IllegalArgumentException,
     * если поле name не заполнено
     * @return имя
     */
    public String getName() {
        if (name.length() == 0) {
            throw new IllegalArgumentException();
        }
        return name;
    }

    /**
     * setName(String word, int number) выбросит исключение, если введены несогласованные данные
     * (длина переменной word не совпадает со значением переменной number)
     * @param word
     * @param number
     */
    public void setName(String word, int number) {
        if (word.length() != number) {
            throw new IllegalArgumentException(
                    String.format("this word: %s has length not equal to : %s", word, number)
            );
        }
        this.name = word;
    }
}
