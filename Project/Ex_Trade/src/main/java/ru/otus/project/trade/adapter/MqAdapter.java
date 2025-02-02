package ru.otus.project.trade.adapter;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class MqAdapter {

    @RabbitListener(queues = "/queue/trade/inbound/order/buy")
    public void handleOrderToBuy(String orderRecord) {
        // Process order request
    }

    @RabbitListener(queues = "/queue/trade/inbound/order/sell")
    public void handleOrderToSell(String orderRecord) {
        // Process order request
    }

    @RabbitListener(queues = "/queue/trade/inbound/security")
    public void handleSecurityCreationEvent(String securityCode) {
        // Process security request
    }

    @RabbitListener(queues = "/queue/trade/inbound/user")
    public void handleSecurityRequest(String userTradingId) {
        // Process security request
    }
}
