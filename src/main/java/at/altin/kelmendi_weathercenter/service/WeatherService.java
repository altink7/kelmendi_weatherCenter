package at.altin.kelmendi_weathercenter.service;

import at.altin.kelmendi_weathercenter.model.WeatherInformation;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Altin Kelmendi
 * @since 1.0.0
 *
 * <br> This class is for the business logic of the weather data
 * <ul>
 * <b>Requirements:</b> <br>
 * <li>1. Clients want to know the current temperature for a specific city</li>
 * <li>2. Weather stations want to send you data</li>
 * </ul>
 */
public class WeatherService {
    private final List<WeatherInformation> weatherInformation = new LinkedList<>();

    /**
     * @param city City to be added
     * @param country Country to be added
     * @param temperature Temperature to be added
     * @param windSpeed Wind speed to be added <br>
     * New weather data is added to the list
     */
    public void addWeatherData(String city, String country, String temperature, String windSpeed) {
        weatherInformation.add(new WeatherInformation(city, country, temperature, windSpeed, LocalDate.now()));
    }

    public void addWeatherData(WeatherInformation weatherInformation) {
        this.weatherInformation.add(weatherInformation);
    }

    /**
     * @return List of all weather data
     */
    public List<WeatherInformation> getAllWeatherData() {
        return weatherInformation;
    }

    /**
     * @return Last weather data from all Cities
     */
    public WeatherInformation getLastWeatherData() {
        return weatherInformation.get(weatherInformation.size() - 1);
    }

    /**
     * @param city City to be searched
     * @return Weather data for the given city
     */
    public Object getWeatherDataForCity(String city) {
        List<WeatherInformation> weatherDataForCity = new LinkedList<>();

        for (WeatherInformation weatherInformation : weatherInformation) {
            if (weatherInformation.getCity().equals(city)) {
                weatherDataForCity.add(weatherInformation);
            }
        }
        return weatherDataForCity.size()>0 ? weatherDataForCity : String.format("No weather data for %s", city);
    }

    /**
     * @param country Country to be searched
     * @return Weather data for the given country
     */
    public Object getWeatherDataForCountry(String country) {
        List<WeatherInformation> weatherDataForCountry = new LinkedList<>();
        for (WeatherInformation weatherInformation : weatherInformation) {
            if (weatherInformation.getCountry().equals(country)) {
                weatherDataForCountry.add(weatherInformation);
            }
        }
        return weatherDataForCountry.size()>0 ? weatherDataForCountry : String.format("No weather data for %s", country);
    }

    /**
     * clears all weather data
     */
    public void clearWeatherData() {
        weatherInformation.clear();
    }

     /**
     * this Method is used to create test data
     */
     public void createTestData() {
         addWeatherData("Vienna", "Austria", "20", "10");
         addWeatherData("Berlin", "Germany", "25", "15");
         addWeatherData("Paris", "France", "30", "20");
         addWeatherData("Stockholm", "Sweden", "35", "25");
         addWeatherData("London", "England", "40", "30");
         addWeatherData("Kopenhagen", "Denmark", "45", "35");
         addWeatherData("Warsaw", "Poland", "50", "40");
         addWeatherData("Washington", "USA", "55", "45");
         addWeatherData("New York", "USA", "60", "50");
         addWeatherData("Los Angeles", "USA", "65", "55");
         addWeatherData("San Francisco", "USA", "70", "60");
         addWeatherData("Chicago", "USA", "75", "65");
         addWeatherData("Toronto", "Canada", "80", "70");
         addWeatherData("Montreal", "Canada", "85", "75");
     }
}
