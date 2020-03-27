package cmdcard;

import card.base.CmdCard;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import tile.Tile;
import token.Mech;
import token.Minion;
import token.Token;

import java.util.ArrayList;

import card.base.Attack;

public class RedAttackCard extends CmdCard implements Attack {
	private int spriteValue ;
	public RedAttackCard() {
		this.spriteValue = CardSprite.RED_ATTACK_CARD_1;
	}
	@Override
	public ArrayList<Token> attack(int tier) {
		ArrayList<Token> result = new ArrayList<>();
		int x = this.getProgrammedMech().getSelfTile().getLocationX();
		int y = this.getProgrammedMech().getSelfTile().getLocationY();
		Mech mech = (Mech) GameController.getBoard().getTile(x, y).getToken();
		Direction dir = mech.getDirection();
		ArrayList<Tile> tileList = new ArrayList<>();
		switch(tier) {
		case 1:
			tileList.addAll(GameController.getBoard().getAdjacentTile(GameController.getBoard().getTile(x,y),2,dir));
			break;
		case 2:
			tileList.addAll(GameController.getBoard().getAdjacentTile(GameController.getBoard().getTile(x,y),2,dir));
			tileList.addAll(GameController.getBoard().getDiagonalTile(GameController.getBoard().getTile(x,y),2,dir));
			break;
		case 3:
			tileList.addAll(GameController.getBoard().getAdjacentTile(GameController.getBoard().getTile(x,y),2,dir));
			tileList.addAll(GameController.getBoard().getDiagonalTile(GameController.getBoard().getTile(x,y),2,dir));
			switch(dir) {
			case UP:
				if(GameController.getBoard().isMovePossible(x-1, y-2)) {
					tileList.add(GameController.getBoard().getTile(x-1, y-2));
				}if(GameController.getBoard().isMovePossible(x+1, y-2)) {
					tileList.add(GameController.getBoard().getTile(x+1, y-2));
				}
				break;
			case DOWN:
				if(GameController.getBoard().isMovePossible(x-1, y+2)) {
					tileList.add(GameController.getBoard().getTile(x-1, y+2));
				}if(GameController.getBoard().isMovePossible(x+1, y+2)) {
					tileList.add(GameController.getBoard().getTile(x+1, y+2));
				}
				break;
			case RIGHT:
				if(GameController.getBoard().isMovePossible(x+2, y-1)) {
					tileList.add(GameController.getBoard().getTile(x+2, y-1));
				}if(GameController.getBoard().isMovePossible(x+2, y+1)) {
					tileList.add(GameController.getBoard().getTile(x+2, y+1));
				}
				break;
			case LEFT:
				if(GameController.getBoard().isMovePossible(x-2, y-1)) {
					tileList.add(GameController.getBoard().getTile(x-2, y-1));
				}if(GameController.getBoard().isMovePossible(x-2, y+1)) {
					tileList.add(GameController.getBoard().getTile(x-2, y+1));
				}
			}
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

	@Override
	public void execute() {

	}
	@Override
	public void setSpriteValue(int tier) {
		switch(tier) {
		case 1:
			this.spriteValue = CardSprite.RED_ATTACK_CARD_1;
			break;
		case 2:
			this.spriteValue = CardSprite.RED_ATTACK_CARD_2;
			break;
		case 3:
			this.spriteValue = CardSprite.RED_ATTACK_CARD_3;
			break;
		
		}
	}
	

}
