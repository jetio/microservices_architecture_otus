package ru.otus.hw8.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @PostMapping("/create")
    public String createPayment() {
        // Logic to create a payment
        System.out.println("Payment created");
        return "Payment Created";
    }

    @PostMapping("/cancel")
    public String cancelPayment() {
        // Logic to cancel a payment
        System.out.println("Payment canceled");
        return "Payment Canceled";
    }
}