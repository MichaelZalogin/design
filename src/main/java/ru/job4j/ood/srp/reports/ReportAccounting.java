package ru.job4j.ood.srp.reports;

import ru.job4j.ood.srp.reports.converter.Currency;
import ru.job4j.ood.srp.reports.converter.CurrencyConverter;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportAccounting implements Report {

    private final DateTimeParser<Calendar> dateTimeParser;

    private final CurrencyConverter currencyConverter;

    private final Currency source;

    private final Currency target;

    public ReportAccounting(DateTimeParser<Calendar> dateTimeParser, CurrencyConverter currencyConverter, Currency source, Currency target) {
        this.dateTimeParser = dateTimeParser;
        this.currencyConverter = currencyConverter;
        this.source = source;
        this.target = target;
    }

    @Override
    public String generate(Predicate<Employee> filter, Store store) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(currencyConverter.convert(source, employee.getSalary(), target))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}