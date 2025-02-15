package ru.otus.project.trade.adapter;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.project.trade.domain.Order;
import ru.otus.project.trade.domain.TradingGlass;

import java.math.BigDecimal;

@Component
public class MqAdapter {

    @Autowired
    private TradingGlass tradingGlass;

    private static Order parseOrder(String orderRecord) {
        String[] orderFields = orderRecord.split("\\|");
        if (orderFields.length == 0){
            throw new RuntimeException("Unknown message format");
        } else {
            Order order = new Order();
            order.setId(Long.valueOf(orderFields[0]));
            try {
                order.setTicker(orderFields[1]);
                order.setAmount(Integer.parseInt(orderFields[2]));
                order.setPrice(new BigDecimal(orderFields[3]));
                order.setTraderCode(orderFields[4]);
            } catch (IndexOutOfBoundsException e){
                /* NOP */
            }
            return order;
        }
    }

    @RabbitListener(queues = "${mq.buy.queue}")
    public void handleOrderToBuy(String orderRecord) {
        tradingGlass.appendBuyOrder(parseOrder(orderRecord));
    }

    @RabbitListener(queues = "${mq.sell.queue}")
    public void handleOrderToSell(String orderRecord) {
        tradingGlass.appendSellOrder(parseOrder(orderRecord));
    }

    /*
    @RabbitListener(queues = "${mq.security.queue}")
    public void handleSecurityCreationEvent(String securityCode) {
        throw new RuntimeException("Not supported yet");
    }

    @RabbitListener(queues = "${mq.user.queue}")
    public void handleSecurityRequest(String userTradingId) {
        throw new RuntimeException("Not supported yet");
    }

    @RabbitListener(queues = "/queue/trade/inbound/request")
    public void receiveRequest(String message) {
        deals.add("Request: " + message);
    }

    @RabbitListener(queues = "/queue/trade/inbound/security")
    public void receiveSecurity(String message) {
        deals.add("Security: " + message);
    }
    */
}
