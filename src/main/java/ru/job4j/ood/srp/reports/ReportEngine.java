package ru.job4j.ood.srp.reports;

import java.util.function.Predicate;

public class ReportEngine {

    private final Report reportType;

    private final Store store;

    public ReportEngine(Report reportType, Store store) {
        this.reportType = reportType;
        this.store = store;
    }

    public String getReport(Predicate<Employee> filter, Store store) {
        return reportType.generate(filter, store);
    }
}