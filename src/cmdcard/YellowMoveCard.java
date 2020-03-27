package cmdcard;

import java.util.ArrayList;

import card.base.CmdCard;
import card.base.Move;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import tile.Tile;
import token.Mech;

public class YellowMoveCard extends CmdCard implements Move {
	private int spriteValue;
	public YellowMoveCard() {
		this.spriteValue = CardSprite.YELLOW_MOVE_CARD_1;
	}
	@Override
	public ArrayList<Tile> move(int tier) {
		int x = this.getProgrammedMech().getSelfTile().getLocationX();
		int y = this.getProgrammedMech().getSelfTile().getLocationY();
		Mech mech = (Mech) GameController.getBoard().getTile(x, y).getToken();
		Direction dir = mech.getDirection();
		ArrayList<Tile> tileList = new ArrayList<>();
		ArrayList<Tile> tileList1 = new ArrayList<>();
		ArrayList<Tile> tileList2 = new ArrayList<>();
		switch(tier) {
		case 1:
			tileList = GameController.getBoard().getAdjacentTile(mech.getSelfTile(),2,dir);
			break;
		case 2:
			tileList1 = GameController.getBoard().getAdjacentTile(mech.getSelfTile(),1,dir);
			tileList2 = GameController.getBoard().getAdjacentTile(mech.getSelfTile(),2,dir);
			for(Tile tile:tileList2) {
				if(!tileList1.contains(tile))
					tileList.add(tile);
			}
			break;
		case 3:
			tileList1 = GameController.getBoard().getAdjacentTile(mech.getSelfTile(),3,dir);
			tileList2 = GameController.getBoard().getAdjacentTile(mech.getSelfTile(),6,dir);
			for(Tile tile:tileList2) {
				if(!tileList1.contains(tile))
					tileList.add(tile);
			}
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

	@Override
	public void setSpriteValue(int tier) {
		switch(tier) {
		case 1:
			this.spriteValue = CardSprite.YELLOW_MOVE_CARD_1;
			break;
		case 2:
			this.spriteValue = CardSprite.YELLOW_MOVE_CARD_2;
			break;
		case 3:
			this.spriteValue = CardSprite.YELLOW_MOVE_CARD_3;
			break;
		}
	}


}
