package at.altin.kelmendi_weathercenter.service;

import at.altin.kelmendi_weathercenter.model.WeatherInformation;
import at.altin.kelmendi_weathercenter.repo.WeatherInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
@Service
public class WeatherService {
    WeatherInformationRepo weatherInformationRepo;

    @Autowired
    public WeatherService(WeatherInformationRepo weatherInformationRepo) {
        this.weatherInformationRepo = weatherInformationRepo;
    }

    /**
     * @param city City to be added
     * @param country Country to be added
     * @param temperature Temperature to be added
     * @param windSpeed Wind speed to be added <br>
     * New weather data is added to the list
     */
    public void addWeatherData(String city, String country, String temperature, String windSpeed) {
        weatherInformationRepo.save(new WeatherInformation(city, country, temperature, windSpeed, LocalDate.now()));
    }

    public void addWeatherData(WeatherInformation weatherInformation) {
        this.addWeatherData(weatherInformation.getCity(), weatherInformation.getCountry(), weatherInformation.getTemperature(), weatherInformation.getWindSpeed());
    }

    /**
     * @return List of all weather data
     */
    public List<WeatherInformation> getAllWeatherData() {
        return weatherInformationRepo.findAll();
    }

    /**
     * @return Last weather data from all Cities
     */
    public Object getLastWeatherData() {
        if(weatherInformationRepo.findAll().size() == 0) {
            return "no data, please add some data first";
        }else {
            return weatherInformationRepo.findFirstByOrderByIdDesc();
        }
    }

    /**
     * @param city City to be searched
     * @return Weather data for the given city
     */
    public Object getWeatherDataForCity(String city) {
        List<WeatherInformation> weatherDataForCity = weatherInformationRepo.findAll().stream().filter
                (weatherInformation -> weatherInformation.getCity().equals(city)).collect(Collectors.toCollection(LinkedList::new));

        return weatherDataForCity.size()>0 ? weatherDataForCity : String.format("No weather data for %s", city);
    }

    /**
     * @param country Country to be searched
     * @return Weather data for the given country
     */
    public Object getWeatherDataForCountry(String country) {
        List<WeatherInformation> weatherDataForCountry = weatherInformationRepo.findAll().stream().filter
                (weatherInformation -> weatherInformation.getCountry().equals(country)).collect(Collectors.toCollection(LinkedList::new));

        return weatherDataForCountry.size()>0 ? weatherDataForCountry : String.format("No weather data for %s", country);
    }

    /**
     * clears all weather data
     */
    public void clearWeatherData() {
        weatherInformationRepo.deleteAll();
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

     @Autowired
    public void setWeatherInformationRepo(WeatherInformationRepo weatherInformationRepo) {
        this.weatherInformationRepo = weatherInformationRepo;
    }

}
