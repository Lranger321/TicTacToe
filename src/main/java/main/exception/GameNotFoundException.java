package main.exception;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(String message) {
        super(message);
    }

    public GameNotFoundException(Long id) {
        super(String.format("Game with id [%d] not found", id));
    }
}
