package is.hi.hugbo2.backend.service;

import is.hi.hugbo2.backend.persistence.entities.Account;
import is.hi.hugbo2.backend.persistence.entities.Account;
import is.hi.hugbo2.backend.persistence.entities.Transaction;
import is.hi.hugbo2.backend.persistence.entities.User;

import java.util.List;

public interface AccountManagementService {

    /**
     * Save a {@link Account}
     * @param Account {@link Account} to be saved
     * @return {@link Account} that was saved
     */
    Account save(Account Account);

    /**
     * Delete {@link Account}
     * @param Account {@link Account} to be deleted
     */
    void delete(Account Account);

    /**
     * Get all {@link Account}s
     * @return A list of {@link Account}s
     */
    List<Account> findAll();

    /**
     * Find one  {@link Account}
     * @param accountId
     * @return
     */
    Account findOne(Long accountId);

    /**
     * Find by user {@link Account}
     * @param user
     * @return
     */
    //Account findByUser(User user);

    /**
     * Finds all transactions in Account
     * @param account
     * @return
     */
    //Transaction getTransactions(Account account);

    Account findAccountByUsers(String user1, String user2);

    void updateBalance(Double ammount, Account account);

    /**
     * Find by userId {@link Account}
     *      * @param User
     * @param user
     * @return
     */
    List<Account> findByUsername(String username);

    /**
     * Create new account and set netballance to 0 {@link Account}
     *      * @param User
     * @param user1
     * @param user2
     * @return
     */
    // void createNew(User user1, User user2);

}
