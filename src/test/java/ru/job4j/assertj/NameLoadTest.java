package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {

    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenArrayNameIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Names array is empty");
    }

    @Test
    void whenNameNotContainsEquals() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Some text"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("this name: Some text does not contain the symbol \"=\""));
    }

    @Test
    void whenNameStartWithEquals() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=Some text"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("this name: =Some text does not contain a key"));
    }

    @Test
    void whenNameEndsWithEquals() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Some text="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("Some text= does not contain a value"));
    }
}