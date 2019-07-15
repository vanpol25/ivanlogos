package ivan.polahniuk.ivanLogos.repository;

import ivan.polahniuk.ivanLogos.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
