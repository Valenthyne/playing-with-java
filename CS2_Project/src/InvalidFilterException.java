@SuppressWarnings("serial")

// Minor custom exception while will handle most of the filter exception issues (for design purposes only)

public class InvalidFilterException extends Exception {

	InvalidFilterException(String error) {
		super(error);
	}
	
}
