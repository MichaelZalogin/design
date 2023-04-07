package ru.job4j.ood.srp.reports;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.reports.reports.Report;
import ru.job4j.ood.srp.reports.reports.ReportIt;

import java.util.Calendar;
import java.util.List;

import static java.lang.System.lineSeparator;
import static org.assertj.core.api.Assertions.assertThat;

class ReportItTest {

    @Test
    void whenGeneratedItReport() {
        Store store = new MemStore();
        List<Employee> employees;
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Employee worker1 = new Employee("Ivan", now, now, 200);
        Employee worker2 = new Employee("Dmitry", now, now, 1100);
        Employee worker3 = new Employee("Petr", now, now, 1300);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        employees = List.of(worker1, worker2, worker3);
        Report report = new ReportIt(parser, store);
        StringBuilder expect = new StringBuilder("Name, Hired, Fired, Salary")
                .append(lineSeparator());
        for (Employee emp : employees) {
            expect.append(emp.getName()).append(",")
                    .append(parser.parse(emp.getHired())).append(",")
                    .append(parser.parse(emp.getFired())).append(",")
                    .append(emp.getSalary())
                    .append(lineSeparator());
        }
        assertThat(report.generate(em -> true)).isEqualTo(expect.toString());
    }
}