package be.exception;

public class WrongVersionException extends RuntimeException {

    public WrongVersionException(String message) {
	super(message);
    }
}
