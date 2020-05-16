package damagecard;

import java.util.ArrayList;

import card.base.CmdCard;
import card.base.OnGoing;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import token.Mech;

public class RightMoveCard extends CmdCard implements OnGoing {
	Direction dir;

	public RightMoveCard(Mech programmedMech) {
		dir = Direction.RIGHT;
		this.setSpriteValue(CardSprite.RIGHT_MOVE);
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

}
