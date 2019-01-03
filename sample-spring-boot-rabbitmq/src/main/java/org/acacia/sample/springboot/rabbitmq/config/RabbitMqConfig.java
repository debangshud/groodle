package org.dasgupta.sample.springboot.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${airbnb.exchange}")
    private String airbnbExchange;

    @Value("${airbnb.queue}")
    private String airbnbQueue;

    @Value("${airbnb.routingkey}")
    private String airbnbRoutingKey;

    @Value("${booking.exchange}")
    private String bookingExchange;

    @Value("${booking.queue}")
    private String bookingQueue;

    @Value("${booking.routingkey}")
    private String bookingRoutingKey;

    @Bean
    public Queue airbnbQueue() {
        return new Queue(airbnbQueue);
    }

    @Bean
    public Queue bookingQueue() {
        return new Queue(bookingQueue);
    }

    @Bean
    public DirectExchange airbnbExchange() {
        return new DirectExchange(airbnbExchange);
    }

    @Bean
    public TopicExchange bookingExchange() {
        return new TopicExchange(bookingExchange);
    }

    @Bean
    public Binding airbnbExchangeQueueBinding() {
        return BindingBuilder.bind(airbnbQueue()).to(airbnbExchange()).with(airbnbRoutingKey);
    }

    @Bean
    public Binding bookingExchangeQueueBinding() {
        return BindingBuilder.bind(bookingQueue()).to(bookingExchange()).with(bookingRoutingKey);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
