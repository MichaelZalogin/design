package ru.job4j.ood.srp.reports;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.reports.converter.Currency;
import ru.job4j.ood.srp.reports.converter.CurrencyConverter;
import ru.job4j.ood.srp.reports.converter.InMemoryCurrencyConverter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.System.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportEngineTest {

    Store store = new MemStore();

    List<Employee> employees = new ArrayList<>();

    Calendar now = Calendar.getInstance();

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

    @Disabled
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
//        Report engine = new ReportEngine(store, parser);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(" ")
                .append(parser.parse(worker.getHired())).append(" ")
                .append(parser.parse(worker.getFired())).append(" ")
                .append(worker.getSalary())
                .append(System.lineSeparator());
//        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenGeneratedItReport() {
        Report report = new ReportIt();
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
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
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