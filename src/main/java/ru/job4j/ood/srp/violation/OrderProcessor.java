package ru.job4j.ood.srp.violation;

public class OrderProcessor {

    public void process(Order order) {
        if (order.isValid() && save(order)) {
            sendConfirmationEmail(order);
        }
    }

    private boolean save(Order order) {
        MySqlConnection connection = new MySqlConnection("database.url");
        return true;
    }

    private void sendConfirmationEmail(Order order) {
        String name = order.getCustomerName();
        String email = order.getCustomerEmail();
    }
}

class Order {
    public boolean isValid() {
        return true;
    }

    public String getCustomerEmail() {
        return "";
    }

    public String getCustomerName() {
        return "";
    }
}

class MySqlConnection {
    String database;

    public MySqlConnection(String database) {
        this.database = database;
    }
}