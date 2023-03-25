package ru.job4j.ood.ocp.reports;

import java.util.ArrayList;
import java.util.List;

/**
 * Если потребуется добавить новый тип товара, то нужно будет изменять код класса.
 * Чтобы исправить это, можно создать абстрактный класс Item и наследовать от него
 * конкретные типы товаров.
 */

public class ShoppingCart {

    private final List<Item> items = new ArrayList<Item>();

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public double calculateTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }

    private static class Item {

        private double price;

        public double getPrice() {
            return price;
        }
    }
}