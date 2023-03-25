package ru.job4j.ood.srp.reports;

import java.util.function.Predicate;

public class ReportEngine {

    private final Report reportType;

    public ReportEngine(Report reportType) {
        this.reportType = reportType;
    }

    public String getReport(Predicate<Employee> filter) {
        return reportType.generate(filter);
    }
}