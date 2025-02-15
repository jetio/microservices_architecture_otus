package ru.otus.project.trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.trade.domain.Deal;
import ru.otus.project.trade.domain.TradingGlass;
import ru.otus.project.trade.service.DealService;
import ru.otus.project.trade.service.TradeService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @Autowired
    private TradingGlass tradingGlass;

    @Autowired
    private DealService dealService;

    @GetMapping("/deals")
    public List<Deal> getDeals() {
        return dealService.getDeals();
    }

    @GetMapping("/trading")
    public ResponseEntity<String> getTradingGlass() {
        return ResponseEntity.ok(tradingGlass.toString());
    }

    @PostMapping("trading/start")
    public String startTrade(){
        tradeService.startTradeSession();
        return "Trade started!";
    }

    @PostMapping("trading/finish")
    public String finishTrade(){
        tradeService.finishTradeSession();
        return "Trade finished!";
    }
}