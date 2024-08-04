package com.example.demo.account;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {
    private final Set<Account> accounts = new HashSet<>();

    public AccountService() {
        accounts.add(new Account(UUID.randomUUID(), "Test 1", "123456"));
        accounts.add(new Account(UUID.randomUUID(), "Test 2", "12345622"));
    }

    public Set<Account> getAllAccounts() {
        return Collections.unmodifiableSet(accounts);
    }

    Account addAccount(Account account) {
        accounts.add(account);
        return account;
    }

    public Account getAccountName(String name) {
        return accounts.stream()
                .filter(account -> account.name().equals(name))
                .findFirst()
                .orElse(null);
    }
}
