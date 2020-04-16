package damagecard;

import java.util.ArrayList;

import card.base.*;
import logic.*;
import token.Mech;

public class BackMoveCard extends CmdCard implements OnGoing {
	
	Direction dir;
	
	public BackMoveCard(Mech programmedMech) {
		dir = Direction.DOWN;
		this.setSpriteValue(CardSprite.BACK_MOVE);
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
