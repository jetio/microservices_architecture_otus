package ru.otus.project.trade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import ru.otus.project.trade.domain.Deal;
import ru.otus.project.trade.domain.TradingGlass;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import java.util.List;

@Service
public class TradeService {

    @Autowired
    private DealService dealService;

    @Autowired
    private TradingGlass tradingGlass;

    private final ThreadPoolTaskScheduler taskScheduler;
    private ScheduledFuture<?> scheduledFuture;

    public TradeService() {
        this.taskScheduler = new ThreadPoolTaskScheduler();
        this.taskScheduler.initialize();
    }

    public void startTradeSession() {
        if (scheduledFuture == null || scheduledFuture.isCancelled()) {
            Runnable task = () -> {
                    System.out.println("Executing trade session...");
                    List<Deal> deals = tradingGlass.makeTrade();
                    dealService.putDeals(deals);
            };

            PeriodicTrigger trigger = new PeriodicTrigger(5, TimeUnit.SECONDS);
            scheduledFuture = taskScheduler.schedule(task, trigger);
        }
    }

    public void finishTradeSession() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

}