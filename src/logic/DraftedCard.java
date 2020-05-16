package logic;

import java.util.ArrayList;

import application.DrawUtil;
import cmdcard.*;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import card.base.CmdCard;

public class DraftedCard extends HBox {
	private ArrayList<CmdCard> draftedCardList;
	private ArrayList<CmdBox> draftedBoxList;

	public DraftedCard() {
		super(10);
		this.setAlignment(Pos.CENTER);
		this.draftedCardList = new ArrayList<CmdCard>();
		this.draftedBoxList = new ArrayList<CmdBox>();
		for (int i = 0; i < 6; i++) {
			this.draftedCardList.add(randomCard());
		}

		for (int i = 0; i < 6; i++) {
			CmdCard cmdCard = this.draftedCardList.get(i);
			draftedBoxList.add(new CmdBox(i));
			GraphicsContext selectedCardGC = draftedBoxList.get(i).getCmdCanvas().getGraphicsContext2D();
			if (cmdCard != null) {
				DrawUtil.drawCard(selectedCardGC,4,4,cmdCard.getSpriteValue());
				DrawUtil.drawCard(selectedCardGC,4,4,CardSprite.SELECTED_CARD);
				draftedBoxList.get(i).setGraphic(draftedBoxList.get(i).getCmdCanvas());
			}
		}
		this.getChildren().addAll(draftedBoxList);
	}

	public void setSelectedDraftedCard(boolean selectCard) {
		if (selectCard) {
			for (int i = 0; i < 6; i++) {
				CmdCard cmdCard = GameController.getDraftedCard().getDraftedCardList().get(i);
				GraphicsContext selectedCardGC = draftedBoxList.get(i).getCmdCanvas().getGraphicsContext2D();
				selectedCardGC.clearRect(0, 0, 123, 200);
				if (cmdCard != null) {
					if (i == GameController.getSelectedCard()) {
						DrawUtil.drawCard(selectedCardGC,4,4,cmdCard.getSpriteValue());
						DrawUtil.drawCard(selectedCardGC,4,4,CardSprite.SELECTED_CARD);
						draftedBoxList.get(i).setGraphic(draftedBoxList.get(i).getCmdCanvas());
					} else {
						DrawUtil.drawCard(selectedCardGC,4,4,cmdCard.getSpriteValue());
						draftedBoxList.get(i).setGraphic(draftedBoxList.get(i).getCmdCanvas());
					}
				} else {
					draftedBoxList.get(i).setCmdCanvas(new Canvas(123, 200));
					draftedBoxList.get(i).setGraphic(draftedBoxList.get(i).getCmdCanvas());
					draftedBoxList.get(i).setDisable(true);
				}
			}
		} else {
			for (int i = 0; i < 6; i++) {
				CmdCard cmdCard = GameController.getDraftedCard().getDraftedCardList().get(i);
				GraphicsContext cmdCardGC = draftedBoxList.get(i).getCmdCanvas().getGraphicsContext2D();
				cmdCardGC.clearRect(0, 0, 123, 200);
				if (cmdCard != null) {
					DrawUtil.drawCard(cmdCardGC,4,4,cmdCard.getSpriteValue());
					DrawUtil.drawCard(cmdCardGC,4,4,CardSprite.SELECTED_CARD);
					draftedBoxList.get(i).setGraphic(draftedBoxList.get(i).getCmdCanvas());
				} else {
					draftedBoxList.get(i).setCmdCanvas(new Canvas(123, 200));
					cmdCardGC = draftedBoxList.get(i).getCmdCanvas().getGraphicsContext2D();
					draftedBoxList.get(i).setGraphic(draftedBoxList.get(i).getCmdCanvas());
					draftedBoxList.get(i).setDisable(true);
				}
			}
		}
	}

	public CmdCard randomCard() {
		CmdCard newCard = null;
		switch ((int) (Math.random() * 12)) {
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
				this.draftedCardList.set(i, this.randomCard());
			}
		}
		setSelectedDraftedCard(false);
	}

	public ArrayList<CmdCard> getDraftedCardList() {
		return draftedCardList;
	}

	public void remove(int slot) {
		this.getDraftedCardList().set(slot, null);
	}

	public void setDisableButton(boolean disable) {
		for (Button box : draftedBoxList) {
			box.setDisable(disable);
		}
	}
}
