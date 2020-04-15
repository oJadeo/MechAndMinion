package exception;

import logic.GameController;
import token.Mech;

public class SelectMechException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7610147905427145411L;
	public String message;

	public SelectMechException(Mech selectedMech) {
		if(selectedMech.equals(GameController.getRedMech())){
			this.message = "Red Mech";
		}else {
			this.message = "Blue Mech";
		}
	}
}
