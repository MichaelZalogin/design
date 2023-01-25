package ru.job4j.serialization;

public class Engine {
    private final int horsePower;
    private final int amoungCilinders;
    private final String brand;
    private final boolean isPetrol;

    public Engine(int horsePower, int amoungCilinders, String brand, boolean isPetrol) {
        this.horsePower = horsePower;
        this.amoungCilinders = amoungCilinders;
        this.brand = brand;
        this.isPetrol = isPetrol;
    }

    public int getHorsePower() {
        return this.horsePower;
    }

    public int getAmoungCilinders() {
        return this.amoungCilinders;
    }

    public String getBrand() {
        return this.brand;
    }

    public boolean isPetrol() {
        return this.isPetrol;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "horsePower=" + horsePower
                + ", amoungCilinder=" + amoungCilinders
                + ", brand='" + brand + '\''
                + ", isPetrol=" + isPetrol
                +
                '}';
    }
}