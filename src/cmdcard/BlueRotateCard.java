package cmdcard;

import card.base.CmdCard;

import java.util.ArrayList;

import card.base.Attack;
import card.base.Rotate;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import tile.Tile;
import token.Mech;
import token.Token;

public class BlueRotateCard extends CmdCard implements Rotate, Attack {
	public BlueRotateCard() {
		this.spriteValue = CardSprite.BLUE_ROTATE_CARD_1;
	}
	@Override
	public ArrayList<Object> attack(int tier) {
		ArrayList<Object> result = new ArrayList<>();
		int x = this.getProgrammedMech().getSelfTile().getLocationX();
		int y = this.getProgrammedMech().getSelfTile().getLocationY();
		ArrayList<Tile> tileList = GameController.getBoard().getBorder(GameController.getBoard().getTile(x,y),1);
		for(Tile tile:tileList) {
			if(tile.getToken() instanceof Token) {
				if(!tile.getToken().equals(this.getProgrammedMech())) {
					result.add((Object) tile.getToken());
				}
			}
		}
		return result;
	}
	@Override
	public ArrayList<Object> rotate(int tier) {
		Mech mech = this.getProgrammedMech();
		Direction dir = mech.getDirection();
		ArrayList<Object> dirList =new ArrayList<Object>();
		switch(tier) {
		case 1:
			switch(dir) {
			case UP:
				dirList.add((Object) Direction.RIGHT);
				dirList.add((Object) Direction.LEFT);
				break;
			case RIGHT:
				dirList.add((Object) Direction.DOWN);
				dirList.add((Object) Direction.UP);
				break;
			case DOWN:
				dirList.add((Object) Direction.LEFT);
				dirList.add((Object) Direction.RIGHT);
				break;
			case LEFT:
				dirList.add((Object) Direction.UP);
				dirList.add((Object) Direction.DOWN);
				break;
			default:
				break;
			}
			break;
		case 2:
			switch(dir) {
			case UP:
				dirList.add((Object) Direction.RIGHT);
				dirList.add((Object) Direction.LEFT);
				dirList.add((Object) Direction.DOWN);
				break;
			case RIGHT:
				dirList.add((Object) Direction.DOWN);
				dirList.add((Object) Direction.LEFT);
				dirList.add((Object) Direction.UP);
				break;
			case LEFT:
				dirList.add((Object) Direction.DOWN);
				dirList.add((Object) Direction.RIGHT);
				dirList.add((Object) Direction.UP);
				break;
			case DOWN:
				dirList.add((Object) Direction.RIGHT);
				dirList.add((Object) Direction.LEFT);
				dirList.add((Object) Direction.UP);
				break;
			default:
				break;
			}
			break;
		case 3:
			dirList.add((Object) Direction.DOWN);
			dirList.add((Object) Direction.RIGHT);
			dirList.add((Object) Direction.LEFT);
			dirList.add((Object) Direction.UP);
			break;
		default:
			break;
		}
		return dirList;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		int tier = this.getCmdBox().getCmdCardList().size();
		GameController.setSelectable(rotate(tier));
		GameController.setSelectTimes(1);
	}
	@Override
	public void setSpriteValue(int tier) {
		switch(tier) {
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
