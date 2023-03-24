package ru.job4j.ood.srp.reports;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.reports.converter.Currency;
import ru.job4j.ood.srp.reports.converter.CurrencyConverter;
import ru.job4j.ood.srp.reports.converter.InMemoryCurrencyConverter;
import java.util.Calendar;
import java.util.List;

import static java.lang.System.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ReportEngineTest {

    Store store = new MemStore();

    List<Employee> employees;

    Calendar now = Calendar.getInstance();

    DateTimeParser<Calendar> parser = new ReportDateTimeParser();

    @BeforeEach
    void init() {
        Employee worker1 = new Employee("Ivan", now, now, 200);
        Employee worker2 = new Employee("Dmitry", now, now, 1100);
        Employee worker3 = new Employee("Petr", now, now, 1300);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        employees = List.of(worker1, worker2, worker3);
    }

    @Test
    public void whenGeneratedItReport() {
        Report report = new ReportIt(parser);
        ReportEngine engine = new ReportEngine(report, store);
        StringBuilder expect = new StringBuilder("Name, Hired, Fired, Salary")
                .append(lineSeparator());
        for (Employee emp : employees) {
            expect.append(emp.getName()).append(",")
                    .append(parser.parse(emp.getHired())).append(",")
                    .append(parser.parse(emp.getFired())).append(",")
                    .append(emp.getSalary())
                    .append(lineSeparator());
        }
        assertThat(engine.getReport(em -> true, store)).isEqualTo(expect.toString());
    }

    @Test
    public void whenGeneratedHrReport() {
        Report report = new ReportHR();
        ReportEngine engine = new ReportEngine(report, store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(lineSeparator());
        for (Employee emp : employees) {
            expect.append(emp.getName()).append(" ")
                    .append(emp.getSalary())
                    .append(lineSeparator());
        }
        assertThat(engine.getReport(em -> true, store)).isEqualTo(expect.toString());
    }

    @Test
    public void whenGeneratedAccountingReport() {
        CurrencyConverter currencyConverter = new InMemoryCurrencyConverter();
        Report report = new ReportAccounting(parser, currencyConverter, Currency.RUB, Currency.EUR);
        ReportEngine engine = new ReportEngine(report, store);
        StringBuilder expect = new StringBuilder("Name; Hired; Fired; Salary;")
                .append(lineSeparator());
        for (Employee emp : employees) {
            expect.append(emp.getName()).append(" ")
                    .append(parser.parse(emp.getHired())).append(" ")
                    .append(parser.parse(emp.getFired())).append(" ")
                    .append(currencyConverter.convert(Currency.RUB, emp.getSalary(), Currency.EUR))
                    .append(lineSeparator());
        }
        assertThat(engine.getReport(em -> true, store)).isEqualTo(expect.toString());
    }
}