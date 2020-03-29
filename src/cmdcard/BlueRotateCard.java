package cmdcard;

import java.util.ArrayList;
import card.base.*;
import logic.*;
import tile.*;
import token.*;

public class BlueRotateCard extends CmdCard implements Rotate, Attack ,OnGoing {
	
	public BlueRotateCard() {
		this.spriteValue = CardSprite.BLUE_ROTATE_CARD_1;
	}

	@Override
	public ArrayList<Object> attack(int tier) {
		ArrayList<Object> result = new ArrayList<Object>();
		int x = this.getProgrammedMech().getSelfTile().getLocationX();
		int y = this.getProgrammedMech().getSelfTile().getLocationY();
		for (Tile tile : GameController.getBoard().getBorder(GameController.getBoard().getTile(x, y), 1)) {
			if (tile.getToken() instanceof Token) {
				if (!tile.getToken().equals(this.getProgrammedMech())) {
					result.add((Object) tile.getToken());
				}
			}
		}
		return result;
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
		// TODO Auto-generated method stub
		GameController.setSelectable(rotate(tier));
		GameController.setSelectTimes(1);
	}

	@Override
	public void setSpriteValue(int tier) {
		switch (tier) {
		case 1:
			this.spriteValue = CardSprite.BLUE_ROTATE_CARD_1;
			break;
		case 2:
			this.spriteValue = CardSprite.BLUE_ROTATE_CARD_2;
			break;
		case 3:
			this.spriteValue = CardSprite.BLUE_ROTATE_CARD_3;
			break;
		}
	}
}
