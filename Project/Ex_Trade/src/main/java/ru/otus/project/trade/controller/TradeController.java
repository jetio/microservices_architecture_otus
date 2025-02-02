package ru.otus.project.trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.trade.domain.Deal;
import ru.otus.project.trade.domain.TradingGlass;
import ru.otus.project.trade.service.TradeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @Autowired
    private TradingGlass tradingGlass;

    @GetMapping("/deals")
    public List<Deal> getDeals() {
        return tradeService.getDeals();
    }

    @GetMapping("/trading")
    public ResponseEntity<String> getTradingGlass() {
        System.out.println(tradingGlass);
        return ResponseEntity.ok(tradingGlass.toString());
    }

    @PostMapping("trading/start")
    public void startTrade(){
        // Start job that matches orders
    }

    @PostMapping("trading/finish")
    public void finishTrade(){
        // Stop job that matches orders
    }

}