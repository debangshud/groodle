package org.dasgupta.sample.springboot.rabbitmq.listener;

public class AirbnbInventoryUpdateMessage {

    private String message;

    public AirbnbInventoryUpdateMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AirbnbInventoryUpdateMessage{" +
                "message='" + message + '\'' +
                '}';
    }
}
