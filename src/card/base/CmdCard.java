package card.base;

import logic.*;
import token.Mech;

public abstract class CmdCard {
	private CardType cardType;
	private Mech programmedMech;
	protected int spriteValue;
	private CmdBox cmdBox;
	public CmdCard() {
	}
	public abstract void execute();
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	public abstract void setSpriteValue(int tier);
	public CardType getCardType() {
		return this.cardType;
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
