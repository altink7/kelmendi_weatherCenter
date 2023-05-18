package at.altin.kelmendi_weathercenter.service;

import at.altin.kelmendi_weathercenter.model.WeatherInformation;
import at.altin.kelmendi_weathercenter.repo.WeatherInformationRepo;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        // initial Arrange
        MockitoAnnotations.openMocks(this);
        weatherService = new WeatherService(weatherInformationRepo);
    }

    @Test
    public void testAddWeatherData() {
        // Arrange
        WeatherInformation weatherInformation = getWeatherInformation();
        // Act
        weatherService.addWeatherData(weatherInformation);
        // Assert
        verify(weatherInformationRepo, times(1)).save(any(WeatherInformation.class));
        assertEquals("Vienna", weatherInformation.getCity());
    }


    @Test
    public void testGetAllWeatherData() {
        // Arrange
        WeatherInformation weatherInformation1 = getWeatherInformation();
        WeatherInformation weatherInformation2 = new WeatherInformation("Berlin", "Germany", "25", "15", null);
        List<WeatherInformation> weatherDataList = Arrays.asList(weatherInformation1, weatherInformation2);
        when(weatherInformationRepo.findAll()).thenReturn(weatherDataList);
        // Act
        List<WeatherInformation> result = weatherService.getAllWeatherData();
        // Assert
        assertEquals(weatherDataList, result);
        assertEquals("Germany", result.get(1).getCountry());
    }
    @Test
    public void testGetWeatherDataForCity() {
        // Arrange
        WeatherInformation weatherInformation1 = getWeatherInformation();
        WeatherInformation weatherInformation2 = new WeatherInformation("Vienna", "Austria", "25", "15", null);
        List<WeatherInformation> weatherDataList = Arrays.asList(weatherInformation1, weatherInformation2);
        when(weatherInformationRepo.findAll()).thenReturn(weatherDataList);
        // Act
        Object result = weatherService.getWeatherDataForCity("Vienna");
        // Assert
        assertEquals(weatherDataList, result);
        assertEquals("Austria", ((List<WeatherInformation>) result).get(1).getCountry());
    }

    @Test
    public void testGetWeatherDataForCountry() {
        // Arrange
        WeatherInformation weatherInformation1 = getWeatherInformation();
        WeatherInformation weatherInformation2 = new WeatherInformation("Berlin", "Austria", "25", "15", null);
        List<WeatherInformation> weatherDataList = Arrays.asList(weatherInformation1, weatherInformation2);
        when(weatherInformationRepo.findAll()).thenReturn(weatherDataList);
        // Act
        Object result = weatherService.getWeatherDataForCountry("Austria");
        // Assert
        assertEquals(weatherDataList, result);
        assertEquals("Berlin", ((List<WeatherInformation>) result).get(1).getCity());
    }

    @Test
    public void testClearWeatherData() {
        // Act
        weatherService.clearWeatherData();
        // Assert
        verify(weatherInformationRepo, times(1)).deleteAll();
        assertEquals(0, weatherInformationRepo.findAll().size());
    }

    @Test
    public void testGetLastWeatherData() {
        // Arrange
        WeatherInformation weatherInformation1 = getWeatherInformation();
        WeatherInformation weatherInformation2 = new WeatherInformation("Berlin", "Austria", "25", "15", null);
        List<WeatherInformation> weatherDataList = Arrays.asList(weatherInformation1, weatherInformation2);
        when(weatherInformationRepo.findAll()).thenReturn(weatherDataList);
        when(weatherInformationRepo.findFirstByOrderByIdDesc()).thenReturn(weatherInformation2);
        // Act
        Object result = weatherService.getLastWeatherData();
        // Assert
        assertEquals(weatherInformation2, result);
        assertEquals("Berlin", ((WeatherInformation) result).getCity());
    }

    @Test
    public void testCreateTestData(){
        // Act
        weatherService.createTestData();
        // Assert
        verify(weatherInformationRepo, times(14)).save(any(WeatherInformation.class));
    }

    private static WeatherInformation getWeatherInformation() {
        WeatherInformation weatherInformation = new WeatherInformation("Vienna", "Austria", "20", "10", null);
        return weatherInformation;
    }

}
