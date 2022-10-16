package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).isNotNull()
                .isNotEmpty()
                .containsExactly("first", "second", "three", "four", "five")
                .startsWith("first")
                .doesNotContain("ten");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> stringSet = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(stringSet).isNotEmpty()
                .allSatisfy(a -> {
                    assertThat(a.length()).isLessThan(10);
                    assertThat(a.length()).isGreaterThan(0);
                })
                .anyMatch(e -> e.length() == 5)
                .hasSize(5);
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> values = simpleConvert.toMap("three", "four", "five");
        assertThat(values).isNotNull()
                .containsExactlyInAnyOrderEntriesOf(Map.of("three", 0, "four", 1, "five", 2))
                .containsKeys("three", "four")
                .containsValue(1)
                .containsEntry("four", 1);
    }
}