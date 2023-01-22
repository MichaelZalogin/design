package ru.job4j.queue;

import java.util.Queue;

public class AppleStore {
    private final Queue<Customer> queue;

    private final int count;

    public AppleStore(Queue<Customer> queue, int count) {
        this.queue = queue;
        this.count = count;
    }

    public String getLastHappyCustomer() {
        int i = count;
        while (i > 1) {
            queue.poll();
            i--;
        }
        return queue.element().name();
    }

    public String getLastUpsetCustomer() {
        int i = count;
        while (i > 0) {
            queue.poll();
            i--;
        }
        return queue.element().name();
    }
}