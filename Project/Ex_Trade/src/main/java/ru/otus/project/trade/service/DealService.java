package ru.otus.project.trade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.project.trade.domain.Deal;
import ru.otus.project.trade.repository.DealRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DealService {

    private final DealRepository dealRepository;

    public DealService(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    public List<Deal> getDeals() {
        return dealRepository.findAll();
    }

    public void putDeals(List<Deal> deals) {
        dealRepository.saveAll(deals);
    }
}
