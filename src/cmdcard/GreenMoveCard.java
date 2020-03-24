package cmdcard;

import java.util.ArrayList;

import card.base.CmdCard;
import card.base.Move;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import logic.Sprite;
import tile.Tile;
import token.Mech;

public class GreenMoveCard extends CmdCard implements Move {
	private int spriteValue;
	public GreenMoveCard() {
		this.spriteValue = CardSprite.GREEN_MOVE_CARD_1;
	}
	@Override
	public ArrayList<Tile> move(int tier) {
		int x = this.getProgrammedMech().getSelfTile().getLocationX;
		int y = this.getProgrammedMech().getSelfTile().getLocationY;
		Mech mech = (Mech) GameController.getBoard().getTile(x, y).getToken();
		Direction dir = mech.getDirection();
		ArrayList<Tile> tileList = GameController.getBoard().getAdjacentTile(mech.getSelfTile(),tier,dir);
		switch(dir) {
		
		case UP:
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(),tier,Direction.RIGHT));
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(),tier,Direction.LEFT));
			break;
		case DOWN:
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(),tier,Direction.RIGHT));
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(),tier,Direction.LEFT));
			break;
		case RIGHT:
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(),tier,Direction.DOWN));
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(),tier,Direction.UP));
			break;
		case LEFT:
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(),tier,Direction.DOWN));
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(),tier,Direction.UP));
			break;
		default:
			break;
		}
		return tileList;
		
		

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	public void setSpriteValue(int tier) {
		switch(tier) {
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
