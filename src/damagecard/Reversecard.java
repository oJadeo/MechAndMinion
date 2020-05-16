package damagecard;

import java.util.ArrayList;

import card.base.*;
import logic.*;
import token.*;

public class Reversecard extends CmdCard implements Instant {
	public Reversecard(Mech programmedMech) {
		this.setProgrammedMech(programmedMech);
		this.setSpriteValue(CardSprite.DAMAGE_REVERSE);
	}

	@Override
	public void trigger() {
		//TODO FIx
		ArrayList<CmdCard> temp = this.getProgrammedMech().getCmdBoard().getCmdBox(0).getCmdCardList();
		this.getProgrammedMech().getCmdBoard().getCmdBox(0)
				.setCmdCardList(this.getProgrammedMech().getCmdBoard().getCmdBox(5).getCmdCardList());
		this.getProgrammedMech().getCmdBoard().getCmdBox(5).setCmdCardList(temp);
		
		temp = this.getProgrammedMech().getCmdBoard().getCmdBox(1).getCmdCardList();
		this.getProgrammedMech().getCmdBoard().getCmdBox(1)
				.setCmdCardList(this.getProgrammedMech().getCmdBoard().getCmdBox(4).getCmdCardList());
		this.getProgrammedMech().getCmdBoard().getCmdBox(4).setCmdCardList(temp);
		
		temp = this.getProgrammedMech().getCmdBoard().getCmdBox(2).getCmdCardList();
		this.getProgrammedMech().getCmdBoard().getCmdBox(2)
				.setCmdCardList(this.getProgrammedMech().getCmdBoard().getCmdBox(3).getCmdCardList());
		this.getProgrammedMech().getCmdBoard().getCmdBox(3).setCmdCardList(temp);
	}

}
