package cmdcard;

import card.base.*;
import logic.*;
import tile.*;
import token.*;
import java.util.ArrayList;

public class GreenAttackCard extends CmdCard implements Attack,OnGoing{

	public GreenAttackCard() {
		super.setSpriteValue(CardSprite.GREEN_ATTACK_CARD_1);
		this.setCardType("Green");
	}

	@Override
	public ArrayList<Object> attack(int tier) {
		ArrayList<Object> result = new ArrayList<Object>();
		int x = this.getProgrammedMech().getSelfTile().getLocationX();
		int y = this.getProgrammedMech().getSelfTile().getLocationY();
		ArrayList<Tile> tileList = GameController.getBoard().getBorder(GameController.getBoard().getTile(x, y), tier);
		for (Tile tile : tileList) {
			if (tile.getToken() instanceof Token &&
					!tile.getToken().equals(this.getProgrammedMech())) {
				result.add((Object) tile.getToken());
			}
		}
		return result;

	}

	@Override
	public void execute(int tier) {
		if(attack(tier).size()!=0) {
			GameController.setSelectTimes(1);
			GameController.setSelectable(attack(tier));
		}else {
			GameController.setSelectTimes(0);
			GameController.setProgramCount(GameController.getProgramCount()+1);
			GameController.execute(GameController.getProgramCount());
		}
	}

	public void setSpriteValue(int tier) {
		switch (tier) {
		case 1:
			super.setSpriteValue(CardSprite.GREEN_ATTACK_CARD_1);
			break;
		case 2:
			super.setSpriteValue(CardSprite.GREEN_ATTACK_CARD_2);
			break;
		case 3:
			super.setSpriteValue(CardSprite.GREEN_ATTACK_CARD_3);
			break;

		}
	}

}
