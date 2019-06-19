package ivan.polhniuk.ivanlogos.repository;

import ivan.polhniuk.ivanlogos.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("from Product p where p.price between :from and :to")
    List<Product> findByPrice(@Param("from") Integer from, @Param("to") Integer to);

    @Query("from Product p join p.subCategory subC where subC.name like :name")
    List<Product> findBySubCategory(@Param("name") String name);

    @Query("from Product p")
    List<Product> getAll();
}
