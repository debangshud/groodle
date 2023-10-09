package org.groodle.boot.service.customer.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Slf4j
@Component
public class CustomerDeletionEventConverter implements MessageConverter {

    ObjectMapper mapper;

    public CustomerDeletionEventConverter() {
        mapper = new ObjectMapper();
    }

    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        CustomerDeletionEvent event = (CustomerDeletionEvent) o;
        String payload = null;
        try {
            payload = mapper.writeValueAsString(event);
            log.info("outbound json='{}'", payload);
        } catch (JsonProcessingException e) {
            log.error("error converting form event", e);
        }
        TextMessage message = session.createTextMessage();
        message.setText(payload);

        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        TextMessage textMessage = (TextMessage) message;
        String payload = textMessage.getText();
        log.info("inbound json='{}'", payload);

        CustomerDeletionEvent event = null;
        try {
            event = mapper.readValue(payload, CustomerDeletionEvent.class);
        } catch (Exception e) {
            log.error("error converting to event", e);
        }

        return event;
    }
}
