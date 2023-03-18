package ru.job4j.ood.srp.violation;

public class GodObject {
    private int temp;
    private int timer;
    private boolean condition;
    private OrderProcessor orderProcessor;

    public GodObject(int temp, int timer, boolean condition) {
        this.temp = temp;
        this.timer = timer;
        this.condition = condition;
    }

    public int mesureTemp() {
        int logic = 1;
        this.temp = logic;
        return logic;
    }

    public int showTimer() {
        int logic = 1;
        this.timer = logic;
        return logic;
    }

    public boolean isCondition() {
        OrderProcessor orderProcessor = new OrderProcessor();
        orderProcessor.process(new Order());
        this.condition = true;
        return true;
    }
}