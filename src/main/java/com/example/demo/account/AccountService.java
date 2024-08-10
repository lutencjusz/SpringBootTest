package com.example.demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public void createExampleData() {
        accountRepository.save(new Account(UUID.randomUUID(), "Test_1", "123456"));
        accountRepository.save(new Account(UUID.randomUUID(), "Test 2", "12345622"));
    }

    public Set<Account> getAllAccounts() {
        return new HashSet<>(accountRepository.findAll());
    }

    Account addAccount(Account account) {
        accountRepository.save(account);
        return account;
    }

    public Account getAccountName(String name) {
        return accountRepository.findAll().stream()
                .filter(account -> account.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public Account getAccount(UUID id) {
        return accountRepository.findById(id)
                .orElse(null);
    }

    public Account deleteAccount(UUID id) {
        accountRepository.findById(id).ifPresent(account -> accountRepository.deleteById(id));
        return null;
    }

    public Account updateAccount(Account account) {
        Account existingAccount = accountRepository.findById(account.getId()).orElse(null);
        if (existingAccount == null) {
            return null;
        }
        existingAccount.setName(account.getName());
        existingAccount.setAccountNumber(account.getAccountNumber());
        accountRepository.save(existingAccount);
        return existingAccount;
    }
}
