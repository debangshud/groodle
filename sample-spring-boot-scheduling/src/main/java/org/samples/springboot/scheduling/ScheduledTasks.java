package com.samples.springboot.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${sample.rabbitmq.exchange.direct}")
    private String directExchange;

    @Value("${sample.rabbitmq.exchange.direct.routingkey}")
    private String directExchangeRoutingKey;

    @Value("${sample.rabbitmq.exchange.topic}")
    private String topicExchange;

    @Value("${sample.rabbitmq.exchange.topic.routingkey}")
    private String topicExchangeRoutingKey;

    private int i = 0;
    private int j = 0;

    @Scheduled(fixedRate = 5000)
    public void publishToDirectExchange() {
        logger.info("triggered: exchange={}, routingKey={}", directExchange, directExchangeRoutingKey);
        rabbitTemplate.convertAndSend(directExchange, directExchangeRoutingKey, "Hello Direct Exchange" + (++i));
        logger.info("Message sent to direct exchange");
    }

    @Scheduled(fixedRate = 1000)
    public void publishToTopicExchange() {
        logger.info("triggered:");
        rabbitTemplate.convertAndSend(topicExchange, topicExchangeRoutingKey, "Hello Topic Exchange" + (++j));
        logger.info("Message sent to topic exchange");

    }
}
