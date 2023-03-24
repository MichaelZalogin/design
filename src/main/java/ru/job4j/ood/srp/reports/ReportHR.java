package ru.job4j.ood.srp.reports;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportHR implements Report {

    @Override
    public String generate(Predicate<Employee> filter, Store store) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;").append(System.lineSeparator());
        List<Employee> salarySort = new ArrayList<>();
        for (Employee employee : store.findBy(filter)) {
            salarySort.add(employee);
        }
        Collections.sort(salarySort, Comparator.comparingDouble(Employee::getSalary));
        for (Employee employee : salarySort) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
