package logic;

import java.util.ArrayList;

import card.CmdCard;
import card.base.Card;

public class CmdBox {
	private ArrayList<CmdCard> cmdCardList;
	public CmdBox() {
		this.cmdCardList = new ArrayList<CmdCard>();
	}
	public Boolean addCmdCard(CmdCard selectedCard) {
		if(this.cmdCardList.get(0).getCardType() == selectedCard.getCardType()) {
			switch(this.cmdCardList.size()) {
			case 1:
				this.cmdCardList.add(selectedCard);
				selectedCard.setSpriteValue(0);
				GameController.update();
				break;
			case 2:
				this.cmdCardList.add(selectedCard);
				selectedCard.setSpriteValue(0);
				GameController.update();
				break;
			case 3:
				this.cmdCardList.set(2, selectedCard);
				selectedCard.setSpriteValue(0);
				GameController.update();
				break;
			}
			return true;
		}
	}
	public Boolean addDamageCard() {
		
	}
	public void update() {
		
	}
	public void execute() {
		
	}

}
