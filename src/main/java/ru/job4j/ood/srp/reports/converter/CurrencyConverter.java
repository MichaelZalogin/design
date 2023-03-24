package ru.job4j.ood.srp.reports.converter;

public interface CurrencyConverter {

    double convert(Currency source, double sourceValue, Currency target);

}