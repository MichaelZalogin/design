package ru.job4j.ood.ocp.reports;

public class DiscountCalculator {

    /**
     * Если потребуется добавить новый тип скидки,
     * то нужно будет изменять код класса. Чтобы исправить это, можно использовать
     * паттерн "Стратегия" или создать абстрактный класс Discount и наследовать
     * от него конкретные типы скидок.
     */
    public double calculateDiscount(double price, int discountType) {
        double discount = 0;
        if (discountType == 1) {
            discount = 0.1;
        } else if (discountType == 2) {
            discount = 0.2;
        } else if (discountType == 3) {
            discount = 0.3;
        }
        return price * (1 - discount);
    }
}