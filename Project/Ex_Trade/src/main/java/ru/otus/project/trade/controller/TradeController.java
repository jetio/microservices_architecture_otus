package ru.otus.project.trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.trade.domain.Deal;
import ru.otus.project.trade.service.TradeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/deals")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @GetMapping
    public List<Deal> getDeals() {
        return tradeService.getAllDeals();
    }

    public void startTrade(){

    }

    public void finishTrade(){

    }

}