package is.hi.hugbo2.backend.controllers;

import com.google.gson.Gson;
import is.hi.hugbo2.backend.persistence.entities.Account;
import is.hi.hugbo2.backend.persistence.entities.Transaction;
import is.hi.hugbo2.backend.persistence.entities.User;
import is.hi.hugbo2.backend.service.AccountManagementService;
import is.hi.hugbo2.backend.service.TransactionManagementService;
import is.hi.hugbo2.backend.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class mainContoller {

    //Instance Variables
    private UserManagementService userManagementService;
    private AccountManagementService accountManagementService;
    private TransactionManagementService transactionManagementService;

    private Gson gson = new Gson();


    //Dependency Injection
    @Autowired
    public void AccountsController( UserManagementService userManagementService,
                                    AccountManagementService accountManagementService,
                                    TransactionManagementService transactionManagementService,
                                    Gson gson) {
        this.userManagementService = userManagementService;
        this.accountManagementService = accountManagementService;
        this.transactionManagementService = transactionManagementService;
        this.gson = gson;
    }

    // User ---
    @PostMapping("/user/new")
    public String saveUser(@RequestBody User newUser){

        // Validate username ??

        return gson.toJson(userManagementService.save(newUser));
    }
    @GetMapping("/user/{userId}")
    public String getUser(@PathVariable("userId") Long userId){
        return gson.toJson(userManagementService.findByUserId(userId));
    }


    // Transaction ---
    @PostMapping("/transaction/new")
    public String saveTransaction(@RequestBody Transaction newTransaction){

        Transaction savedTrans = transactionManagementService.save(newTransaction);
        Long accountId = savedTrans.getAccountId();

        Account currAccount = accountManagementService.findOne(accountId);
        ArrayList<Long> transactionList = currAccount.getTransactionList();
        Double currBalance = currAccount.getNetBalance();
        transactionList.add(newTransaction.getId());
        currAccount.setTransactionList(transactionList);
        currAccount.setNetBalance(currBalance + savedTrans.getAmount());
        accountManagementService.save(currAccount);
        return gson.toJson(savedTrans);
    }

    @GetMapping("/transaction/{transactionId}")
    public String getTransaction(@PathVariable("transactionId") Long transactionId){
        return gson.toJson(transactionManagementService.findOne(transactionId));
    }



    @PostMapping("/user/{userId}/add/{friendName}")
    public String addFriend(@PathVariable ("userId") Long userId, @PathVariable("friendName") String friendName){

        User myUser = userManagementService.findByUserId(userId);
        User friend = userManagementService.findByUsername(friendName);

        if (friend == null) {
            return "{error: 'no user with username: " + friendName+ "' }";
        } else if (myUser.getFriendlist().contains(friendName)){
            return "{error: 'user with username: " + friendName+ " is already your friend' }";
        }
        Account newAccount = new Account(myUser.getUsername(), friendName);
        newAccount.setNetBalance(0.0);
        newAccount = accountManagementService.save(newAccount);
        userManagementService.addFriend(myUser, friend);
        return gson.toJson(newAccount);
    }


    @GetMapping("/account/{accountId}")
    public String getAccountInfo(@PathVariable("accountId") Long accountId){
        return gson.toJson(accountManagementService.findOne(accountId));
    }

    @GetMapping("/user/{userId}/accounts")
    public String getAccount(@PathVariable("userId") Long userId){
        User myUser = userManagementService.findByUserId(userId);
        return gson.toJson(accountManagementService.findByUsername(myUser.getUsername()));
    }


}
