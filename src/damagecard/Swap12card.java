package damagecard;

import java.util.ArrayList;

import card.base.CmdCard;
import card.base.Instant;
import logic.CardSprite;
import token.Mech;

public class Swap12card extends CmdCard implements Instant {
	public Swap12card(Mech programmedMech) {
		this.setProgrammedMech(programmedMech);
		this.setSpriteValue(CardSprite.DAMAGE_SWAP12);
	}

	@Override
	public void trigger() {
		ArrayList<CmdCard> temp = this.getProgrammedMech().getCmdBoard().getCmdBox(0).getCmdCardList();
		this.getProgrammedMech().getCmdBoard().getCmdBox(0)
				.setCmdCardList(this.getProgrammedMech().getCmdBoard().getCmdBox(1).getCmdCardList());
		this.getProgrammedMech().getCmdBoard().getCmdBox(1).setCmdCardList(temp);
	}

}
