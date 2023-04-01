package at.altin.kelmendi_weathercenter.controller;

import at.altin.kelmendi_weathercenter.model.WeatherInformation;
import at.altin.kelmendi_weathercenter.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
/**
 * @author Altin Kelmendi
 * @since 1.0.0
 * <br> This class is for the REST API of the weather data
 * <ul>
 *     <b>Requirements:</b> <br>
 *     <li>1. Clients want to know the current temperature for a specific city</li>
 *     <li>2. Weather stations want to send you data</li>
 * </ul>
 * <br> <b>Extras:</b>
 * <ul>
 *     <li>1. Clients want to know the current temperature for a specific country</li>
 *     <li>2. Clients want to know the current temperature for all cities</li>
 *     <li>3. Clients want to know the current temperature for the last Data</li>
 *     <li>4. Clients want to know the current temperature for the last Data from a specific city</li>
 *     <li>5. Clients want to empty the list </li>
 * </ul>
 *
 */
@RequestMapping("/api/weather")
@RestController
public class WeatherController {
    WeatherService weatherService;


    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * adds and return all the test data
     */
    @GetMapping("/test")
    public String addTestData(){
        weatherService.createTestData();
        return weatherService.getAllWeatherData().toString();
    }

    /**
     * @return List of all weather data
     */
    @GetMapping("/all")
    public String getAllWeatherData() {
        return weatherService.getAllWeatherData().toString();
    }

    /**
     * @return Last weather data from all Cities
     */
    @GetMapping("/last")
    public String getLastWeatherData() {
        return weatherService.getLastWeatherData().toString();
    }
    /**
     * @param city City to be searched
     * @return Weather data for the given city
     */
    @GetMapping("/city/{city}")
    public String getWeatherDataForCity(@PathVariable String city) {
        return weatherService.getWeatherDataForCity(city).toString();
    }
    /**
     * @param country Country to be searched
     * @return Weather data for the given country
     */
    @GetMapping("/country/{country}")
    public String getWeatherDataForCountry( @PathVariable("country") String country) {
        return weatherService.getWeatherDataForCountry(country).toString();
    }
    /**
     * @param city City to be added
     * @param country Country to be added
     * @return Success message
     */
    @GetMapping("/{city}/{country}/{temperature}/{windSpeed}")
    public String addWeatherDataUrl( @PathVariable("city") String city, @PathVariable("country") String country, @PathVariable("temperature") String temperature, @PathVariable("windSpeed") String windSpeed) {
        weatherService.addWeatherData(city, country, temperature, windSpeed);
        return "Added weather data for " + weatherService.getLastWeatherData();
    }
    /**
     * Deletes all weather data
     * @return Success message
     */
    @GetMapping("/delete")
    public String deleteAllWeatherData() {
        weatherService.clearWeatherData();
        return "Deleted all weather data";
    }
    /**
     * @param weatherInformation Weather data to be added
     * @return Success message
     */
    @PostMapping("/add")
    public String addWeatherData(@RequestBody WeatherInformation weatherInformation) {
        weatherService.addWeatherData(weatherInformation);
        return "Added weather data for " + weatherInformation.getCity();
    }
}
