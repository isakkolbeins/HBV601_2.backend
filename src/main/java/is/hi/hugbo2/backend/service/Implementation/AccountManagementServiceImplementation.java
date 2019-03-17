package is.hi.hugbo2.backend.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import is.hi.hugbo2.backend.persistence.entities.Account;
import is.hi.hugbo2.backend.persistence.entities.Transaction;
import is.hi.hugbo2.backend.persistence.entities.User;
import is.hi.hugbo2.backend.persistence.repositories.AccountRepository;
import is.hi.hugbo2.backend.persistence.repositories.TransactionRepository;
import is.hi.hugbo2.backend.service.AccountManagementService;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class AccountManagementServiceImplementation implements AccountManagementService {

    // Instance Variables
    AccountRepository repository;

    //Dependency Injection
    @Autowired
    public AccountManagementServiceImplementation(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account save(Account account) {
        return repository.save(account);
    }

    @Override
    public void delete(Account account) {
        repository.delete(account);
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public Account findOne(Long accountId) {
        return repository.findOne(accountId);
    }

    @Override
    public Account findAccountByUsers(String user1, String user2) {
        return repository.findAccountByUsers(user1, user2);
    }

    @Override
    public void updateBalance(Double amount, Account account) {
        //calculate and change the amount of the account
        Double newAmount = account.getNetBalance() + amount;
        account.setNetBalance(newAmount);
        repository.save(account);
    }

    @Override
    public List<Account> findByUsername(String username) {
        //Find all accounts
        List<Account> allAccounts = repository.findAll();

        //Find all accunts that the user has
        List<Account> userAccounts = new ArrayList<>();

        for (Account account : allAccounts) {
            if (account.getUser1().equals(username) || account.getUser2().equals(username)) {
                userAccounts.add(account);
            }
        }
        return userAccounts;
    }
}
