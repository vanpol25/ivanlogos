package ivan.polahniuk.ivanLogos.repository;

import ivan.polahniuk.ivanLogos.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository  extends JpaRepository<SubCategory, Long> {
}
