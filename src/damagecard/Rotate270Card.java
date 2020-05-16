package damagecard;

import java.util.ArrayList;

import card.base.CmdCard;
import card.base.OnGoing;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import token.Mech;

public class Rotate270Card extends CmdCard implements OnGoing {
	public Rotate270Card(Mech programmedMech) {
		this.setSpriteValue(CardSprite.TURN_LEFT);
		this.setProgrammedMech(programmedMech);
		this.setCardType("Damage");
	}

	@Override
	public void execute(int tier) {
		ArrayList<Object> result = new ArrayList<Object>();
		switch (this.getProgrammedMech().getDirection()) {
		case UP:
			result.add((Object)Direction.LEFT);
			break;
		case DOWN:
			result.add((Object)Direction.RIGHT);
			break;
		case LEFT:
			result.add((Object)Direction.DOWN);
			break;
		case RIGHT:
			result.add((Object)Direction.UP);
			break;
		default:
			break;
		}
		GameController.setSelectable(result);
		GameController.setSelectTimes(1);

	}

}
