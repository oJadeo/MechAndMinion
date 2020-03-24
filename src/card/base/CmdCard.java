package card.base;

import logic.CardType;
import token.Mech;

public abstract class CmdCard {
	private CardType cardType;
	private Mech programmedMech;
	private int spriteValue;
	public CmdCard() {
	}
	public abstract boolean execute();
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	public abstract void setSpriteValue(int spriteValue);
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
	
	
}
