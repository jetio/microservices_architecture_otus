package ru.otus.hw8.saga.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SagaTriggerController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @GetMapping("/start")
    public String startSaga(@RequestHeader("orderId") long orderId) {
        try {
            producerTemplate.sendBodyAndHeader("direct:startSaga", "Start Saga", "orderId", orderId);
            //producerTemplate.sendBody("direct:startSaga", "Start Saga");
        } catch(Exception e) {
            return "Saga Failed: " + e.getMessage();
        }
        return "Saga Succeeded";
    }
}