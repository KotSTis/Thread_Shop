package ourExceptions;

public class InvalidItemException extends Exception {
	
	public InvalidItemException(String error) {
		super(error);
	}
}