package exception;

public class SelectEmptyCardException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8572105105979992769L;
	public String message;
	public SelectEmptyCardException(String message) {
		this.message = message;
	}
}
