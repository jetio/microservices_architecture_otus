package ru.otus.project.trade.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ru.otus.project.trade.domain.Deal;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradeService {

    private final List<Deal> deals = new ArrayList<>();

    public List<Deal> getDeals() {
        return deals;
    }
}