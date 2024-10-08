package com.example.demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Account>> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping(value = "/accounts/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getAccountName(@PathVariable("name") String name) {
        return new ResponseEntity<>(accountService.getAccountName(name), HttpStatus.OK);
    }

    @GetMapping(value = "/accounts/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getHello() {
        accountService.createExampleData();
        return new ResponseEntity<>("Dodałem dwa rekordy", HttpStatus.OK);
    }

    @PostMapping(value = "/accounts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.addAccount(account), HttpStatus.CREATED);
    }

    @PutMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        return new ResponseEntity<>(accountService.updateAccount(account), HttpStatus.OK);
    }

    @DeleteMapping(value = "/accounts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> deleteAccount(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(accountService.deleteAccount(id), HttpStatus.OK);
    }
}