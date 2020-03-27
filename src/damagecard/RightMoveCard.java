package damagecard;

import card.base.CmdCard;
import card.base.OnGoing;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import token.Mech;

public class RightMoveCard extends CmdCard implements OnGoing{
	Direction dir;
	public RightMoveCard(Mech programmedMech) {
		dir = Direction.RIGHT;
		this.setSpriteValue(CardSprite.RIGHT_MOVE);
		this.setProgrammedMech(programmedMech);
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		GameController.move(this.getProgrammedMech(), dir);
	}
	@Override
	public void setSpriteValue(int spriteValue) {
		// TODO Auto-generated method stub
		this.spriteValue = spriteValue;
	}
}
