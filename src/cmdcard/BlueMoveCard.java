package cmdcard;

import card.base.CmdCard;

import java.util.ArrayList;

import card.base.*;
import logic.*;
import tile.*;
import token.*;

public class BlueMoveCard extends CmdCard implements Attack, Move {
	public BlueMoveCard() {
		this.spriteValue = CardSprite.BLUE_MOVE_CARD_1;
	}
	@Override
	public ArrayList<Object> move(int tier) {
		Mech mech = this.getProgrammedMech();
		Direction dir = mech.getDirection();
		ArrayList<Object> tileList = new ArrayList<Object>();
		for(Tile e:GameController.getBoard().getAdjacentTile(mech.getSelfTile(),tier,dir)) {
			tileList.add((Object) e);
		}
		return tileList;
	}
	@Override
	public ArrayList<Object> attack(int tier) {
		ArrayList<Object> result = new ArrayList<>();
		Mech mech = this.getProgrammedMech();
		Direction dir = mech.getDirection();
		if(GameController.getBoard().getAdjacentTile(mech.getSelfTile(), 1, dir).size()==0) {
			return result;
		}
 		Tile nextTile = GameController.getBoard().getAdjacentTile(mech.getSelfTile(), 1, dir).get(0);
 		if(nextTile.getToken() != null) {
 			result.add((Object) nextTile.getToken());
 		}
		return result;
	}
	@Override
	public void execute() {
		int tier = this.getCmdBox().getCmdCardList().size();
		GameController.setSelectTimes(tier);
		GameController.setSelectable(attack(tier));
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
