package damagecard;

import card.base.CmdCard;
import card.base.Instant;
import logic.CardSprite;
import logic.CmdBox;
import token.Mech;

public class Swap12card extends CmdCard implements Instant{
	public Swap12card(Mech programmedMech) {
		// TODO Auto-generated constructor stub
		this.setProgrammedMech(programmedMech);
		this.setSpriteValue(CardSprite.DAMAGE_SWAP12);
	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		CmdBox temp = this.getProgrammedMech().getCmdBoard().getCmdBox(0);
		this.getProgrammedMech().getCmdBoard().setCmdBox(0,this.getProgrammedMech().getCmdBoard().getCmdBox(1));
		this.getProgrammedMech().getCmdBoard().setCmdBox(1, temp);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSpriteValue(int spriteValue) {
		// TODO Auto-generated method stub
		this.spriteValue = spriteValue;
	}
	

}
