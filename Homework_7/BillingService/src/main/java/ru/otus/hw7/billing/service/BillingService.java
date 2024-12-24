package ru.otus.hw7.billing.service;

import org.springframework.stereotype.Service;
import ru.otus.hw7.billing.model.Account;
import ru.otus.hw7.billing.repository.AccountRepository;

import java.util.Optional;

@Service
public class BillingService {
    private final AccountRepository accountRepository;

    public BillingService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(String username) {
        Account account = new Account();
        account.setUsername(username);
        account.setBalance(0.0);
        return accountRepository.save(account);
    }

    public void refillAccount(String username, Double amount) {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    public void withdrawMoney(String username, Double amount) {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            if (account.getBalance() >= amount) {
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
            } else {
                throw new RuntimeException("Insufficient balance");
            }
        } else {
            throw new RuntimeException("Account not found");
        }
    }
}