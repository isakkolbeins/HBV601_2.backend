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

    // User ---
    @PostMapping("/user/new")
    public String saveUser(@RequestBody User newUser){


        // Validate username ??

        return gson.toJson(userManagementService.save(newUser));
    }


    @GetMapping("/login/{username}")
    public String saveUser(@PathVariable("username") String username ){

        // Validate pw ??
        return gson.toJson(userManagementService.findByUsername(username));
    }


    @GetMapping("/user/{userId}")
    public String getUser(@PathVariable("userId") Long userId){
        return gson.toJson(userManagementService.findByUserId(userId));
    }


    // Transaction ---
    @PostMapping("user/{userId}/transaction/new")
    public String saveTransaction(@PathVariable("userId") Long userId, @RequestBody Transaction newTransaction){

        Account currAccount = accountManagementService.findOne(newTransaction.getAccountId());
        User myUser = userManagementService.findByUserId(userId);
        Transaction savedTrans = transactionManagementService.save(newTransaction);

        // Long accountId = savedTrans.getAccountId();
        ArrayList<Long> transactionList = currAccount.getTransactionList();
        transactionList.add(newTransaction.getId());
        currAccount.setTransactionList(transactionList);

        Double currBalance = currAccount.getNetBalance();
        if (currAccount.getUser1().equals(myUser.getUsername())) {
            newTransaction.setAmount(-1*newTransaction.getAmount());
        }
        currAccount.setNetBalance(currBalance + newTransaction.getAmount());
        accountManagementService.save(currAccount);
        return gson.toJson(savedTrans);
    }

    @GetMapping("/user/{userId}/transaction/{transactionId}")
    public String getTransaction(@PathVariable ("userId") Long userId, @PathVariable("transactionId") Long transactionId){

        User myUser = userManagementService.findByUserId(userId);
        Transaction currTransaction = transactionManagementService.findOne(transactionId);
        Account currAccount = accountManagementService.findOne(transactionId);
        if (currAccount.getUser1().equals(myUser.getUsername())) {
            currTransaction.setAmount( -1*currTransaction.getAmount());
        }

        return gson.toJson(currTransaction);
    }


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


    @GetMapping("/user/{userId}/account/{accountId}")
    public String getAccountInfo(@PathVariable("userId") Long userId, @PathVariable("accountId") Long accountId){
        Account currAccount = accountManagementService.findOne(accountId);
        User myUser = userManagementService.findByUserId(userId);
        // user  * -1  --- user 2 normal
        if (currAccount.getUser1().equals(myUser.getUsername())) {
            currAccount.setNetBalance(-1*currAccount.getNetBalance());
        }
        return gson.toJson(currAccount);
    }

    @GetMapping("/user/{userId}/accounts")
    public String getAccounts(@PathVariable("userId") Long userId){
        User myUser = userManagementService.findByUserId(userId);
        List<Account> accountList = accountManagementService.findByUsername(myUser.getUsername());
        String[] accountIdList = new String[accountList.size()];
        //String accountIdList = "";
        for (int i = 0; i < accountList.size(); i++){
            accountIdList[i] = accountList.get(i).getId().toString();
            //accountIdList = accountIdList + ", " + accountList.get(i).getId();
        }
        return "{accounts: [" + String.join(",", accountIdList) +"]}";
    }



}
