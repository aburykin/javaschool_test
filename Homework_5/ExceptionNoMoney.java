package Homework_5;

/**
 * Created by ABurykin on 10.08.2016.
 */
public class ExceptionNoMoney  extends Exception{
    public ExceptionNoMoney(String message) {
        super(message);
    }

    public ExceptionNoMoney(String message, Throwable cause) {
        super(message, cause);
    }
}
