package ivan.polhniuk.ivanlogos.repository;

import ivan.polhniuk.ivanlogos.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
