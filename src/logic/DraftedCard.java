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
	private ArrayList<CmdBox> draftedBox;

	public DraftedCard() {
		super(10);
		this.setAlignment(Pos.CENTER);
		this.draftedCardList = new ArrayList<CmdCard>();
		this.draftedBox = new ArrayList<CmdBox>();
		for (int i = 0; i < 6; i++) {
			this.draftedCardList.add(randomCard());
		}

		for (int i = 0; i < 6; i++) {
			CmdCard cmdCard = this.draftedCardList.get(i);
			draftedBox.add(new CmdBox(i));
			GraphicsContext selectedCardGC = draftedBox.get(i).getCmdCanvas().getGraphicsContext2D();
			if (cmdCard != null) {
				DrawUtil.drawCard(selectedCardGC,4,4,cmdCard.getSpriteValue());
				DrawUtil.drawCard(selectedCardGC,4,4,CardSprite.SELECTED_CARD);
				draftedBox.get(i).setGraphic(draftedBox.get(i).getCmdCanvas());
			}
		}
		this.getChildren().addAll(draftedBox);
	}

	public void setSelectedDraftedCard(boolean selectCard) {
		if (selectCard) {
			for (int i = 0; i < 6; i++) {
				CmdCard cmdCard = GameController.getDraftedCard().getDraftedCardList().get(i);
				GraphicsContext selectedCardGC = draftedBox.get(i).getCmdCanvas().getGraphicsContext2D();
				selectedCardGC.clearRect(0, 0, 123, 200);
				if (cmdCard != null) {
					if (i == GameController.getSelectedCard()) {
						DrawUtil.drawCard(selectedCardGC,4,4,cmdCard.getSpriteValue());
						DrawUtil.drawCard(selectedCardGC,4,4,CardSprite.SELECTED_CARD);
						draftedBox.get(i).setGraphic(draftedBox.get(i).getCmdCanvas());
					} else {
						DrawUtil.drawCard(selectedCardGC,4,4,cmdCard.getSpriteValue());
						draftedBox.get(i).setGraphic(draftedBox.get(i).getCmdCanvas());
					}
				} else {
					draftedBox.get(i).setCmdCanvas(new Canvas(123, 200));
					draftedBox.get(i).setGraphic(draftedBox.get(i).getCmdCanvas());
					draftedBox.get(i).setDisable(true);
				}
			}
		} else {
			for (int i = 0; i < 6; i++) {
				CmdCard cmdCard = GameController.getDraftedCard().getDraftedCardList().get(i);
				GraphicsContext cmdCardGC = draftedBox.get(i).getCmdCanvas().getGraphicsContext2D();
				cmdCardGC.clearRect(0, 0, 123, 200);
				if (cmdCard != null) {
					DrawUtil.drawCard(cmdCardGC,4,4,cmdCard.getSpriteValue());
					DrawUtil.drawCard(cmdCardGC,4,4,CardSprite.SELECTED_CARD);
					draftedBox.get(i).setGraphic(draftedBox.get(i).getCmdCanvas());
				} else {
					draftedBox.get(i).setCmdCanvas(new Canvas(123, 200));
					cmdCardGC = draftedBox.get(i).getCmdCanvas().getGraphicsContext2D();
					draftedBox.get(i).setGraphic(draftedBox.get(i).getCmdCanvas());
					draftedBox.get(i).setDisable(true);
				}
			}
		}
	}

	// for test
	public DraftedCard(CmdCard cmdCard) {
		this.draftedCardList = new ArrayList<CmdCard>();
		for (int i = 0; i < 6; i++) {
			this.draftedCardList.add(cmdCard);

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

	public void remove(int cmdCard) {
		this.getDraftedCardList().set(cmdCard, null);
	}

	public void setDisableButton(boolean disable) {
		for (Button box : draftedBox) {
			box.setDisable(disable);
		}
	}
}
