package ourExceptions;


public class InvalidItemIDLengthException extends Exception {
	
	public InvalidItemIDLengthException() {
		System.err.print("Item ID'S length is invalid!\n");
	}
}