package damagecard;

import card.base.CmdCard;
import card.base.Instant;
import logic.CardSprite;
import logic.CmdBox;
import token.Mech;

public class Swap34card extends CmdCard implements Instant {
	public Swap34card(Mech programmedMech) {
		this.setProgrammedMech(programmedMech);
		this.setSpriteValue(CardSprite.DAMAGE_SWAP34);
	}

	@Override
	public void trigger() {
		CmdBox temp = this.getProgrammedMech().getCmdBoard().getCmdBox(2);
		this.getProgrammedMech().getCmdBoard().setCmdBox(2, this.getProgrammedMech().getCmdBoard().getCmdBox(2));
		this.getProgrammedMech().getCmdBoard().setCmdBox(3, temp);
	}

	@Override
	public void setSpriteValue(int spriteValue) {
		this.spriteValue = spriteValue;

	}

}
