package ivan.polahniuk.ivanLogos.repository;

import ivan.polahniuk.ivanLogos.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
