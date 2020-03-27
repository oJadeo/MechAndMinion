package cmdcard;

import card.base.CmdCard;

import java.util.ArrayList;

import card.base.Attack;
import card.base.Move;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import logic.Sprite;
import tile.Tile;
import token.Mech;
import token.Minion;
import token.Token;

public class RedMoveCard extends CmdCard implements Attack, Move {
	public RedMoveCard() {
		this.spriteValue = CardSprite.RED_MOVE_CARD_1;
	}
	public ArrayList<Tile> move(int tier) {
		Mech mech = this.getProgrammedMech();
		Direction dir = mech.getDirection();
		GameController.move(mech, dir);
		ArrayList<Tile> tileList = GameController.getBoard().getAdjacentTile(mech.getSelfTile(),tier,dir);
		return tileList;
		
	}
	public ArrayList<Token> attack(int tier) {
		ArrayList<Token> result = new ArrayList<>();
		Mech mech = this.getProgrammedMech();
		Direction dir = mech.getDirection();
		ArrayList<Tile> tileList = new ArrayList<>();
		switch(dir) {
		case UP:
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(),1,Direction.RIGHT));
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(),1,Direction.LEFT));
			break;
		case DOWN:
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(),1,Direction.RIGHT));
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(),1,Direction.LEFT));
			break;
		case RIGHT:
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(),1,Direction.UP));
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(),1,Direction.DOWN));
			break;
		case LEFT:
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(),1,Direction.UP));
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(),1,Direction.DOWN));
			break;
		default:
			break;
		}
		for(Tile tile:tileList) {
			if(tile.getToken() instanceof Token) {
				result.add((Token)tile.getToken());
			}
		}
		return result;
	}
	public void execute() {
		int tier = this.getCmdBox().getCmdCardList().size();
		for(int i=0;i<tier;i++) {
			move(tier);
			attack(tier);
		}	
	}
	@Override
	public void setSpriteValue(int tier) {
		switch(tier) {
		case 1:
			this.spriteValue = CardSprite.RED_MOVE_CARD_1;
			break;
		case 2:
			this.spriteValue = CardSprite.RED_MOVE_CARD_2;
			break;
		case 3:
			this.spriteValue = CardSprite.RED_MOVE_CARD_3;
			break;
		
		}
		
	}

}
