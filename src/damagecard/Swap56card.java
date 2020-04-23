package damagecard;

import java.util.ArrayList;

import card.base.CmdCard;
import card.base.Instant;
import logic.CardSprite;
import token.Mech;

public class Swap56card extends CmdCard implements Instant {
	public Swap56card(Mech programmedMech) {
		this.setProgrammedMech(programmedMech);
		this.setSpriteValue(CardSprite.DAMAGE_SWAP56);
	}

	@Override
	public void trigger() {
		ArrayList<CmdCard> temp = this.getProgrammedMech().getCmdBoard().getCmdBox(4).getCmdCardList();
		this.getProgrammedMech().getCmdBoard().getCmdBox(4)
				.setCmdCardList(this.getProgrammedMech().getCmdBoard().getCmdBox(5).getCmdCardList());
		this.getProgrammedMech().getCmdBoard().getCmdBox(5).setCmdCardList(temp);
	}

	@Override
	public void setSpriteValue(int spriteValue) {
		this.spriteValue = spriteValue;
	}

}
