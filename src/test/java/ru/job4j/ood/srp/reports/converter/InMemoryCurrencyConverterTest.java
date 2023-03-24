package ru.job4j.ood.srp.reports.converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCurrencyConverterTest {

    @Test
    void whenConvertIntoEurFromRub() {
        double salary = 1000.00;
        CurrencyConverter currencyConverter = new InMemoryCurrencyConverter();
        double result = currencyConverter.convert(Currency.EUR, salary, Currency.RUB);
        double expect = 63000;
        assertTrue(Double.compare(result, expect) == 0);
    }

    @Test
    void whenConvertIntoRubFromUsd() {
        double salary = 1000.00;
        CurrencyConverter currencyConverter = new InMemoryCurrencyConverter();
        double result = currencyConverter.convert(Currency.RUB, salary, Currency.USD);
        double expect = 16.2;
        assertTrue(Double.compare(result, expect) == 0);
    }
}