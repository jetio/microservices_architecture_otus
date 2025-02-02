package ru.otus.project.securities.service;

import org.springframework.stereotype.Service;
import ru.otus.project.securities.domain.Security;
import ru.otus.project.securities.repository.SecurityRepository;

@Service
public class SecurityService {
    private final SecurityRepository securityRepository;

    public SecurityService(SecurityRepository securityRepository) {
        this.securityRepository = securityRepository;
    }

    public Security save(Security security) {
        return securityRepository.save(security);
    }
}