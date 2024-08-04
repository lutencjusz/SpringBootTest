package com.example.demo;

import com.example.demo.account.Account;
import com.example.demo.account.AccountController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Set;

@SpringBootTest
class SprintBootTestApplicationTests {

    @Autowired
    private AccountController accountController;

    @Test
    void testGetAllAccounts() {
        ResponseEntity<Set<Account>> accounts = accountController.getAllAccounts();
        Assertions.assertNotNull(accounts, "Accounts - jest puste");
        Assertions.assertTrue(Objects.requireNonNull(accounts.getBody()).size() >= 2, "Accounts - nie ma 2 lub więcej elementów");
        Assertions.assertTrue(accounts.getBody().stream().anyMatch(account -> account.name().contains("Test 2")), "Accounts - nie zawiera Test 2");
    }

    @Test
    void testGetAccount() {
        ResponseEntity<Account> account = accountController.getAccountName("Test 1");
        Assertions.assertNotNull(account, "Account - jest puste");
        Assertions.assertEquals(HttpStatusCode.valueOf(200), account.getStatusCode(), "Account - nie ma statusu 200");
        Assertions.assertNotNull(account.getBody(), "Account - nie ma ciała");
        Assertions.assertTrue(Objects.requireNonNull(account.getBody()).name().contains("Test 1"), "Account - nie zawiera Test 1");
    }

}
