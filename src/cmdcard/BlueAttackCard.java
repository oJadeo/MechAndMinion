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
	public ArrayList<Object> attack(int tier) {
		ArrayList<Object> result = new ArrayList<Object>();
		Mech mech = this.getProgrammedMech();
		Direction dir = mech.getDirection();
		ArrayList<Tile> tileList = GameController.getBoard().getAdjacentTile(mech.getSelfTile(), 10, dir);
		for(int i=0;i<tileList.size();i++) {
			if(tileList.get(i).getToken() instanceof Token) {
				result.add((Object)tileList.get(i).getToken());
			}
			if(result.size()==tier) {
				break;
			}
		}
		return result;
	}
	@Override
	public void execute() {
		int tier = this.getCmdBox().getCmdCardList().size();
		GameController.setSelectable(this.attack(tier));
		GameController.setSelectTimes(this.attack(tier).size());
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
