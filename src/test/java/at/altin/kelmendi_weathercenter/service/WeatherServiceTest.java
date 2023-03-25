package at.altin.kelmendi_weathercenter.service;

import at.altin.kelmendi_weathercenter.model.WeatherInformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherServiceTest {
    WeatherService service;
    WeatherInformation weatherInformation;
    @BeforeEach
    void setUp() {
        service = new WeatherService();
        weatherInformation = new WeatherInformation("city", "country", "temperature", "windSpeed", LocalDate.now());
    }

    @Test
    void addWeatherData() {
        service.addWeatherData("city", "country", "temperature", "windSpeed");
        assertEquals(1, service.getAllWeatherData().size());
        assertEquals("city", service.getAllWeatherData().get(0).getCity());
        assertEquals("temperature", service.getAllWeatherData().get(0).getTemperature());
    }

    @Test
    void testAddWeatherData() {
        service.addWeatherData(weatherInformation);
        assertEquals(1, service.getAllWeatherData().size());
        assertEquals(weatherInformation, service.getLastWeatherData());
    }

    @Test
    void getAllWeatherData() {
        service.createTestData();
        assertEquals(14, service.getAllWeatherData().size());
    }

    @Test
    void getWeatherDataForCity() {
        service.addWeatherData(weatherInformation);
        service.getWeatherDataForCity("city");
        LinkedList<WeatherInformation> result = new LinkedList<>();
        result.add(weatherInformation);

        assertEquals(result,service.getWeatherDataForCity("city"));
    }

    @Test
    void getWeatherDataForCountry() {
        service.addWeatherData(weatherInformation);
        service.getWeatherDataForCountry("country");
        LinkedList<WeatherInformation> result = new LinkedList<>();
        result.add(weatherInformation);
        assertEquals(result,service.getWeatherDataForCountry("country"));
    }

    @Test
    void clearWeatherData() {
        service.addWeatherData(weatherInformation);
        service.clearWeatherData();
        assertEquals(0, service.getAllWeatherData().size());
    }
}