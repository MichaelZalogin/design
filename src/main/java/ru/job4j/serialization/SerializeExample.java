package ru.job4j.serialization;

import com.google.gson.*;

public class SerializeExample {
    public static void main(String[] args) {
        Engine nissanEngine = new Engine(160, 8, "NissanEngine", true);
        final Car nissan = new Car(true, 4, "f123as98", nissanEngine,
                new String[]{"red", "sedan"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(nissan));

        String carJSON = "{"
                + "\"rapid\":true,"
                + "\"engineVolume\":4,"
                + "\"number\":\"f123as98\","
                + "\"engine\":"
                + "{"
                + "\"horsePower\":160,"
                + "\"amoungCilinders\":8,"
                + "\"brand\":\"NissanEngine\","
                + "\"isPetrol\":true"
                + "},"
                + "\"parameters\":"
                + "[\"red\",\"sedan\"]"
                + "}";

        final Car nissanMod = gson.fromJson(carJSON, Car.class);
        System.out.println(nissanMod);
    }
}
