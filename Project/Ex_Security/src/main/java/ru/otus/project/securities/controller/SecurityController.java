package ru.otus.project.securities.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.securities.domain.Security;
import ru.otus.project.securities.service.SecurityService;

import java.util.List;
import java.util.StringJoiner;

@RestController
@RequestMapping("/api/v1/securities")
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public Security createSecurity(@RequestBody Security security) {
        Security savedSecurity = securityService.save(security);
        StringJoiner stringJoiner = new StringJoiner("|");
        stringJoiner.add(savedSecurity.getId().toString());
        stringJoiner.add(savedSecurity.getTicker());
        rabbitTemplate.convertAndSend("/queue/trade/inbound/security", stringJoiner.toString());
        return savedSecurity;
    }

    @GetMapping
    public List<Security> getSecurities(){
        return securityService.getSecurities();
    }
}

