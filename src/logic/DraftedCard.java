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
		for(int i=0;i<6;i++) {
			this.draftedCardList.add(this.randomCard());
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
	public CmdCard getCmdCard(int slot) {
		return draftedCardList.get(slot);
	}
}
