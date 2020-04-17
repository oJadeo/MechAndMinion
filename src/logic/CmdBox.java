package logic;

import java.util.ArrayList;

import application.DrawUtil;
import card.base.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import token.Mech;

public class CmdBox extends Button {

	private ArrayList<CmdCard> cmdCardList;
	private Canvas cmdCanvas;
	int no = 0;

	// for drafted Card
	public CmdBox(int a) {
		cmdCanvas = new Canvas(123, 200);
		this.cmdCardList = new ArrayList<CmdCard>();
		this.cmdCardList.add(null);

		this.setPadding(new Insets(0));
		this.setPrefSize(115, 192);
		this.setGraphic(getCanvas(true));
		this.setOnMouseClicked((event) -> {
			GameController.setSelectedCard(a);
		});
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("enter");
				if (GameController.getDraftedCard().getDraftedCardList().get(a) != null) {
					GameController.getCardPane()
							.setShowingCard(GameController.getDraftedCard().getDraftedCardList().get(a));
				}
			}

		});
		this.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (GameController.getSelectedCard() != 6) {
					GameController.getCardPane().setShowingCard(
							GameController.getDraftedCard().getDraftedCardList().get(GameController.getSelectedCard()));
				}
			}
		});
		this.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				Dragboard db = ((Button) event.getSource()).startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				content.putString(Integer.toString(a));
				db.setContent(content);
				GameController.setSelectedCard(a);
				event.consume();
			};
		});

	}

	// for Mech
	public CmdBox(Mech programmedMech) {
		super();

		cmdCanvas = new Canvas(123, 200);
		this.cmdCardList = new ArrayList<CmdCard>();
		this.cmdCardList.add(null);

		this.setPadding(new Insets(0));
		this.setPrefSize(115, 192);
		this.setGraphic(getCanvas(true));
		CmdBox thisCmdBox = this;
		this.setOnMouseClicked((e) -> {
			if (GameController.getCurrentPhase() == Phase.Program) {
				GameController.setSelectedSlot(programmedMech, thisCmdBox);
			}
		});
		this.setOnDragEntered(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				GameController.getRedMech().getCmdBoard().setSelectedCmdBox(null);
				GameController.getBlueMech().getCmdBoard().setSelectedCmdBox(null);
				Dragboard db = event.getDragboard();
				GraphicsContext cmdGC = thisCmdBox.cmdCanvas.getGraphicsContext2D();
				cmdGC.clearRect(0, 0, 123, 200);
				int upgraded = 0;
				if (thisCmdBox.cmdCardList.get(0) != null
						&& thisCmdBox.cmdCardList.get(0).getCardType().equals(GameController.getDraftedCard()
								.getDraftedCardList().get(Integer.parseInt(db.getString())).getCardType())) {
					switch (thisCmdBox.cmdCardList.size()) {
					case 1:
						if (thisCmdBox.cmdCardList.get(0) == null) {
							upgraded = 0;
						} else {
							upgraded = 1;
						}
						break;
					case 2:
					case 3:
						upgraded = 2;
					default:
						break;
					}
				}
				DrawUtil.drawCard(cmdGC, 4, 4, GameController.getDraftedCard().getDraftedCardList()
						.get(Integer.parseInt(db.getString())).getSpriteValue() + upgraded);
				DrawUtil.drawCard(cmdGC, 4, 4, CardSprite.SELECTED_CARD);
				thisCmdBox.setGraphic(thisCmdBox.cmdCanvas);
				event.consume();
			}
		});
		this.setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				event.acceptTransferModes(TransferMode.MOVE);
				event.consume();
			}
		});
		this.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					GameController.setSelectedSlot(programmedMech, thisCmdBox);
					success = true;
				}
				event.setDropCompleted(success);
				event.consume();
			}
		});
	}

	public void addCmdCard(CmdCard selectedCard) {
		if (this.cmdCardList.get(this.cmdCardList.size() - 1) == null) {
			this.cmdCardList.clear();
			this.cmdCardList.add(selectedCard);
		} else if (this.cmdCardList.get(this.cmdCardList.size() - 1).getCardType().equals(selectedCard.getCardType())) {
			switch (this.cmdCardList.size()) {
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
		} else {
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

	public Canvas getCanvas(boolean selected) {
		GraphicsContext cmdGC = this.cmdCanvas.getGraphicsContext2D();
		cmdGC.clearRect(0, 0, 115, 192);
		if (this.cmdCardList.get(this.cmdCardList.size() - 1) != null) {
			DrawUtil.drawCard(cmdGC, 4, 4, this.cmdCardList.get(this.cmdCardList.size() - 1).getSpriteValue());
		}
		if (selected) {
			DrawUtil.drawCard(cmdGC, 4, 4, CardSprite.SELECTED_CARD);
		}
		return cmdCanvas;
	}

	public void execute() {
		this.setDisable(false);
		GameController.getRedMech().getCmdBoard().setSelectedCmdBox(this);
		GameController.getBlueMech().getCmdBoard().setSelectedCmdBox(this);
		if (this.cmdCardList.get(this.cmdCardList.size() - 1) == null) {
			GameController.setProgramCount(GameController.getProgramCount() + 1);
			GameController.execute(GameController.getProgramCount());
		} else {
			GameController.setExecutingProgram(this.cmdCardList.get(this.cmdCardList.size() - 1));
			((OnGoing) this.cmdCardList.get(this.cmdCardList.size() - 1)).execute(this.cmdCardList.size());
		}
	}

	public void setCmdCardList(ArrayList<CmdCard> cmdCardList) {
		this.cmdCardList = cmdCardList;
	}

	public ArrayList<CmdCard> getCmdCardList() {
		return this.cmdCardList;
	}

	public Canvas getCmdCanvas() {
		return cmdCanvas;
	}

	public void setCmdCanvas(Canvas cmdCanvas) {
		this.cmdCanvas = cmdCanvas;
	}
}
