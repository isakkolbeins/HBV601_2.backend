package is.hi.hugbo2.backend.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import is.hi.hugbo2.backend.persistence.entities.Account;

import java.util.List;

/**
 * By extending the {@link JpaRepository} we have access to powerful methods.
 * For detailed information, see:
 * http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
 * http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 *
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account save(Account account);

    void delete(Account account);

    List<Account> findAll();


    @Query(value = "SELECT p FROM Account p WHERE p.id = ?1")
    Account findOne(Long id);


    @Query(value = "SELECT p FROM Account p WHERE ((user1 LIKE ?1) AND (user2 LIKE  ?2)) OR ((user1 LIKE ?2) AND  (user2 LIKE ?1))")
    Account findAccountByUsers(String user1, String user2);

    @Query(value = "SELECT p FROM Account p WHERE (user1 LIKE ?1) OR (user2 LIKE ?1)")
    List<Account> findByUser1IsWithinOrUser2IsWithin(String user);

}
