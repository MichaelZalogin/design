package ru.job4j.ood.srp.reports;

public interface CurrencyConverter {
    double convert(Currency source, double sourceValue, Currency target);
}
