package cmdcard;

import card.base.CmdCard;

import java.util.ArrayList;

import card.base.Attack;
import card.base.Move;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import tile.Tile;
import token.Mech;
import token.Minion;
import token.Token;

public class BlueMoveCard extends CmdCard implements Attack, Move {
	public BlueMoveCard() {
		this.spriteValue = CardSprite.BLUE_MOVE_CARD_1;
	}
	@Override
	public ArrayList<Tile> move(int tier) {
		Mech mech = this.getProgrammedMech();
		Direction dir = mech.getDirection();
		GameController.move(mech, dir);
		ArrayList<Tile> tileList = GameController.getBoard().getAdjacentTile(mech.getSelfTile(),tier,dir);
		return tileList;
	}
	@Override
	public ArrayList<Token> attack(int tier) {
		ArrayList<Token> result = new ArrayList<>();
		Mech mech = this.getProgrammedMech();
		Direction dir = mech.getDirection();
		if(GameController.getBoard().getAdjacentTile(mech.getSelfTile(), 1, dir).size()==0) {
			return null;
		}
 		Tile nextTile = GameController.getBoard().getAdjacentTile(mech.getSelfTile(), 1, dir).get(0);
 		if(nextTile.getToken() != null) {
 			nextTile.getToken().damaged();
 		}
		return result;
	}
	@Override
	public void execute() {
		int tier = this.getCmdBox().getCmdCardList().size();
		for(int i=0;i<tier;i++) {
			attack(tier);
			move(tier);
		}	
	}
	@Override
	public void setSpriteValue(int tier) {
		switch(tier) {
		case 1:
			this.spriteValue = CardSprite.BLUE_MOVE_CARD_1;
			break;
		case 2:
			this.spriteValue = CardSprite.BLUE_MOVE_CARD_2;
			break;
		case 3:
			this.spriteValue = CardSprite.BLUE_MOVE_CARD_3;
			break;
		
		}
		
	}
}
