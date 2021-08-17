package ru.demo.marketplaceservice.exception;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
public class OrderCreationException extends RuntimeException {

    public OrderCreationException() {
        super();
    }

    public OrderCreationException(String message) {
        super(message);
    }
}
