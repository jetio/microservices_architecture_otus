package ru.otus.project.securities.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.securities.domain.Security;
import ru.otus.project.securities.service.SecurityService;

import java.util.List;

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
        rabbitTemplate.convertAndSend("/queue/trade/inbound/security", savedSecurity);
        return savedSecurity;
    }

    @GetMapping
    public List<Security> getSecurities(){
        return securityService.getSecurities();
    }
}

