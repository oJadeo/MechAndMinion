package damagecard;

import java.util.ArrayList;

import card.base.CmdCard;
import card.base.OnGoing;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import token.Mech;

public class Rotate180Card extends CmdCard implements OnGoing {
	public Rotate180Card(Mech programmedMech) {
		this.setSpriteValue(CardSprite.TURN_AROUND);
		this.setProgrammedMech(programmedMech);
		this.setCardType("Damage");
	}

	@Override
	public void execute(int tier) {
		ArrayList<Object> result = new ArrayList<Object>();
		// TODO Auto-generated method stub
		switch (this.getProgrammedMech().getDirection()) {
		case UP:
			result.add((Object) Direction.DOWN);
			break;
		case DOWN:
			result.add((Object) Direction.UP);
			break;
		case LEFT:
			result.add((Object) Direction.RIGHT);
			break;
		case RIGHT:
			result.add((Object) Direction.LEFT);
			break;
		default:
			break;

		}
		GameController.setSelectable(result);
		GameController.setSelectTimes(1);

	}

	@Override
	public void setSpriteValue(int spriteValue) {
		// TODO Auto-generated method stub
		this.spriteValue = spriteValue;
	}

}
