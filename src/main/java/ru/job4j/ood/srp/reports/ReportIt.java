package ru.job4j.ood.srp.reports;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportIt implements Report {

    //    private final DateTimeParser<Calendar> dateTimeParser;
//
    @Override
    public String generate(Predicate<Employee> filter, Store store) {
//        StringBuilder text = new StringBuilder();
//        text.append("Name; Hired; Fired; Salary;")
//                .append(System.lineSeparator());
//        for (Employee employee : store.findBy(filter)) {
//            text.append(employee.getName()).append(" ")
//                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
//                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
//                    .append(employee.getSalary())
//                    .append(System.lineSeparator());

        return null;
    }
}
