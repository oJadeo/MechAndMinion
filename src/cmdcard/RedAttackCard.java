package cmdcard;

import card.base.CmdCard;
import logic.CardSprite;
import card.base.Attack;

public class RedAttackCard extends CmdCard implements Attack {
	private int spriteValue ;
	public RedAttackCard() {
		this.spriteValue = CardSprite.RED_ATTACK_CARD_1;
	}
	@Override
	public void attack() {
		
	}

	@Override
	public void execute() {

	}
	

}
