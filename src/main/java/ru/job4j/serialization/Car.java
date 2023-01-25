package ru.job4j.serialization;

import java.util.Arrays;

public class Car {
    private final boolean rapid;
    private final int engineVolume;
    private final String number;
    private final Engine engine;
    private final String[] parameters;

    public Car(boolean rapid, int engineVolume, String number, Engine engine, String[] parameters) {
        this.rapid = rapid;
        this.engineVolume = engineVolume;
        this.number = number;
        this.engine = engine;
        this.parameters = parameters;
    }

    public boolean isRapid() {
        return this.rapid;
    }

    public int getEngineVolume() {
        return this.engineVolume;
    }

    public String getNumber() {
        return this.number;
    }

    public Engine getEngine() {
        return this.engine;
    }

    public String[] getParameters() {
        return this.parameters;
    }

    @Override
    public String toString() {
        return "Car{"
                + "rapid=" + rapid
                + ", engineVolume=" + engineVolume
                + ", number='" + number + '\''
                + ", engine=" + engine
                + ", parameters=" + Arrays.toString(parameters)
                +
                '}';
    }
}
