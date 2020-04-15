package damagecard;

import card.base.CmdCard;
import card.base.Instant;
import logic.CardSprite;
import logic.CmdBox;
import token.Mech;

public class Swap56card extends CmdCard implements Instant {
	public Swap56card(Mech programmedMech) {
		this.setProgrammedMech(programmedMech);
		this.setSpriteValue(CardSprite.DAMAGE_SWAP56);
	}

	@Override
	public void trigger() {
		CmdBox temp = this.getProgrammedMech().getCmdBoard().getCmdBox(4);
		this.getProgrammedMech().getCmdBoard().setCmdBox(5, this.getProgrammedMech().getCmdBoard().getCmdBox(4));
		this.getProgrammedMech().getCmdBoard().setCmdBox(5, temp);
	}

	@Override
	public void setSpriteValue(int spriteValue) {
		this.spriteValue = spriteValue;
	}

}
