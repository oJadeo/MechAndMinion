package damagecard;

import card.base.CmdCard;
import card.base.Instant;
import logic.CardSprite;
import logic.CmdBox;
import token.Mech;

public class Swap34card extends CmdCard implements Instant {
	public Swap34card(Mech programmedMech) {
		// TODO Auto-generated constructor stub
		this.setProgrammedMech(programmedMech);
		this.setSpriteValue(CardSprite.DAMAGE_SWAP34);
	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		CmdBox temp = this.getProgrammedMech().getCmdBoard().getCmdBox(3);
		this.getProgrammedMech().getCmdBoard().setCmdBox(3, this.getProgrammedMech().getCmdBoard().getCmdBox(4));
		this.getProgrammedMech().getCmdBoard().setCmdBox(4, temp);
	}

	@Override
	public void setSpriteValue(int spriteValue) {
		// TODO Auto-generated method stub
		this.spriteValue = spriteValue;

	}

}
