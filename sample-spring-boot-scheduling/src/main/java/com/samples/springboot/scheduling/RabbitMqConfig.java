package com.samples.springboot.scheduling;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${sample.rabbitmq.exchange.direct}")
    private String directExchange;

    @Value("${sample.rabbitmq.exchange.direct.queue}")
    private String directExchangeQueue;

    @Value("${sample.rabbitmq.exchange.direct.routingkey}")
    private String directExchangeRoutingKey;

    @Bean
    public Queue queue() {
        return new Queue(directExchangeQueue);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(directExchange);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(directExchange()).with(directExchangeRoutingKey);
    }
}
