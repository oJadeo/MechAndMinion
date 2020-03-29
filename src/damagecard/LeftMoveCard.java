package damagecard;

import card.base.CmdCard;
import card.base.OnGoing;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import token.Mech;

public class LeftMoveCard extends CmdCard implements OnGoing {
	Direction dir;

	public LeftMoveCard(Mech programmedMech) {
		dir = Direction.LEFT;
		this.setSpriteValue(CardSprite.LEFT_MOVE);
		this.setProgrammedMech(programmedMech);
	}

	@Override
	public void execute(int tier) {
		// TODO Auto-generated method stub
		GameController.move(this.getProgrammedMech(), dir);
	}

	@Override
	public void setSpriteValue(int spriteValue) {
		// TODO Auto-generated method stub
		this.spriteValue = spriteValue;
	}
}
