package cmdcard;

import card.base.*;
import java.util.ArrayList;
import logic.*;
import tile.*;
import token.*;

public class YellowRotateCard extends CmdCard implements Rotate, Attack, OnGoing {

	public YellowRotateCard() {
		this.spriteValue = CardSprite.YELLOW_ROTATE_CARD_1;
		this.setCardType("Yellow");
	}

	@Override
	public ArrayList<Object> attack(int tier) {
		ArrayList<Object> resultList = new ArrayList<>();
		for (Tile tile : GameController.getBoard().getDiagonalTile(this.getProgrammedMech().getSelfTile(), tier,
				Direction.ALL)) {
			if (tile.getToken() instanceof Token && !tile.getToken().equals(this.getProgrammedMech())) {
				resultList.add((Object) tile.getToken());
			}
		}
		return resultList;
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

	@Override
	public void setSpriteValue(int tier) {
		switch (tier) {
		case 1:
			this.spriteValue = CardSprite.YELLOW_ROTATE_CARD_1;
			break;
		case 2:
			this.spriteValue = CardSprite.YELLOW_ROTATE_CARD_2;
			break;
		case 3:
			this.spriteValue = CardSprite.YELLOW_ROTATE_CARD_3;
			break;
		}

	}

}
