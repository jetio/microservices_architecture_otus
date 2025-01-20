package ru.otus.hw8.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @PostMapping("/create")
    public String createDelivery() {
        // Logic to create a delivery
        System.out.println("Delivery created");
        return "Delivery Created";
    }

    @PostMapping("/cancel")
    public String cancelDelivery() {
        // Logic to cancel a delivery
        System.out.println("Delivery canceled");
        return "Delivery Canceled";
    }
}