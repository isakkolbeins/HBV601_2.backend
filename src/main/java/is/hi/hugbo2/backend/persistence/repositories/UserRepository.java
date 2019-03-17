package is.hi.hugbo2.backend.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import is.hi.hugbo2.backend.persistence.entities.User;

import java.util.List;


/**
 * By extending the {@link JpaRepository} we have access to powerful methods.
 * For detailed information, see:
 * http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
 * http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User save(User User);

    void delete(User User);


    @Query(value = "SELECT p FROM User p WHERE p.id = ?1")
    User findOne(Long id);

    @Query(value = "SELECT p FROM User p WHERE p.id = ?1")
    User findByUserId(Long id);


    // Boolean validateUser(String username, String password);

    @Query(value = "SELECT p FROM User p WHERE p.username = ?1")
    User findByUsername(String username);


}
