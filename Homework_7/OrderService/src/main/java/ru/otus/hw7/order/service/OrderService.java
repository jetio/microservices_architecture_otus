package ru.otus.hw7.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.otus.hw7.order.model.CustomerOrder;
import ru.otus.hw7.order.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Value("${api.billing.url}")
    private String billingUrl;

    @Value("${api.notification.url}")
    private String notificationUrl;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    public CustomerOrder createOrder(String username, BigDecimal amount) {
        // Step 1: Call the billing service to withdraw money
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> billingResponse = restTemplate.postForEntity(billingUrl + "?username=" + username + "&amount=" + amount, request, String.class);

        if (billingResponse.getStatusCode().is2xxSuccessful()) {
            // Step 2: Save the order
            CustomerOrder order = new CustomerOrder();
            order.setUsername(username);
            order.setAmount(amount);
            CustomerOrder savedOrder = orderRepository.save(order);

            // Step 3: Notify the user
            restTemplate.postForEntity(notificationUrl + "?username=" + username + "&message=Order%20created", request, String.class);
            return savedOrder;
        } else {
            throw new RuntimeException("Failed to withdraw money");
        }
    }

    public List<CustomerOrder> getOrdersByUsername(String username) {
        return orderRepository.findByUsername(username);
    }
}