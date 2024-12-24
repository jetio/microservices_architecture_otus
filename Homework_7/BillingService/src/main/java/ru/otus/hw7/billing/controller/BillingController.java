package ru.otus.hw7.billing.controller;

import org.springframework.web.bind.annotation.*;
import ru.otus.hw7.billing.model.Account;
import ru.otus.hw7.billing.service.BillingService;

@RestController
@RequestMapping("/hw7/api/billing")
public class BillingController {
    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping("/register")
    public Account registerAccount(@RequestParam String username) {
        return billingService.registerAccount(username);
    }

    @PostMapping("/refill")
    public String refillAccount(@RequestParam String username, @RequestParam Double amount) {
        billingService.refillAccount(username, amount);
        return "Amount " + amount + " refilled successfully";
    }

    @PostMapping("/withdraw")
    public String withdrawMoney(@RequestParam String username, @RequestParam Double amount) {
        billingService.withdrawMoney(username, amount);
        return "Amount " + amount + " withdrawn successfully";
    }
}