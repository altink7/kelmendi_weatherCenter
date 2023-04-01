package at.altin.kelmendi_weathercenter.repo;

import at.altin.kelmendi_weathercenter.model.WeatherInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Altin Kelmendi
 * @since 1.0.0
 * <br> This class is for the database logic of the weather data
 */
@Repository
public interface WeatherInformationRepo extends JpaRepository<WeatherInformation,Long>{
    /**
     * @returns last weather data from all cities, order by Id
     */
    WeatherInformation findFirstByOrderByIdDesc();

}
