package ru.job4j.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@Disabled
class GeneratorImpTest {

    @Test
    void whenArgsAndTemplateValid() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        String expected = "I am a Petr Arsentev, Who are you? ";
        var generatorImp = new GeneratorImp();
        String result = generatorImp.produce(template, args);
        assertEquals(expected, result);
    }

    @Test
    void whenArgsNotContainsInTemplate() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = new HashMap<>();
        args.put("anotherName", "Petr Arsentev");
        args.put("anotherSubject", "you");
        var generatorImp = new GeneratorImp();
        assertThatThrownBy(() -> generatorImp.produce(template, args)).
                isInstanceOf(IllegalArgumentException.class);
    }
}