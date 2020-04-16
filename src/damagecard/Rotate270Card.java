package damagecard;

import card.base.CmdCard;
import card.base.OnGoing;
import logic.CardSprite;
import logic.Direction;
import token.Mech;

public class Rotate270Card extends CmdCard implements OnGoing {
	public Rotate270Card(Mech programmedMech) {
		this.setSpriteValue(CardSprite.TURN_LEFT);
		this.setProgrammedMech(programmedMech);
		this.setCardType("Damage");
	}

	@Override
	public void execute(int tier) {
		switch (this.getProgrammedMech().getDirection()) {
		case UP:
			this.getProgrammedMech().setDirection(Direction.LEFT);
			break;
		case DOWN:
			this.getProgrammedMech().setDirection(Direction.RIGHT);
			break;
		case LEFT:
			this.getProgrammedMech().setDirection(Direction.DOWN);
			break;
		case RIGHT:
			this.getProgrammedMech().setDirection(Direction.UP);
			break;
		default:
			break;

		}

	}

	@Override
	public void setSpriteValue(int spriteValue) {
		this.spriteValue = spriteValue;
	}

}
