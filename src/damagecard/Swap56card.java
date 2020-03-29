package damagecard;

import card.base.CmdCard;
import card.base.Instant;
import logic.CardSprite;
import logic.CmdBox;
import token.Mech;

public class Swap56card extends CmdCard implements Instant {
	public Swap56card(Mech programmedMech) {
		// TODO Auto-generated constructor stub
		this.setProgrammedMech(programmedMech);
		this.setSpriteValue(CardSprite.DAMAGE_SWAP56);
	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		CmdBox temp = this.getProgrammedMech().getCmdBoard().getCmdBox(5);
		this.getProgrammedMech().getCmdBoard().setCmdBox(5, this.getProgrammedMech().getCmdBoard().getCmdBox(6));
		this.getProgrammedMech().getCmdBoard().setCmdBox(6, temp);
	}

	@Override
	public void setSpriteValue(int spriteValue) {
		// TODO Auto-generated method stub
		this.spriteValue = spriteValue;
	}

}
