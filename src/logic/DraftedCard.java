package logic;

import java.util.ArrayList;
import cmdcard.*;
import card.base.CmdCard;


public class DraftedCard {
	private ArrayList<CmdCard> draftedCardList;
	private int statingPositionX;
	private int statingPositionY;
	public final int OFFSETX=0;
	public final int OFFSETY=0;
	public DraftedCard() {
		this.draftedCardList = new ArrayList<CmdCard>();
		for(int i=0;i<6;i++) {
			this.draftedCardList.add(new BlueRotateCard());
			//this.draftedCardList.add(randomCard());
		}
	}
	public CmdCard randomCard() {
		double random = Math.random();
		CmdCard newCard;
		if(random < 1.0/12.0) {
			newCard = new BlueAttackCard();
		}else if(random < 2.0/12.0) {
			newCard = new BlueMoveCard();
		}else if(random < 3.0/12.0) {
			newCard = new BlueRotateCard();
		}else if(random < 4.0/12.0) {
			newCard = new GreenAttackCard();
		}else if(random < 5.0/12.0) {
			newCard = new GreenMoveCard();
		}else if(random < 6.0/12.0) {
			newCard = new GreenRotateCard();
		}else if(random < 7.0/12.0) {
			newCard = new RedAttackCard();
		}else if(random < 8.0/12.0) {
			newCard = new RedMoveCard();
		}else if(random < 9.0/12.0) {
			newCard = new RedRotateCard();
		}else if(random < 10.0/12.0) {
			newCard = new YellowAttackCard();
		}else if(random < 11.0/12.0) {
			newCard = new YellowMoveCard();
		}else{
			newCard = new YellowRotateCard();
		}
		return newCard;
	}
	public void reDeal() {
		for(CmdCard card:this.draftedCardList) {
			if(card == null) {
				card = this.randomCard();
			}
		}
	}
	public void update() {
		String result = "DraftedCard : [";
		for(CmdCard card:this.draftedCardList) {
			if(card instanceof BlueAttackCard) {
				result += " BA ";
			}else if(card instanceof BlueMoveCard) {
				result += " BM ";
			}else if(card instanceof BlueRotateCard) {
				result += " BR ";
			}else if(card instanceof RedAttackCard) {
				result += " RA ";
			}else if(card instanceof RedRotateCard) {
				result += " RR ";
			}else if(card instanceof RedMoveCard) {
				result += " RM ";
			}else if(card instanceof GreenAttackCard) {
				result += " GA ";
			}else if(card instanceof GreenMoveCard) {
				result += " GM ";
			}else if(card instanceof GreenRotateCard) {
				result += " GR ";
			}else if(card instanceof YellowAttackCard) {
				result += " YA ";
			}else if(card instanceof YellowMoveCard) {
				result += " YM ";
			}else if(card instanceof YellowRotateCard) {
				result += " YR ";
			}if(card == null) {
				result += " [] ";
			}
		}
		result += "]";
		System.out.println(result);
	}
	public ArrayList<CmdCard> getDraftedCardList() {
		return draftedCardList;
	}
}
