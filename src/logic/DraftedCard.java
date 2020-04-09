package logic;

import java.util.ArrayList;

import application.DrawUtil;
import cmdcard.*;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import card.base.CmdCard;

public class DraftedCard extends HBox {
	private ArrayList<CmdCard> draftedCardList;
	private ArrayList<Button> draftedButton;
	private ArrayList<Canvas> draftedCanvas;

	public DraftedCard() {
		super();
		this.draftedCardList = new ArrayList<CmdCard>();
		this.draftedButton = new ArrayList<Button>();
		this.draftedCanvas = new ArrayList<Canvas>();
		for (int i = 0; i < 6; i++) {
			this.draftedCardList.add(randomCard());
		}

		Canvas emptyCanvas = new Canvas(105, 200);
		this.getChildren().add(emptyCanvas);
		for (int i = 0; i < 6; i++) {
			CmdCard cmdCard = this.draftedCardList.get(i);
			draftedButton.add(new Button());
			draftedButton.get(i).setPadding(new Insets(0));
			draftedButton.get(i).setPrefSize(115, 192);
			draftedCanvas.add(new Canvas(115, 192));
			GraphicsContext selectedCardGC = draftedCanvas.get(i).getGraphicsContext2D();
			if (cmdCard != null) {
				selectedCardGC.drawImage(DrawUtil.drawCard(cmdCard.getSpriteValue()), 0, 0);
				selectedCardGC.drawImage(DrawUtil.drawCard(CardSprite.SELECTED_CARD), 0, 0);
				draftedButton.get(i).setGraphic(draftedCanvas.get(i));
			}
			int a = i;
			draftedButton.get(i).setOnMouseClicked((e) -> {
				GameController.setSelectedCard(a);
			});
		}
		this.getChildren().addAll(draftedButton);
	}

	public void setSelectedDraftedCard(boolean selectCard) {
		if (selectCard) {
			for (int i = 0; i < 6; i++) {
				CmdCard cmdCard = GameController.getDraftedCard().getDraftedCardList().get(i);
				GraphicsContext selectedCardGC = draftedCanvas.get(i).getGraphicsContext2D();
				if (cmdCard != null) {
					if (i == GameController.getSelectedCard()) {
						selectedCardGC.drawImage(DrawUtil.drawCard(cmdCard.getSpriteValue()), 0, 0);
						selectedCardGC.drawImage(DrawUtil.drawCard(CardSprite.SELECTED_CARD), 0, 0);
						draftedButton.get(i).setGraphic(draftedCanvas.get(i));
					} else {
						selectedCardGC.drawImage(DrawUtil.drawCard(cmdCard.getSpriteValue()), 0, 0);
						draftedButton.get(i).setGraphic(draftedCanvas.get(i));
					}
				} else {
					draftedCanvas.set(i, new Canvas(115, 192));
					draftedButton.get(i).setGraphic(draftedCanvas.get(i));
					draftedButton.get(i).setDisable(true);
				}
			}
		} else {
			for (int i = 0; i < 6; i++) {
				CmdCard cmdCard = GameController.getDraftedCard().getDraftedCardList().get(i);
				GraphicsContext cmdCardGC = draftedCanvas.get(i).getGraphicsContext2D();
				if (cmdCard != null) {
					cmdCardGC.drawImage(DrawUtil.drawCard(cmdCard.getSpriteValue()), 0, 0);
					cmdCardGC.drawImage(DrawUtil.drawCard(CardSprite.SELECTED_CARD), 0, 0);
					draftedButton.get(i).setGraphic(draftedCanvas.get(i));
				} else {
					draftedCanvas.set(i, new Canvas(115, 192));
					cmdCardGC = draftedCanvas.get(i).getGraphicsContext2D();
					draftedButton.get(i).setGraphic(draftedCanvas.get(i));
					draftedButton.get(i).setDisable(true);
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
		for (Button button : draftedButton) {
			button.setDisable(disable);
		}
	}
}
