package ru.job4j.ood.srp.reports;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static java.lang.System.lineSeparator;
import static org.assertj.core.api.Assertions.assertThat;

class ReportHRTest {

    @Test
    public void whenGeneratedHrReport() {
        Store store = new MemStore();
        List<Employee> employees;
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 1200);
        Employee worker2 = new Employee("Dmitry", now, now, 1100);
        Employee worker3 = new Employee("Petr", now, now, 1300);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        employees = List.of(worker2, worker1, worker3);
        Report report = new ReportHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(lineSeparator());
        for (Employee emp : employees) {
            expect.append(emp.getName()).append(" ")
                    .append(emp.getSalary())
                    .append(lineSeparator());
        }
        assertThat(report.generate(em -> true)).isEqualTo(expect.toString());
    }
}