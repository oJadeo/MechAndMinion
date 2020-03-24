package cmdcard;

import card.base.CmdCard;
import logic.CardSprite;
import logic.GameController;
import tile.Tile;
import token.Minion;
import token.Token;

import java.util.ArrayList;

import card.base.Attack;

public class GreenAttackCard extends CmdCard implements Attack {
	private int spriteValue;
	public  GreenAttackCard() {
		this.spriteValue = CardSprite.GREEN_ATTACK_CARD_1;
	}
	@Override
	public ArrayList<Minion> attack(int tier) {
		// TODO Auto-generated method stub
		
		ArrayList<Minion> result = new ArrayList<>();
		int x = this.getProgrammedMech().getSelfTile().getLocationX;
		int y = this.getProgrammedMech().getSelfTile().getLocationY;
		ArrayList<Tile> tileList = GameController.getBoard().getBorder(GameController.getBoard().getTile(x,y), tier);
		for(Tile tile:tileList) {
			if(tile.getToken() instanceof Minion) {
				result.add((Minion)tile.getToken());
			}
		}
		return result;
		
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	public void setSpriteValue(int tier) {
		switch(tier) {
		case 1:
			this.spriteValue = CardSprite.GREEN_ATTACK_CARD_1;
			break;
		case 2:
			this.spriteValue = CardSprite.GREEN_ATTACK_CARD_2;
			break;
		case 3:
			this.spriteValue = CardSprite.GREEN_ATTACK_CARD_3;
			break;
		
		}
	}

}
