package ru.job4j.ood.srp.reports.reports;

import ru.job4j.ood.srp.reports.Employee;

import java.util.function.Predicate;

public interface Report {

    String generate(Predicate<Employee> filter);

}