package ivan.polhniuk.ivanlogos.repository;


import ivan.polhniuk.ivanlogos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  UserRepository extends JpaRepository<User, Long>{

}
