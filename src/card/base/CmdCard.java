package card.base;

import logic.CardType;
import token.Mech;

public abstract class CmdCard {
	private CardType cardType;
	private Mech programmedMech;
	private int spriteValue;
	public CmdCard() {
	}
	public abstract void execute();
	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}
	public void setSpriteValue(int spriteValue) {
		this.spriteValue = spriteValue;
	}
	public CardType getCardType() {
		return this.cardType;
	}
	public int getSpriteValue() {
		return this.spriteValue;
	}
	
	
	
}
