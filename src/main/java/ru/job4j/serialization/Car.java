package ru.job4j.serialization;

import org.json.JSONObject;

import java.io.StringWriter;
import java.util.Arrays;
import javax.xml.bind.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlElement
    private boolean rapid;

    @XmlElement
    private int engineVolume;

    @XmlElement
    private String number;

    private Engine engine;

    @XmlElementWrapper(name = "parameters")
    @XmlElement(name = "parameter")
    private String[] parameters;

    public Car() {
    }

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

    public static void main(String[] args) throws JAXBException {

        Engine nissanEngine = new Engine(160, 8, "NissanEngine", true);
        final Car nissan = new Car(true, 4, "f123as98", nissanEngine,
                new String[]{"red", "sedan"});

        JAXBContext context = JAXBContext.newInstance(Car.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(nissan, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject jsonCar = new JSONObject(nissan);
        System.out.println(jsonCar.toString());

        JSONObject jsonEngine = new JSONObject("{\"horsePower\":160,\"amoungCilinders\":8,"
                + "\"petrol\":true,\"brand\":\"NissanEngine\"}");
        System.out.println(jsonEngine);
    }
}
