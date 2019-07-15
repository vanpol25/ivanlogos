package ivan.polahniuk.ivanLogos.repository;

import ivan.polahniuk.ivanLogos.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

//    List<Category> findAllByNameLike(String name);

}
