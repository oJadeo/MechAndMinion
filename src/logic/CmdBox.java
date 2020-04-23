package logic;

import java.util.ArrayList;

import application.DrawUtil;
import card.base.*;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import token.Mech;

public class CmdBox extends Button {

	private ArrayList<CmdCard> cmdCardList;
	private Mech programmedMech;
	private Canvas cmdCanvas;
	int no = 0;

	// for drafted Card
	public CmdBox(int a) {
		super();
		cmdCanvas = new Canvas(123, 200);
		this.cmdCardList = new ArrayList<CmdCard>();
		this.cmdCardList.add(null);

		this.setPadding(new Insets(0));
		this.setPrefSize(123, 200);
		this.drawCanvas(true);
		this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				GameController.setSelectedCard(a);
			}
			
		});
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (GameController.getDraftedCard().getDraftedCardList().get(a) != null) {
					GameController.getCardPane()
							.setShowingCard(GameController.getDraftedCard().getDraftedCardList().get(a));
				}
			}

		});
		this.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (GameController.getCardPane().getSelectedCard() != null) {
					GameController.getCardPane().setDmgCard(GameController.getCardPane().getSelectedCard());
				} else if (GameController.getSelectedCard() != 6) {
					GameController.getCardPane().setShowingCard(
							GameController.getDraftedCard().getDraftedCardList().get(GameController.getSelectedCard()));
				}
			}
		});
		this.setOnDragDetected(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (GameController.getCurrentPhase() == Phase.Program) {
					Dragboard db = ((Button) event.getSource()).startDragAndDrop(TransferMode.MOVE);
					ClipboardContent content = new ClipboardContent();
					content.putString(Integer.toString(a));
					db.setContent(content);
					GameController.setSelectedCard(a);
					event.consume();
				}
			};
		});
	}

	// for Mech
	public CmdBox(Mech programmedMech) {
		super();
		this.setProgrammedMech(programmedMech);

		cmdCanvas = new Canvas(123, 200);
		this.cmdCardList = new ArrayList<CmdCard>();
		this.cmdCardList.add(null);

		this.setPadding(new Insets(0));
		this.setPrefSize(123, 200);
		this.drawCanvas(true);
		this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		CmdBox thisCmdBox = this;
		this.setOnMouseClicked((e) -> {
			if (GameController.getCurrentPhase() == Phase.Program) {
				GameController.setSelectedSlot(thisCmdBox);
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
		this.setOnDragExited(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent arg0) {
				thisCmdBox.drawCanvas(true);
			}
		});
		this.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					GameController.setSelectedSlot(thisCmdBox);
					success = true;
				}
				event.setDropCompleted(success);
				event.consume();
			}
		});
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (thisCmdBox.getCmdCardList().get(cmdCardList.size() - 1) != null) {
					GameController.getCardPane()
							.setShowingCard(thisCmdBox.getCmdCardList().get(cmdCardList.size() - 1));
				}
			}

		});
		this.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (GameController.getCardPane().getSelectedCard() != null) {
					GameController.getCardPane().setDmgCard(GameController.getCardPane().getSelectedCard());
				} else if (GameController.getSelectedCard() != 6) {
					GameController.getCardPane().setShowingCard(
							GameController.getDraftedCard().getDraftedCardList().get(GameController.getSelectedCard()));
				}
			}
		});
	}

	public void addCmdCard(CmdCard selectedCard) {
		if (this.cmdCardList.get(this.cmdCardList.size() - 1) == null) {
			this.cmdCardList.clear();
			this.cmdCardList.add(selectedCard);
		} else if (this.cmdCardList.get(this.cmdCardList.size() - 1).getCardType() != null
				&& this.cmdCardList.get(this.cmdCardList.size() - 1).getCardType().equals(selectedCard.getCardType())) {
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

	public void drawCanvas(boolean selected) {
		GraphicsContext cmdGC = cmdCanvas.getGraphicsContext2D();
		cmdGC.clearRect(0, 0, 123, 200);
		if (this.cmdCardList.get(this.cmdCardList.size() - 1) != null) {
			DrawUtil.drawCard(cmdGC, 4, 4, this.cmdCardList.get(this.cmdCardList.size() - 1).getSpriteValue());
		}
		if (selected) {
			DrawUtil.drawCard(cmdGC, 4, 4, CardSprite.SELECTED_CARD);
		}
		this.setGraphic(cmdCanvas);
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
	public void setProgrammedMech(Mech programmedMech) {
		this.programmedMech = programmedMech;
	}
	public Mech getProgrammedMech() {
		return programmedMech;
	}
}
