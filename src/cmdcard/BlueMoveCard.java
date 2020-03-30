package cmdcard;

import card.base.CmdCard;

import java.util.ArrayList;

import card.base.*;
import logic.*;
import tile.*;
import token.*;

public class BlueMoveCard extends CmdCard implements Attack, Move ,OnGoing {
	
	public BlueMoveCard() {
		this.spriteValue = CardSprite.BLUE_MOVE_CARD_1;
	}

	@Override
	public ArrayList<Object> move(int tier) {
		Mech mech = this.getProgrammedMech();
		Direction dir = mech.getDirection();
		ArrayList<Object> tileList = new ArrayList<Object>();
		for (Tile e : GameController.getBoard().getAdjacentTile(mech.getSelfTile(), 1, dir)) {
			tileList.add((Object) e);
		}
		return tileList;
	}

	@Override
	public ArrayList<Object> attack(int tier) {
		ArrayList<Object> result = new ArrayList<>();
		for(Tile tile:GameController.getBoard().getAdjacentTile(this.getProgrammedMech().getSelfTile(), 1, this.getProgrammedMech().getDirection())) {
			if(tile.getToken()!=null) {
				result.add((Object) tile.getToken());
			}
		}
		return result;
	}

	@Override
	public void execute(int tier) {
		GameController.setSelectTimes(tier);
		GameController.setSelectable(attack(tier));
	}

	@Override
	public void setSpriteValue(int tier) {
		switch (tier) {
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
