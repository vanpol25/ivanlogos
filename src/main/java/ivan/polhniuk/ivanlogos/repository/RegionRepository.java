package ivan.polhniuk.ivanlogos.repository;

import ivan.polhniuk.ivanlogos.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
