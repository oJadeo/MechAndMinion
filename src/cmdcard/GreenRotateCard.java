package cmdcard;

import java.util.ArrayList;

import card.base.CmdCard;
import card.base.Rotate;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import token.Mech;

public class GreenRotateCard extends CmdCard implements Rotate {
	private int spriteValue;
	public GreenRotateCard() {
		this.spriteValue = CardSprite.GREEN_ROTATE_CARD_1;
	}
	
	public ArrayList<Direction> spin(int tier) {
		int x = this.getProgrammedMech().getSelfTile().getLocationX;
		int y = this.getProgrammedMech().getSelfTile().getLocationY;
		Mech mech = (Mech) GameController.getBoard().getTile(x, y).getToken();
		Direction dir = mech.getDirection();
		ArrayList<Direction> dirList =new ArrayList<>();
		switch(tier) {
		case 1:
			switch(dir) {
			case UP:
				dirList.add(Direction.RIGHT);
				break;
			case RIGHT:
				dirList.add(Direction.DOWN);
				break;
			case DOWN:
				dirList.add(Direction.LEFT);
				break;
			case LEFT:
				dirList.add(Direction.UP);
				break;
			default:
				break;
			}
		case 2:
			switch(dir) {
			case UP:
				dirList.add(Direction.RIGHT);
				dirList.add(Direction.LEFT);
				dirList.add(Direction.DOWN);
				break;
			case RIGHT:
				dirList.add(Direction.DOWN);
				dirList.add(Direction.LEFT);
				dirList.add(Direction.UP);
				break;
			case LEFT:
				dirList.add(Direction.DOWN);
				dirList.add(Direction.RIGHT);
				dirList.add(Direction.UP);
				break;
			case DOWN:
				dirList.add(Direction.RIGHT);
				dirList.add(Direction.LEFT);
				dirList.add(Direction.UP);
				break;
			default:
				break;
			}
		case 3:
			dirList.add(Direction.ALL);
			break;
		default:
			break;
		}
		return dirList;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	public void setSpriteValue(int tier) {
		switch(tier) {
		case 1:
			this.spriteValue = CardSprite.GREEN_ROTATE_CARD_1;
			break;
		case 2:
			this.spriteValue = CardSprite.GREEN_ROTATE_CARD_2;
			break;
		case 3:
			this.spriteValue = CardSprite.GREEN_ROTATE_CARD_3;
			break;
		
		}
	}

}