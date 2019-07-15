package ivan.polahniuk.ivanLogos.repository;

import ivan.polahniuk.ivanLogos.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
