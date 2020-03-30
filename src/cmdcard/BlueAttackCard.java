package cmdcard;

import card.base.*;
import logic.*;
import tile.*;
import token.*;

import java.util.ArrayList;

import card.base.Attack;

public class BlueAttackCard extends CmdCard implements Attack, OnGoing {

	public BlueAttackCard() {
		this.spriteValue = CardSprite.BLUE_ATTACK_CARD_1;
	}

	@Override
	public ArrayList<Object> attack(int tier) {
		ArrayList<Object> result = new ArrayList<Object>();
		for (Tile tile : GameController.getBoard().getAdjacentTile(this.getProgrammedMech().getSelfTile(), 10,
				this.getProgrammedMech().getDirection())) {
			if (tile.getToken() instanceof Token) {
				result.add((Object) tile.getToken());
				break;
			}
		}
		return result;
	}

	@Override
	public void execute(int tier) {
		if (this.attack(tier).size() != 0) {
			GameController.setSelectable(this.attack(tier));
			GameController.setSelectTimes(tier);
		} else {
			GameController.setSelectTimes(0);
		}
	}

	@Override
	public void setSpriteValue(int tier) {
		switch (tier) {
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
