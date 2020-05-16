package cmdcard;

import card.base.CmdCard;

import java.util.ArrayList;

import card.base.*;
import logic.*;
import tile.*;
import token.*;

public class BlueMoveCard extends CmdCard implements Attack, Move, OnGoing {

	public BlueMoveCard() {
		super.setSpriteValue(CardSprite.BLUE_MOVE_CARD_1);
		this.setCardType("Blue");
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
		for (Tile tile : GameController.getBoard().getAdjacentTile(this.getProgrammedMech().getSelfTile(), 1,
				this.getProgrammedMech().getDirection())) {
			if (tile.getToken() != null) {
				result.add((Object) tile.getToken());
			}
		}
		return result;
	}

	@Override
	public void execute(int tier) {
		GameController.setSelectTimes(tier);
		if (attack(tier).size() != 0) {
			GameController.setSelectable(attack(tier));
		} else {
			GameController.setSelectable(move(tier));
			if (move(tier).size() == 0) {
				GameController.setSelectTimes(0);
				GameController.setProgramCount(GameController.getProgramCount() + 1);
				GameController.execute(GameController.getProgramCount());
			}
		}
	}

	@Override
	public void setSpriteValue(int tier) {
		switch (tier) {
		case 1:
			super.setSpriteValue(CardSprite.BLUE_MOVE_CARD_1);
			break;
		case 2:
			super.setSpriteValue(CardSprite.BLUE_MOVE_CARD_2);
			break;
		case 3:
			super.setSpriteValue(CardSprite.BLUE_MOVE_CARD_3);
			break;

		}

	}
}
