package damagecard;

import java.util.ArrayList;

import card.base.CmdCard;
import card.base.OnGoing;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import token.Mech;

public class ForwardMoveCard extends CmdCard implements OnGoing {
	Direction dir;

	public ForwardMoveCard(Mech programmedMech) {
		dir = Direction.UP;
		this.setSpriteValue(CardSprite.FORWARD_MOVE);
		this.setProgrammedMech(programmedMech);
		this.setCardType("Damage");
	}

	@Override
	public void execute(int tier) {
		ArrayList<Object> result = new ArrayList<Object>();
		result.add((Object) dir);
		GameController.setSelectable(result);
		GameController.setSelectTimes(1);
	}

	@Override
	public void setSpriteValue(int spriteValue) {
		this.spriteValue = spriteValue;
	}
}
