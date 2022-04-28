package dml.domain;

public class InvalidUserException extends Exception{

    public InvalidUserException(String message){
        super(message);
    }

    public InvalidUserException(String message, Throwable innerEx ) {
        super(message, innerEx);
    }
}
