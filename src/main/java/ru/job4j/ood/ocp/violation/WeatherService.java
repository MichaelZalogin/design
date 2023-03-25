package ru.job4j.ood.ocp.violation;

public class WeatherService {

    /**
     * Если потребуется добавить новый город, то нужно будет изменять код класса.
     * Чтобы исправить это, можно создать интерфейс WeatherProvider и реализовать
     * его для каждого города. Затем можно создать фабричный класс, который будет
     * создавать соответствующего провайдера в зависимости от города.
     */

    public String getWeatherForecast(String location) {
        if (location.equals("Moscow")) {
            return "The weather in Moscow is sunny.";
        } else if (location.equals("London")) {
            return "The weather in London is rainy.";
        } else if (location.equals("New York")) {
            return "The weather in New York is cloudy.";
        }
        return "Unknown location.";
    }

}