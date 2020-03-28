package logic;

import java.util.ArrayList;
import card.base.CmdCard;
public class CmdBox {
	private ArrayList<CmdCard> cmdCardList;
	public CmdBox() {
		this.cmdCardList = new ArrayList<CmdCard>();
		this.cmdCardList.add(null);
	}
	public void addCmdCard(CmdCard selectedCard) {
		if(this.cmdCardList.get(this.cmdCardList.size()-1)==null) {
			this.cmdCardList.clear();
			this.cmdCardList.add(selectedCard);
		}else if(this.cmdCardList.get(this.cmdCardList.size()-1).getCardType() == selectedCard.getCardType()) {
			switch(this.cmdCardList.size()) {
			case 0:
				this.cmdCardList.add(selectedCard);
				selectedCard.setSpriteValue(1);
				break;
			case 1:
				this.cmdCardList.add(selectedCard);
				selectedCard.setSpriteValue(2);
				break;
			case 2:
				this.cmdCardList.add(selectedCard);
				selectedCard.setSpriteValue(3);
				break;
			case 3:
				this.cmdCardList.set(2, selectedCard);
				selectedCard.setSpriteValue(3);
				break;
			}
		}else {
			cmdCardList.clear();
			cmdCardList.add(selectedCard);
		}
		selectedCard.setCmdBox(this);
	}
	public void addDamageCard(CmdCard damageCard) {
		this.cmdCardList.clear();
		this.cmdCardList.add(damageCard);
		damageCard.setCmdBox(this);
	}
	public void update() {
		
	}
	public void execute() {
		if(this.cmdCardList.get(this.cmdCardList.size()-1)==null) {
			GameController.setProgramCount(GameController.getProgramCount()+1);
			GameController.execute(GameController.getProgramCount());
		}else {
			GameController.setExecutingProgram(this.cmdCardList.get(this.cmdCardList.size()-1));
			this.cmdCardList.get(this.cmdCardList.size()-1).execute();
		}
	}
	public void setCmdCardList(ArrayList<CmdCard> cmdCardList) {
		this.cmdCardList = cmdCardList;
	}
	public ArrayList<CmdCard> getCmdCardList(){
		return this.cmdCardList;
	}

}
