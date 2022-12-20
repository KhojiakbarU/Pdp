package exceptions;

public class WrongPhoneNumberException  extends RuntimeException{
    public WrongPhoneNumberException() {
        super("wrong phone number entered");
    }

    public WrongPhoneNumberException(String message) {
        super(message);
    }
}
