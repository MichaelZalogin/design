package ru.job4j.ood.srp.reports;

public interface DateTimeParser<T> {
    String parse(T t);
}