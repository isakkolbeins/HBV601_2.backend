package is.hi.hugbo2.backend.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import is.hi.hugbo2.backend.persistence.entities.Account;
import is.hi.hugbo2.backend.persistence.entities.Transaction;

import java.util.List;

/**
 * Repository for transactions
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Inserts a transaction into the DB
     * @param Transaction
     * @return
     */
    Transaction save(Transaction Transaction);

    /**
     * Removes a transaction in the DB
     * @param Transaction
     */
    void delete(Transaction Transaction);

    /**
     * Finds all transactions in DB
     * @return
     */
    List<Transaction> findAll();

    /**
     * Finds one transaction with ID
     * @param id
     * @return
     */
    @Query(value = "SELECT p FROM Transaction p WHERE p.id = ?1")
    Transaction findOne(Long id);

    /**
     * Finds all transactions with the same splitId
     * @param splitId
     * @return
     */
    List<Transaction> findBySplitId(Long splitId);
}
