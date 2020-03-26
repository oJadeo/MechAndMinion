package logic;

import java.util.ArrayList;
import card.base.CmdCard;
public class CmdBox {
	private ArrayList<CmdCard> cmdCardList;
	public CmdBox() {
		this.cmdCardList = new ArrayList<CmdCard>();
		for(int  i=0;i<6;i++) {
			this.cmdCardList.add(null);
		}
	}
	public Boolean addCmdCard(CmdCard selectedCard) {
		if(this.cmdCardList.get(this.cmdCardList.size()-1).getCardType() == selectedCard.getCardType()) {
			switch(this.cmdCardList.size()) {
			case 0:
				this.cmdCardList.add(selectedCard);
				selectedCard.setSpriteValue(1);
				GameController.update();
				break;
			case 1:
				this.cmdCardList.add(selectedCard);
				selectedCard.setSpriteValue(2);
				GameController.update();
				break;
			case 2:
				this.cmdCardList.add(selectedCard);
				selectedCard.setSpriteValue(3);
				GameController.update();
				break;
			case 3:
				this.cmdCardList.set(2, selectedCard);
				selectedCard.setSpriteValue(3);
				GameController.update();
				break;
			}
			return true;
		}return false;
	}
	public void addDamageCard(CmdCard damageCard) {
		this.cmdCardList.add(damageCard);
	}
	public void update() {
		
	}
	public boolean execute() {
		//return this.cmdCardList.get(this.cmdCardList.size()-1).execute();
		return true;
	}

}
