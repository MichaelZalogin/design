package ru.job4j.serialization;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Engine")
@XmlAccessorType(XmlAccessType.FIELD)
public class Engine {

    @XmlElement
    private int horsePower;

    @XmlElement
    private int amoungCilinders;

    @XmlElement
    private String brand;

    @XmlElement
    private boolean isPetrol;

    public Engine() {

    }

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