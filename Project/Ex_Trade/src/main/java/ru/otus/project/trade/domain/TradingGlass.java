package ru.otus.project.trade.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public final class TradingGlass {
    private static final String BUY_KEY = "BUY";
    private static final String SELL_KEY = "SELL";

    @Override
    public String toString() {
        return "TradingGlass{" +
                "tradingGlassMap=" + tradingGlassMap +
                '}';
    }

    private Map<String, List<Order>> tradingGlassMap;

    public TradingGlass() {
        this.tradingGlassMap = new HashMap<>();
        this.tradingGlassMap.put(BUY_KEY, new ArrayList<Order>());
        this.tradingGlassMap.put(SELL_KEY, new ArrayList<Order>());
    }

    private List<Order> getBuyOrderList(){
        return this.tradingGlassMap.get(BUY_KEY);
    }

    private List<Order> getSellOrderList(){
        return this.tradingGlassMap.get(SELL_KEY);
    }

    public void appendBuyOrder(Order order){
        getBuyOrderList().add(order);
    }

    public void appendSellOrder(Order order){
        getSellOrderList().add(order);
    }

}
