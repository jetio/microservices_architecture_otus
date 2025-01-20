package ru.otus.hw8.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

@RestController
@RequestMapping("/item")
public class WarehouseController {

    @Value("${isActive}")
    private Boolean warehouseServiceIsAvailable;

    @PostMapping("/book")
    public String bookItem() {
        // Logic to book an item
        if (warehouseServiceIsAvailable){
            System.out.println("Item booked");
            return "Item booked";
        } else {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Warehouse service is unavailable");
        }
    }

    @PostMapping("/release")
    public String releaseItem() {
        // Logic to release an item
        System.out.println("Item released");
        return "Item released";
    }
}