package at.altin.kelmendi_weathercenter.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Altin Kelmendi
 * @since 1.0.0
 * This class is the model for weather data, it contains all the information about the weather
 */
@Entity(name = "weather_information")
public class WeatherInformation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)

    private Long id;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;
    @Column(name = "temperature")
    private String temperature;
    @Column(name = "wind_speed")
    private String windSpeed;
    @Column(name = "time")
    private LocalDate time;

    public WeatherInformation(String city, String country, String temperature, String windSpeed, LocalDate time) {
        this.city = city;
        this.country = country;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.time = time;
    }

    public WeatherInformation() {
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getTemperature() {
        return temperature;
    }
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    public String getWindSpeed() {
        return windSpeed;
    }
    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }
    public LocalDate getTime() {
        return time;
    }
    public void setTime(LocalDate time) {
        this.time = time;
    }
    @Override
    public String toString() {
        return "WeatherInformation [city=" + city + ", country=" + country + ", temperature=" + temperature
                + ", windSpeed=" + windSpeed + ", date=" +time +"] \n";
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
