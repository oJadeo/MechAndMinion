package cmdcard;

import java.util.ArrayList;
import card.base.*;
import logic.*;
import token.*;

public class GreenMoveCard extends CmdCard implements Move ,OnGoing{

	public GreenMoveCard() {
		this.spriteValue = CardSprite.GREEN_MOVE_CARD_1;
	}

	@Override
	public ArrayList<Object> move(int tier) {
		Mech mech = this.getProgrammedMech();
		Direction dir = mech.getDirection();
		ArrayList<Object> resultList = new ArrayList<Object>();
		switch (dir) {
		case UP:
			resultList.add((Object)Direction.LEFT);
			resultList.add((Object)Direction.UP);
			resultList.add((Object)Direction.RIGHT);
			break;
		case DOWN:
			resultList.add((Object)Direction.LEFT);
			resultList.add((Object)Direction.DOWN);
			resultList.add((Object)Direction.RIGHT);
			break;
		case RIGHT:
			resultList.add((Object)Direction.UP);
			resultList.add((Object)Direction.RIGHT);
			resultList.add((Object)Direction.DOWN);
			break;
		case LEFT:
			resultList.add((Object)Direction.DOWN);
			resultList.add((Object)Direction.LEFT);
			resultList.add((Object)Direction.UP);
			break;
		default:
			break;
		}
		return resultList;
	}

	@Override
	public void execute(int tier) {
		GameController.setSelectable(move(tier));
		GameController.setSelectTimes(tier);
	}

	public void setSpriteValue(int tier) {
		switch (tier) {
		case 1:
			this.spriteValue = CardSprite.GREEN_MOVE_CARD_1;
			break;
		case 2:
			this.spriteValue = CardSprite.GREEN_MOVE_CARD_2;
			break;
		case 3:
			this.spriteValue = CardSprite.GREEN_MOVE_CARD_3;
			break;

		}
	}

}
