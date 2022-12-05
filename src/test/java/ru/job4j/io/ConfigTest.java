package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"))
                .isEqualTo("org.hibernate.dialect.PostgreSQLDialect");
        assertThat(config.value("hibernate.connection.url"))
                .isEqualTo("jdbc:postgresql:/127.0.0.1:5432/trackstudio=1");
        assertThat(config.value("hibernate.connection.password"))
                .isEqualTo("password=");
    }

    @Test
    void whenPairWithoutData() {
        String path = "./data/pair_without_data.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"))
                .isNull();
    }

    @Test
    void whenPairContainsInvalidForm() {
        String path = "./data/pair_contains_invalid_form.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> config.load());
        assertThat(exception.getMessage()).isEqualTo("Uncorrected line: = org.hibernate.dialect.PostgreSQLDialect");
    }

    @Test
    void whenPairNotContainsValue() {
        String path = "./data/pair_not_contains_value.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> config.load());
        assertThat(exception.getMessage()).isEqualTo("Uncorrected line: hibernate.connection.url=");
    }
}