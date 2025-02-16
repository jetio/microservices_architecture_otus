package ru.otus.project.trade.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Value("${mq.buy.queue}")
    private String buyQueue;

    @Value("${mq.sell.queue}")
    private String sellQueue;

    @Value("${mq.security.queue}")
    private String securityQueue;

    @Value("${mq.user.queue}")
    private String userQueue;

    @Bean
    public Queue buyQueue() {
        return new Queue(buyQueue, true); // true means durable queue
    }

    @Bean
    public Queue sellQueue() {
        return new Queue(sellQueue, true); // true means durable queue
    }

    @Bean
    public Queue securityQueue() {
        return new Queue(securityQueue, true); // true means durable queue
    }

    @Bean
    public Queue userQueue() {
        return new Queue(userQueue, true); // true means durable queue
    }
}
