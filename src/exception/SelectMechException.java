package exception;

public class SelectMechException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7610147905427145411L;
	public String message;
	public SelectMechException(String message) {
		this.message = message;
	}
}
