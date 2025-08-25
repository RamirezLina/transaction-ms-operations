package com.transactionmgmt.operations.integration.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${app.exchange}")
    private String exchangeName;

    @Value("${app.queue}")
    private String queueName;

    @Value("${app.routing}")
    private String routingKey;

    @Bean
    public DirectExchange accountsDirect() {
        return new DirectExchange(exchangeName, true, false);
    }

    @Bean
    public Queue operationsQueue() {
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public Binding bindOps(Queue operationsQueue, DirectExchange accountsDirect) {
        return BindingBuilder.bind(operationsQueue).to(accountsDirect).with(routingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter jacksonConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
