package ivan.polahniuk.ivanLogos.repository;

import ivan.polahniuk.ivanLogos.entity.Product;
import ivan.polahniuk.ivanLogos.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository  extends JpaRepository<SubCategory, Long> {
    List<SubCategory> findByCategory_Id(Long id);
}
