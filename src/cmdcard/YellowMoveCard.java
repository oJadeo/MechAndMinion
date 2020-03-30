package cmdcard;

import java.util.ArrayList;
import card.base.*;
import logic.*;
import tile.*;

public class YellowMoveCard extends CmdCard implements Move, OnGoing {

	public YellowMoveCard() {
		this.spriteValue = CardSprite.YELLOW_MOVE_CARD_1;
	}

	@Override
	public ArrayList<Object> move(int tier) {
		ArrayList<Object> resultList = new ArrayList<Object>();
		for (Tile tile : GameController.getBoard().getAdjacentTile(this.getProgrammedMech().getSelfTile(), 1,
				this.getProgrammedMech().getDirection())) {
			resultList.add((Object) tile);
		}
		return resultList;
	}

	@Override
	public void execute(int tier) {
		// TODO Auto-generated method stub
		GameController.setStepCount(0);
		if(move(tier).size()!=0) {
			GameController.setSelectable(move(tier));
			GameController.setSelectTimes(tier * 2);
		}else {
			GameController.setSelectTimes(0);
		}
	}

	@Override
	public void setSpriteValue(int tier) {
		switch (tier) {
		case 1:
			this.spriteValue = CardSprite.YELLOW_MOVE_CARD_1;
			break;
		case 2:
			this.spriteValue = CardSprite.YELLOW_MOVE_CARD_2;
			break;
		case 3:
			this.spriteValue = CardSprite.YELLOW_MOVE_CARD_3;
			break;
		}
	}

}
