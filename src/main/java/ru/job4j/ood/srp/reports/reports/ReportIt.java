package ru.job4j.ood.srp.reports.reports;

import ru.job4j.ood.srp.reports.DateTimeParser;
import ru.job4j.ood.srp.reports.Employee;
import ru.job4j.ood.srp.reports.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportIt implements Report {

    private final DateTimeParser<Calendar> dateTimeParser;

    private final Store store;

    public ReportIt(DateTimeParser<Calendar> dateTimeParser, Store store) {
        this.dateTimeParser = dateTimeParser;
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name, Hired, Fired, Salary")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(",")
                    .append(dateTimeParser.parse(employee.getHired())).append(",")
                    .append(dateTimeParser.parse(employee.getFired())).append(",")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
