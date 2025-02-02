package ru.otus.project.trade.adapter;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.project.trade.domain.Order;
import ru.otus.project.trade.domain.TradingGlass;

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
                order.setTraderCode(orderFields[3]);
            } catch (IndexOutOfBoundsException e){
                /* NOP */
            }
            return order;
        }
    }

    //@RabbitListener(queues = "/queue/trade/inbound/order/buy")
    @RabbitListener(queues = "${mq.buy.queue}")
    public void handleOrderToBuy(String orderRecord) {
        System.out.println(orderRecord);
        tradingGlass.appendBuyOrder(parseOrder(orderRecord));
    }

    //@RabbitListener(queues = "/queue/trade/inbound/order/sell")
    @RabbitListener(queues = "${mq.sell.queue}")
    public void handleOrderToSell(String orderRecord) {
        System.out.println(orderRecord);
        tradingGlass.appendSellOrder(parseOrder(orderRecord));
    }

    @RabbitListener(queues = "/queue/trade/inbound/security")
    public void handleSecurityCreationEvent(String securityCode) {
        throw new RuntimeException("Not supported yet");
    }

    @RabbitListener(queues = "/queue/trade/inbound/user")
    public void handleSecurityRequest(String userTradingId) {
        throw new RuntimeException("Not supported yet");
    }

    /*
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
