package damagecard;

import card.base.*;
import logic.*;
import token.*;

public class Reversecard extends CmdCard implements Instant{
	public Reversecard(Mech programmedMech) {
		// TODO Auto-generated constructor stub
		this.setProgrammedMech(programmedMech);
		this.setSpriteValue(CardSprite.DAMAGE_REVERSE);
	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		CmdBox temp = this.getProgrammedMech().getCmdBoard().getCmdBox(0);
		this.getProgrammedMech().getCmdBoard().setCmdBox(0,this.getProgrammedMech().getCmdBoard().getCmdBox(5));
		this.getProgrammedMech().getCmdBoard().setCmdBox(5, temp);
		
		temp = this.getProgrammedMech().getCmdBoard().getCmdBox(1);
		this.getProgrammedMech().getCmdBoard().setCmdBox(1,this.getProgrammedMech().getCmdBoard().getCmdBox(4));
		this.getProgrammedMech().getCmdBoard().setCmdBox(4, temp);
		
		temp = this.getProgrammedMech().getCmdBoard().getCmdBox(2);
		this.getProgrammedMech().getCmdBoard().setCmdBox(2,this.getProgrammedMech().getCmdBoard().getCmdBox(3));
		this.getProgrammedMech().getCmdBoard().setCmdBox(3, temp);
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
