package github.zayn.designpattern.observermode.basic;

public class WeatherStation {
    public static void main(String[] args) {
        WeatherData2 weatherData = new WeatherData2();
        CurrentConditionDisplay2 currentConditionDisplay = new CurrentConditionDisplay2(weatherData);
        weatherData.setMeasurements(11, 111, 1111);
        weatherData.setMeasurements(22, 2222, 222222);
        weatherData.setMeasurements(33, 3333, 3333333);
    }
}
