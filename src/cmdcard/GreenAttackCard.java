package cmdcard;

import card.base.*;
import logic.*;
import tile.*;
import token.*;
import java.util.ArrayList;

public class GreenAttackCard extends CmdCard implements Attack,OnGoing{

	public GreenAttackCard() {
		this.spriteValue = CardSprite.GREEN_ATTACK_CARD_1;
	}

	@Override
	public ArrayList<Object> attack(int tier) {
		// TODO Auto-generated method stub
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
		}
	}

	public void setSpriteValue(int tier) {
		switch (tier) {
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
