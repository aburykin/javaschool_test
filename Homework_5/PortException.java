package Homework_5;

/**
 * Created by ABurykin on 10.08.2016.
 */
public class PortException extends Exception {
    public PortException(String message) {
        super(message);
    }

    public PortException(String message, Throwable cause) {
        super(message, cause);
    }
}
