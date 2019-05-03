package org.dasgupta.sample.springboot.rabbitmq.listener;

public class BookingInventoryUpdateMessage {

    private String message;

    public BookingInventoryUpdateMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BookingInventoryUpdateMessage{" +
                "message='" + message + '\'' +
                '}';
    }
}
