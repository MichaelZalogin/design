package ru.job4j.iterator;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

class ArrayItTest {
    @Test
    public void whenMultiCallhasNextThenTrue() {
        ArrayIt it = new ArrayIt(
                new int[]{1, 2, 3}
        );
        assertThat(it.hasNext()).isTrue();
        assertThat(it.hasNext()).isTrue();
    }

    @Test
    public void whenReadSequence() {
        ArrayIt it = new ArrayIt(
                new int[]{1, 2, 3}
        );
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.next()).isEqualTo(2);
        assertThat(it.next()).isEqualTo(3);
    }

    @Test
    public void whenNextFromEmpty() {
        ArrayIt it = new ArrayIt(new int[]{});
        assertThatThrownBy(it::next)
                .isInstanceOf(NoSuchElementException.class);
    }
}
