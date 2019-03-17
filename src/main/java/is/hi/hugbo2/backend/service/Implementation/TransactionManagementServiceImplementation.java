package is.hi.hugbo2.backend.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import is.hi.hugbo2.backend.persistence.entities.Account;
import is.hi.hugbo2.backend.persistence.entities.Transaction;
import is.hi.hugbo2.backend.persistence.repositories.AccountRepository;
import is.hi.hugbo2.backend.persistence.repositories.TransactionRepository;
import is.hi.hugbo2.backend.service.TransactionManagementService;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionManagementServiceImplementation implements TransactionManagementService {

    // Instance Variables
    TransactionRepository repository;
    AccountRepository accountRepository;

    //Dependency Injection
    @Autowired
    public TransactionManagementServiceImplementation(TransactionRepository repository,  AccountRepository accountRepository) {
        this.repository = repository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Transaction save(Transaction Transaction) {

        return repository.save(Transaction);
    }

    @Override
    public void delete(Transaction Transaction) {

        repository.delete(Transaction);
    }


    @Override
    public List<Transaction> findAllReverseOrder(String user) {

        //Find all account the user has
        List<Account> accounts = accountRepository.findByUser1IsWithinOrUser2IsWithin(user);
        //Initialize and empty list
        List<Transaction> allTransactions = new ArrayList<>();

        // For every account, find all transactions and add them to the list
        for (Account account : accounts ) {
            allTransactions.addAll(account.getTransactionList());
        }

        //Sort the transactions by date and reverse the order showing the newest first
        allTransactions.sort((t1, t2) -> t1.getDate().compareTo(t2.getDate()));
        Collections.reverse(allTransactions);

        return allTransactions;
    }

    @Override
    public Transaction findOne(Long transactionId) {

        return repository.findOne(transactionId);
    }

    @Override
    public List<Transaction> findBySplitId(Long splitId) {

        return repository.findBySplitId(splitId);
    }
}
