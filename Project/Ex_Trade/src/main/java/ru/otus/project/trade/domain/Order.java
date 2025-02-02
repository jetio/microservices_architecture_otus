package ru.otus.project.trade.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity(name = "ex_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ticker;
    private int amount;
    private String traderCode;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", ticker='" + ticker + '\'' +
                ", amount=" + amount +
                ", traderCode='" + traderCode + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getTraderCode() {
        return traderCode;
    }

    public void setTraderCode(String traderCode) {
        this.traderCode = traderCode;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}