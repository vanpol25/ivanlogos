package ivan.polhniuk.ivanlogos.repository;

import ivan.polhniuk.ivanlogos.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
