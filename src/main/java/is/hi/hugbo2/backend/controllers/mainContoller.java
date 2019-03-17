package is.hi.hugbo2.backend.controllers;

import com.google.gson.Gson;
import is.hi.hugbo2.backend.persistence.entities.User;
import is.hi.hugbo2.backend.service.AccountManagementService;
import is.hi.hugbo2.backend.service.TransactionManagementService;
import is.hi.hugbo2.backend.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

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

    @GetMapping("/user")
    public String getUser(@RequestParam("id") String id){

        User currUser = userManagementService.findByUserId(Long.parseLong(id));

        String jsonUser =  gson.toJson(currUser);
        System.out.println(jsonUser);

        return "user: "+ currUser.getFirstname()+ " gson: " ;
    }

    @GetMapping("/transaction")
    public String getTransaction(@RequestParam("id") String id){
        return "transaction with id ="+ id;
    }

    @GetMapping("/account")
    public String getAccount(@RequestParam("id") String id){
        return "account with id ="+ id;
    }


}
