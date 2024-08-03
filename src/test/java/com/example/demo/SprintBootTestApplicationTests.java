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
        Assertions.assertEquals(2, Objects.requireNonNull(accounts.getBody()).size(), "Accounts - nie ma 2 elementów");
        Assertions.assertTrue(accounts.getBody().stream().anyMatch(account -> account.name().contains("Test 2")), "Accounts - nie zawiera Test 2");
    }

    @Test
    void testGetAccount() {
        ResponseEntity<Object> account = accountController.getAccount("123456");
        Assertions.assertNotNull(account, "Account - jest puste");
        Assertions.assertEquals(HttpStatusCode.valueOf(200), account.getStatusCode(), "Account - nie ma statusu 200");
    }

}
