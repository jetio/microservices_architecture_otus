package ru.otus.project.trade.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
        this.tradingGlassMap = new ConcurrentHashMap<>();
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

    public List<Deal> makeTrade(){
        List<Order> sellOrderList = getSellOrderList();
        List<Order> buyOrderList = getBuyOrderList();
        Set<Order> matchedOrderSet = new HashSet<>();
        List<Deal> dealList = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        for (Order sellOrder : sellOrderList){
            for (Order buyOrder : buyOrderList){
                if (sellOrder.equals(buyOrder)){
                    matchedOrderSet.add(sellOrder);
                    System.out.println("Processing deal");
                    Deal deal = new Deal();
                    deal.setSellerCode(sellOrder.getTraderCode());
                    deal.setBuyerCode(buyOrder.getTraderCode());
                    deal.setAmount(sellOrder.getAmount());
                    deal.setTicker(sellOrder.getTicker());
                    deal.setPrice(sellOrder.getPrice());
                    LocalDateTime now = LocalDateTime.now();
                    deal.setDateTime(dtf.format(now));
                    dealList.add(deal);
                    break;
                }
            }
        }
        sellOrderList.removeAll(matchedOrderSet);
        buyOrderList.removeAll(matchedOrderSet);
        return dealList;
    }
}