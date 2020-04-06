package logic;

import java.util.ArrayList;
import cmdcard.*;
import card.base.CmdCard;

public class DraftedCard {
	private ArrayList<CmdCard> draftedCardList;
	private int statingPositionX;
	private int statingPositionY;
	public final int OFFSETX = 0;
	public final int OFFSETY = 0;
	

	public DraftedCard() {
		this.draftedCardList = new ArrayList<CmdCard>();
		for (int i = 0; i < 6; i++) {
			this.draftedCardList.add(randomCard());
		}
	}
	//for test
	public DraftedCard(CmdCard cmdCard) {
		this.draftedCardList = new ArrayList<CmdCard>();
		for (int i = 0; i < 6; i++) {
			this.draftedCardList.add(cmdCard);
			
		}
	}
	public CmdCard randomCard() {
		CmdCard newCard = null;
		switch ((int)(Math.random()*12)) {
		case 0:
			newCard = new BlueAttackCard();
			break;
		case 1:
			newCard = new BlueMoveCard();
			break;
		case 2:
			newCard = new BlueRotateCard();
			break;
		case 3:
			newCard = new GreenAttackCard();
			break;
		case 4:
			newCard = new GreenMoveCard();
			break;
		case 5:
			newCard = new GreenRotateCard();
			break;
		case 6:
			newCard = new RedAttackCard();
			break;
		case 7:
			newCard = new RedMoveCard();
			break;
		case 8:
			newCard = new RedRotateCard();
			break;
		case 9:
			newCard = new YellowAttackCard();
			break;
		case 10:
			newCard = new YellowMoveCard();
			break;
		case 11:
			newCard = new YellowRotateCard();
			break;
		default:
			break;
		}
		return newCard;
	}

	public void reDeal() {
		for (int i = 0; i < 6; i++) {
			if (this.draftedCardList.get(i) == null) {
				this.draftedCardList.set(i,this.randomCard());
			}
		}
	}

	public void update() {
		String result = "DraftedCard : [";
		for (CmdCard card : this.draftedCardList) {
			if (card instanceof BlueAttackCard) {
				result += " BA ";
			} else if (card instanceof BlueMoveCard) {
				result += " BM ";
			} else if (card instanceof BlueRotateCard) {
				result += " BR ";
			} else if (card instanceof RedAttackCard) {
				result += " RA ";
			} else if (card instanceof RedRotateCard) {
				result += " RR ";
			} else if (card instanceof RedMoveCard) {
				result += " RM ";
			} else if (card instanceof GreenAttackCard) {
				result += " GA ";
			} else if (card instanceof GreenMoveCard) {
				result += " GM ";
			} else if (card instanceof GreenRotateCard) {
				result += " GR ";
			} else if (card instanceof YellowAttackCard) {
				result += " YA ";
			} else if (card instanceof YellowMoveCard) {
				result += " YM ";
			} else if (card instanceof YellowRotateCard) {
				result += " YR ";
			}
			if (card == null) {
				result += " [] ";
			}
		}
		result += "]";
		System.out.println(result);
	}

	public ArrayList<CmdCard> getDraftedCardList() {
		return draftedCardList;
	}
	public void remove(CmdCard cmdCard) {
		for(int i = 0;i<this.getDraftedCardList().size();i++) {
			if(cmdCard.equals(this.getDraftedCardList().get(i))) {
				this.getDraftedCardList().set(i,null);
			}
		}
	}
}
