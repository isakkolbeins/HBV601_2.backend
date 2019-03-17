package is.hi.hugbo2.backend.service;

import is.hi.hugbo2.backend.persistence.entities.Transaction;

import java.util.List;

/**
 * Transaction Management Service
 */
public interface TransactionManagementService {

    /**
     * Save a {@link Transaction}
     * @param Transaction {@link Transaction} to be saved
     * @return {@link Transaction} that was saved
     */
    Transaction save(Transaction Transaction);

    /**
     * Delete {@link Transaction}
     * @param Transaction {@link Transaction} to be deleted
     */
    void delete(Transaction Transaction);

    /**
     * Finds transaction by splitId
     * @param splitId
     * @return
     */
    List<Transaction> findBySplitId(Long splitId);


    /**
     * Find one  {@link Transaction}
     * @param transactionId
     * @return
     */
    Transaction findOne(Long transactionId);


    /**
     * Finds all transactions in reverse order by date
     * @param user
     * @return
     */
    List<Transaction> findAllReverseOrder(String user);


}
