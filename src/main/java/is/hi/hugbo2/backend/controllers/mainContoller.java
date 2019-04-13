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
import java.util.List;

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

    // Creating new user
    @PostMapping("/user/new")
    public String saveUser(@RequestBody User newUser){
        return gson.toJson(userManagementService.save(newUser));
    }

    // Gets user by username
    @GetMapping("/login/{username}")
    public String saveUser(@PathVariable("username") String username ){
        return gson.toJson(userManagementService.findByUsername(username));
    }

    // Gets user by user id
    @GetMapping("/user/{userId}")
    public String getUser(@PathVariable("userId") Long userId){
        return gson.toJson(userManagementService.findByUserId(userId));
    }


    // Creates  a new transaction
    @PostMapping("user/{userId}/transaction/new")
    public String saveTransaction(@PathVariable("userId") Long userId, @RequestBody Transaction newTransaction){

        Account currAccount = accountManagementService.findOne(newTransaction.getAccountId());
        User myUser = userManagementService.findByUserId(userId);
        Transaction savedTrans = transactionManagementService.save(newTransaction);

        ArrayList<Long> transactionList = currAccount.getTransactionList();
        transactionList.add(savedTrans.getId());
        currAccount.setTransactionList(transactionList);

        Double currBalance = currAccount.getNetBalance();
        if (currAccount.getUser1().equals(myUser.getUsername())) {
            savedTrans.setAmount(-1*savedTrans.getAmount());
        }
        currAccount.setNetBalance(currBalance + savedTrans.getAmount());
        accountManagementService.save(currAccount);
        return gson.toJson(savedTrans);
    }

    // Gets transaction by id
    @GetMapping("/user/{userId}/transaction/{transactionId}")
    public String getTransaction(@PathVariable ("userId") Long userId, @PathVariable("transactionId") Long transactionId){
        User myUser = userManagementService.findByUserId(userId);
        Transaction currTransaction = transactionManagementService.findOne(transactionId);
        Account currAccount = accountManagementService.findOne(currTransaction.getAccountId());
        Double currAmount = currTransaction.getAmount();
        if (currAccount.getUser1().equals(myUser.getUsername())) {
            currTransaction.setAmount( -1*currAmount);
        }
        return gson.toJson(currTransaction);
    }


    // Adds a user to friendlist, and creates an account amongst them
    @PostMapping("/user/{userId}/add/{friendName}")
    public String addFriend(@PathVariable ("userId") Long userId, @PathVariable("friendName") String friendName){

        User myUser = userManagementService.findByUserId(userId);
        User friend = userManagementService.findByUsername(friendName);
        if (friend == null) {
            return "{error: 'no user with username: " + friendName+ "' }";
        } else if (myUser.getFriendlist().contains(friend.getId())){
            return "{error: 'user with username: " + friendName+ " is already your friend' }";
        } else if (myUser == friend){
            return "{error: '"+ friendName + " is your username silly' }";
        }
        Account newAccount = new Account(myUser.getUsername(), friendName);
        newAccount.setNetBalance(0.0);
        newAccount = accountManagementService.save(newAccount);
        userManagementService.addFriend(myUser, friend);
        return gson.toJson(newAccount);
    }


    // Gets account by id
    @GetMapping("/user/{userId}/account/{accountId}")
    public String getAccountInfo(@PathVariable("userId") Long userId, @PathVariable("accountId") Long accountId){
        Account currAccount = accountManagementService.findOne(accountId);
        User myUser = userManagementService.findByUserId(userId);
        if (currAccount.getUser1().equals(myUser.getUsername())) {
            currAccount.setNetBalance(-1*currAccount.getNetBalance());
        }
        return gson.toJson(currAccount);
    }

    // Returns a list of users account id´s
    @GetMapping("/user/{userId}/accounts")
    public String getAccounts(@PathVariable("userId") Long userId){
        User myUser = userManagementService.findByUserId(userId);
        List<Account> accountList = accountManagementService.findByUsername(myUser.getUsername());
        String[] accountIdList = new String[accountList.size()];
        for (int i = 0; i < accountList.size(); i++){
            accountIdList[i] = accountList.get(i).getId().toString();
        }
        return "{accounts: [" + String.join(",", accountIdList) +"]}";
    }
}
