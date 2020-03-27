package cmdcard;

import card.base.CmdCard;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import tile.Tile;
import token.Mech;
import token.Token;

import java.util.ArrayList;

import card.base.Attack;

public class BlueAttackCard extends CmdCard implements Attack {
	private int spriteValue;
	public BlueAttackCard() {
		this.spriteValue = CardSprite.BLUE_ATTACK_CARD_1;
	}
	@Override
	public ArrayList<Token> attack(int tier) {
		ArrayList<Token> result = new ArrayList<>();
		int x = this.getProgrammedMech().getSelfTile().getLocationX();
		int y = this.getProgrammedMech().getSelfTile().getLocationY();
		Mech mech = (Mech) GameController.getBoard().getTile(x, y).getToken();
		Direction dir = mech.getDirection();
		ArrayList<Tile> tileList = GameController.getBoard().getAdjacentTile(mech.getSelfTile(), 10, dir);
		for(int i=0;i<tier;i++) {
			if(tileList.get(i).getToken() instanceof Token) {
				result.add((Token)tileList.get(i).getToken());
			}
		}
		return result;
	}
	@Override
	public void execute() {
		int tier = this.getCmdBox().getCmdCardList().size();
		for(Token token: attack(tier)) {
			token.damaged();
		}
		
	}
	@Override
	public void setSpriteValue(int tier) {
		switch(tier) {
		case 1:
			this.spriteValue = CardSprite.BLUE_ATTACK_CARD_1;
			break;
		case 2:
			this.spriteValue = CardSprite.BLUE_ATTACK_CARD_2;
			break;
		case 3:
			this.spriteValue = CardSprite.BLUE_ATTACK_CARD_3;
			break;
		
		}
		
	}
	

}
