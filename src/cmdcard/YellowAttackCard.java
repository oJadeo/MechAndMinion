package cmdcard;

import card.base.*;
import logic.*;
import tile.*;
import token.*;
import java.util.ArrayList;

public class YellowAttackCard extends CmdCard implements Attack, OnGoing {

	public YellowAttackCard() {
		this.spriteValue = CardSprite.YELLOW_ATTACK_CARD_1;
		this.setCardType("Yellow");
	}

	@Override
	public ArrayList<Object> attack(int tier) {
		ArrayList<Object> resultList = new ArrayList<Object>();
		for (Tile tile : GameController.getBoard().getDiagonalTile(this.getProgrammedMech().getSelfTile(), 1,
				Direction.ALL)) {
			if (tile.getToken() instanceof Token && !tile.getToken().equals(this.getProgrammedMech())) {
				resultList.add((Object) tile.getToken());
			}
		}
		return resultList;
	}

	@Override
	public void execute(int tier) {
		if(attack(tier).size()!=0) {
			GameController.setSelectable(attack(tier));
			GameController.setSelectTimes(tier*2);
		}else {
			GameController.setSelectTimes(0);
			GameController.setProgramCount(GameController.getProgramCount()+1);
			GameController.execute(GameController.getProgramCount());
		}
	}

	@Override
	public void setSpriteValue(int tier) {
		switch (tier) {
		case 1:
			this.spriteValue = CardSprite.YELLOW_ATTACK_CARD_1;
			break;
		case 2:
			this.spriteValue = CardSprite.YELLOW_ATTACK_CARD_2;
			break;
		case 3:
			this.spriteValue = CardSprite.YELLOW_ATTACK_CARD_3;
			break;

		}
	}
}
