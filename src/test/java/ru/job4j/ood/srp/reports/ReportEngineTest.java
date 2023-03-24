package ru.job4j.ood.srp.reports;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportEngineTest {

//    @Disabled
//    @Test
//    public void whenOldGenerated() {
//        MemStore store = new MemStore();
//        Calendar now = Calendar.getInstance();
//        Employee worker = new Employee("Ivan", now, now, 100);
//        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
//        store.add(worker);
//        Report engine = new ReportEngine(store, parser);
//        StringBuilder expect = new StringBuilder()
//                .append("Name; Hired; Fired; Salary;")
//                .append(System.lineSeparator())
//                .append(worker.getName()).append(" ")
//                .append(parser.parse(worker.getHired())).append(" ")
//                .append(parser.parse(worker.getFired())).append(" ")
//                .append(worker.getSalary())
//                .append(System.lineSeparator());
//        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
//    }

    @Test
    public void whenGeneratedHrReport() {
        Store store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 1200);
        Employee worker2 = new Employee("Dmitry", now, now, 1100);
        Employee worker3 = new Employee("Petr", now, now, 1300);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Report report = new ReportHR();
        ReportEngine engine = new ReportEngine(report, store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator())
                .append(worker1.getName()).append(" ")
                .append(worker1.getSalary())
                .append(System.lineSeparator())
                .append(worker3.getName()).append(" ")
                .append(worker3.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.getReport(em -> true, store)).isEqualTo(expect.toString());
    }
}