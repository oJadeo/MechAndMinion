package card.base;

import logic.*;
import token.Mech;

public abstract class CmdCard {
	private String cardType = null;
	private Mech programmedMech;
	private int spriteValue;
	private CmdBox cmdBox;

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setSpriteValue(int spriteValue) {
		this.spriteValue = spriteValue;
	}

	public int getSpriteValue() {
		return this.spriteValue;
	}

	public void setProgrammedMech(Mech programmedMech) {
		this.programmedMech = programmedMech;
	}

	public Mech getProgrammedMech() {
		return this.programmedMech;
	}

	public void setCmdBox(CmdBox cmdBox) {
		this.cmdBox = cmdBox;
	}

	public CmdBox getCmdBox() {
		return cmdBox;
	}
}
