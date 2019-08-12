package ivan.polahniuk.ivanLogos.repository;


import ivan.polahniuk.ivanLogos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface  UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String name);

    boolean existsByUsername(String name);

}
