package ru.demo.marketplaceservice.exception;

/**
 * @author Anvar Khasanov
 * student of ITIS KFU
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }
}
