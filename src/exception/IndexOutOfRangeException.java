package exception;

public class IndexOutOfRangeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3885580699579850503L;
	public String message;

	public IndexOutOfRangeException(String message) {
		this.message = message;
	}
}
