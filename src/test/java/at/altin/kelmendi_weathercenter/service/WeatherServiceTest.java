package at.altin.kelmendi_weathercenter.service;

import at.altin.kelmendi_weathercenter.model.WeatherInformation;
import at.altin.kelmendi_weathercenter.repo.WeatherInformationRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author Altin Kelmendi
 * @since 1.0.0
 * <br> This class is to test the business logic of the weather data
 */
public class WeatherServiceTest {

    @Mock
    private WeatherInformationRepo weatherInformationRepo;

    private WeatherService weatherService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        weatherService = new WeatherService(weatherInformationRepo);
    }

    @Test
    public void testAddWeatherData() {
        WeatherInformation weatherInformation = getWeatherInformation();
        weatherService.addWeatherData(weatherInformation);
        verify(weatherInformationRepo, times(1)).save(any(WeatherInformation.class));
    }


    @Test
    public void testGetAllWeatherData() {
        WeatherInformation weatherInformation1 = getWeatherInformation();
        WeatherInformation weatherInformation2 = new WeatherInformation("Berlin", "Germany", "25", "15", null);
        List<WeatherInformation> weatherDataList = Arrays.asList(weatherInformation1, weatherInformation2);
        when(weatherInformationRepo.findAll()).thenReturn(weatherDataList);
        List<WeatherInformation> result = weatherService.getAllWeatherData();
        assertEquals(weatherDataList, result);
    }
    @Test
    public void testGetWeatherDataForCity() {
        WeatherInformation weatherInformation1 = getWeatherInformation();
        WeatherInformation weatherInformation2 = new WeatherInformation("Vienna", "Austria", "25", "15", null);
        List<WeatherInformation> weatherDataList = Arrays.asList(weatherInformation1, weatherInformation2);
        when(weatherInformationRepo.findAll()).thenReturn(weatherDataList);
        Object result = weatherService.getWeatherDataForCity("Vienna");
        assertEquals(weatherDataList, result);
    }

    @Test
    public void testGetWeatherDataForCountry() {
        WeatherInformation weatherInformation1 = getWeatherInformation();
        WeatherInformation weatherInformation2 = new WeatherInformation("Berlin", "Austria", "25", "15", null);
        List<WeatherInformation> weatherDataList = Arrays.asList(weatherInformation1, weatherInformation2);
        when(weatherInformationRepo.findAll()).thenReturn(weatherDataList);
        Object result = weatherService.getWeatherDataForCountry("Austria");
        assertEquals(weatherDataList, result);
    }

    @Test
    public void testClearWeatherData() {
        weatherService.clearWeatherData();
        verify(weatherInformationRepo, times(1)).deleteAll();
    }

    private static WeatherInformation getWeatherInformation() {
        WeatherInformation weatherInformation = new WeatherInformation("Vienna", "Austria", "20", "10", null);
        return weatherInformation;
    }

}
