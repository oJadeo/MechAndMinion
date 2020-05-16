package damagecard;

import java.util.ArrayList;

import card.base.CmdCard;
import card.base.Instant;
import logic.CardSprite;
import token.Mech;

public class Swap34card extends CmdCard implements Instant {
	public Swap34card(Mech programmedMech) {
		this.setProgrammedMech(programmedMech);
		this.setSpriteValue(CardSprite.DAMAGE_SWAP34);
	}

	@Override
	public void trigger() {
		ArrayList<CmdCard> temp = this.getProgrammedMech().getCmdBoard().getCmdBox(2).getCmdCardList();
		this.getProgrammedMech().getCmdBoard().getCmdBox(2)
				.setCmdCardList(this.getProgrammedMech().getCmdBoard().getCmdBox(3).getCmdCardList());
		this.getProgrammedMech().getCmdBoard().getCmdBox(3).setCmdCardList(temp);
	}

}
