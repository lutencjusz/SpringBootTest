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

    public Optional<Object> getAccount(String id) {
        return Optional.of(accounts.stream()
                .filter(account -> account.id().toString().equals(id))
                .findFirst());
    }
}
