package ru.otus.project.orders.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.project.orders.domain.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.StringJoiner;

@Component
public class MqAdapter {

    @Value("mq.buy.queue")
    private String buyQueueName;

    @Value("mq.sell.queue")
    private String sellQueueName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static String createOrderString(Order order) {
        StringJoiner stringJoiner = new StringJoiner("|");
        stringJoiner.add(order.getId().toString());
        stringJoiner.add(order.getTicker());
        stringJoiner.add(String.valueOf(order.getAmount()));
        stringJoiner.add(order.getPrice().toString());
        return stringJoiner.toString();
    }

    public void sendOrderToBuyIntoTrade(Order order) {
        rabbitTemplate.convertAndSend(buyQueueName, createOrderString(order));
    }

    public void sendOrderToSellIntoTrade(Order order) {
        rabbitTemplate.convertAndSend(sellQueueName, createOrderString(order));
    }
}
