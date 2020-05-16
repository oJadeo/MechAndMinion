package cmdcard;

import java.util.ArrayList;
import card.base.*;
import logic.*;
import tile.*;
import token.*;

public class GreenRotateCard extends CmdCard implements Attack, Rotate, OnGoing {

	public GreenRotateCard() {
		super.setSpriteValue(CardSprite.GREEN_ROTATE_CARD_1);
		this.setCardType("Green");
	}

	@Override
	public ArrayList<Object> rotate(int tier) {
		ArrayList<Object> resultList = new ArrayList<Object>();
		switch (tier) {
		case 1:
			switch (this.getProgrammedMech().getDirection()) {
			case UP:
				resultList.add((Object) Direction.RIGHT);
				resultList.add((Object) Direction.LEFT);
				break;
			case RIGHT:
				resultList.add((Object) Direction.DOWN);
				resultList.add((Object) Direction.UP);
				break;
			case DOWN:
				resultList.add((Object) Direction.LEFT);
				resultList.add((Object) Direction.RIGHT);
				break;
			case LEFT:
				resultList.add((Object) Direction.UP);
				resultList.add((Object) Direction.DOWN);
				break;
			default:
				break;
			}
			break;
		case 2:
			switch (this.getProgrammedMech().getDirection()) {
			case UP:
				resultList.add((Object) Direction.RIGHT);
				resultList.add((Object) Direction.LEFT);
				resultList.add((Object) Direction.DOWN);
				break;
			case RIGHT:
				resultList.add((Object) Direction.DOWN);
				resultList.add((Object) Direction.LEFT);
				resultList.add((Object) Direction.UP);
				break;
			case LEFT:
				resultList.add((Object) Direction.DOWN);
				resultList.add((Object) Direction.RIGHT);
				resultList.add((Object) Direction.UP);
				break;
			case DOWN:
				resultList.add((Object) Direction.RIGHT);
				resultList.add((Object) Direction.LEFT);
				resultList.add((Object) Direction.UP);
				break;
			default:
				break;
			}
			break;
		case 3:
			resultList.add((Object) Direction.UP);
			resultList.add((Object) Direction.DOWN);
			resultList.add((Object) Direction.RIGHT);
			resultList.add((Object) Direction.LEFT);
			break;
		default:
			break;
		}
		return resultList;
	}
	
	@Override
	public void execute(int tier) {
		GameController.setSelectable(rotate(tier));
		GameController.setSelectTimes(1);
	}

	public void setSpriteValue(int tier) {
		switch (tier) {
		case 1:
			super.setSpriteValue(CardSprite.GREEN_ROTATE_CARD_1);
			break;
		case 2:
			super.setSpriteValue(CardSprite.GREEN_ROTATE_CARD_2);
			break;
		case 3:
			super.setSpriteValue(CardSprite.GREEN_ROTATE_CARD_3);
			break;
		}
	}

	@Override
	public ArrayList<Object> attack(int tier) {
		ArrayList<Object> resultList = new ArrayList<Object>();
		for(Tile e:GameController.getBoard().getAdjacentTile(this.getProgrammedMech().getSelfTile(), tier, Direction.ALL)) {
			if(e.getToken() instanceof Token) {
				resultList.add(e.getToken());
			}
		}
		return resultList;
	}

}
