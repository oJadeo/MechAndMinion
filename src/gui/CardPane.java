package gui;

import application.DrawUtil;
import card.base.CmdCard;
import card.base.Instant;
import card.base.OnGoing;
import cmdcard.*;
import damagecard.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.CardSprite;
import logic.GameController;
import logic.Phase;

public class CardPane extends VBox {
	private CmdCard selectedCard;
	private Label textLabel;
	private Canvas cardCanvas;
	private Label descriptionLabel;
	private Button triggerButton;

	public CardPane() {
		super();
		this.setPrefSize(500, 1080);
		this.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setAlignment(Pos.CENTER);

		StackPane textPane = new StackPane();
		textPane.setPrefSize(460, 100);
		textPane.setPadding(new Insets(20));
		textPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(15),
				new BorderWidths(7), new Insets(20))));
		textLabel = new Label();
		textLabel.setFont(new Font(50));
		textPane.getChildren().add(textLabel);
		this.getChildren().add(textPane);

		cardCanvas = new Canvas(345, 200);
		this.getChildren().add(cardCanvas);

		Pane descriptionPane = new Pane();
		descriptionPane.setPrefSize(460, 600);
		descriptionPane.setPadding(new Insets(20));
		descriptionPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(15),
				new BorderWidths(7), new Insets(20))));
		descriptionLabel = new Label();
		descriptionLabel.setFont(new Font(30));
		descriptionLabel.relocate(40, 40);
		descriptionLabel.setMaxWidth(420);
		descriptionLabel.setWrapText(true);
		descriptionPane.getChildren().add(descriptionLabel);
		this.getChildren().add(descriptionPane);

		triggerButton = new Button();
		triggerButton.setPrefSize(420, 80);
		triggerButton.setText("Trigger");
		triggerButton.setFont(new Font(30));
		triggerButton.setDisable(true);
		this.getChildren().add(triggerButton);
		
	}

	public void setDmgCard(CmdCard damageCard) {
		triggerButton.setDisable(false);
		selectedCard = damageCard;
		setTextLabel(damageCard);
		setImage(damageCard);
		if (damageCard instanceof OnGoing) {
			String description = "";
			if (damageCard instanceof BackMoveCard) {
				description = "When execute: move South 1 step\n";
			} else if (damageCard instanceof ForwardMoveCard) {
				description = "When execute: move North 1 step\n";
			} else if (damageCard instanceof LeftMoveCard) {
				description = "When execute: move West 1 step\n";
			} else if (damageCard instanceof RightMoveCard) {
				description = "When execute: move East 1 step\n";
			} else if (damageCard instanceof Rotate90Card) {
				description = "When execute: rotate 90 degrees clockwise\n";
			} else if (damageCard instanceof Rotate180Card) {
				description = "When execute: rotate 180 degrees clockwise\n";
			} else if (damageCard instanceof Rotate270Card) {
				description = "When execute: rotate 270 degrees clockwise\n";
			}
			int slot = (int) (Math.random() * 6);
			description += "\nWhen trigger: this will be added to slot " + (slot + 1) + " of ";
			if (damageCard.getProgrammedMech().equals(GameController.getRedMech())) {
				description += "RedMech";
			} else {
				description += "BlueMech";
			}
			descriptionLabel.setText(description);
			triggerButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					damageCard.getProgrammedMech().getCmdBoard().setDisableSlot(false);
					damageCard.getProgrammedMech().getCmdBoard().getCmdBox(slot).addCmdCard(damageCard);
					damageCard.getProgrammedMech().getCmdBoard().draw();
					//damageCard.getProgrammedMech().getCmdBoard().setDisableSlot(true);
					selectedCard = null;
					setShowingCard(selectedCard);
					if (GameController.getCurrentPhase() == Phase.MinionAttack) {
						if (GameController.getRedMech().getAttackedTimes() != 0) {
							GameController.getRedMech().getSelfTile().setSelectable(true);
							GameController.getRedMech().getSelfTile().setSelectToken(true);
							GameController.getBoard().drawGameBoard();
						}
						if (GameController.getBlueMech().getAttackedTimes() != 0) {
							GameController.getBlueMech().getSelfTile().setSelectable(true);
							GameController.getBlueMech().getSelfTile().setSelectToken(true);
							GameController.getBoard().drawGameBoard();
						}
						if(GameController.getBlueMech().getAttackedTimes() == 0 && GameController.getRedMech().getAttackedTimes() == 0) {
							GameController.nextPhase();
						}
					}else if(GameController.getCurrentPhase()== Phase.Execute) {
						GameController.setProgramCount(GameController.getProgramCount()+1);
						GameController.execute(GameController.getProgramCount());
					}
					triggerButton.setDisable(true);
				}
			});

		} else if (damageCard instanceof Instant) {
			setDescription(damageCard);
			triggerButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					damageCard.getProgrammedMech().getCmdBoard().setDisableSlot(false);
					((Instant) damageCard).trigger();
					damageCard.getProgrammedMech().getCmdBoard().draw();
					selectedCard = null;
					setShowingCard(selectedCard);
					//damageCard.getProgrammedMech().getCmdBoard().setDisableSlot(true);
					if (GameController.getCurrentPhase() == Phase.MinionAttack) {
						if (GameController.getRedMech().getAttackedTimes() != 0) {
							GameController.getRedMech().getSelfTile().setSelectable(true);
							GameController.getRedMech().getSelfTile().setSelectToken(true);
							GameController.getBoard().drawGameBoard();
						}
						if (GameController.getBlueMech().getAttackedTimes() != 0) {
							GameController.getBlueMech().getSelfTile().setSelectable(true);
							GameController.getBlueMech().getSelfTile().setSelectToken(true);
							GameController.getBoard().drawGameBoard();
						}
						if(GameController.getBlueMech().getAttackedTimes() == 0 && GameController.getRedMech().getAttackedTimes() == 0) {
							GameController.nextPhase();
						}
					}else if(GameController.getCurrentPhase()== Phase.Execute) {
						GameController.setProgramCount(GameController.getProgramCount()+1);
						GameController.execute(GameController.getProgramCount());
					}
					triggerButton.setDisable(true);
				}
			});
		}
	}

	public void setShowingCard(CmdCard showingCard) {
		setTextLabel(showingCard);
		setImage(showingCard);
		setDescription(showingCard);
	}

	public void setTextLabel(CmdCard showingCard) {
		if (showingCard instanceof BlueAttackCard) {
			textLabel.setText("BlueAttackCard");
		} else if (showingCard instanceof BlueMoveCard) {
			textLabel.setText("BlueMoveCard");
		} else if (showingCard instanceof BlueRotateCard) {
			textLabel.setText("BlueRotateCard");
		} else if (showingCard instanceof GreenAttackCard) {
			textLabel.setText("GreenAttackCard");
		} else if (showingCard instanceof GreenMoveCard) {
			textLabel.setText("GreenMoveCard");
		} else if (showingCard instanceof GreenRotateCard) {
			textLabel.setText("GreenRotateCard");
		} else if (showingCard instanceof RedAttackCard) {
			textLabel.setText("RedAttackCard");
		} else if (showingCard instanceof RedMoveCard) {
			textLabel.setText("RedMoveCard");
		} else if (showingCard instanceof RedRotateCard) {
			textLabel.setText("RedRotateCard");
		} else if (showingCard instanceof YellowAttackCard) {
			textLabel.setText("YellowAttackCard");
		} else if (showingCard instanceof YellowMoveCard) {
			textLabel.setText("YellowMoveCard");
		} else if (showingCard instanceof YellowRotateCard) {
			textLabel.setText("YellowRotateCard");
		} else if (showingCard instanceof BackMoveCard) {
			textLabel.setText("MoveSouthCard");
		} else if (showingCard instanceof ForwardMoveCard) {
			textLabel.setText("MoveNorthCard");
		} else if (showingCard instanceof LeftMoveCard) {
			textLabel.setText("MoveWestCard");
		} else if (showingCard instanceof RightMoveCard) {
			textLabel.setText("MoveEastCard");
		} else if (showingCard instanceof Rotate90Card) {
			textLabel.setText("Rotate90Card");
		} else if (showingCard instanceof Rotate180Card) {
			textLabel.setText("Rotate180Card");
		} else if (showingCard instanceof Rotate270Card) {
			textLabel.setText("Rotate270Card");
		} else if (showingCard instanceof Swap12card) {
			textLabel.setText("Swap12card");
		} else if (showingCard instanceof Swap34card) {
			textLabel.setText("Swap34card");
		} else if (showingCard instanceof Swap56card) {
			textLabel.setText("Swap56card");
		} else if (showingCard instanceof Reversecard) {
			textLabel.setText("ReverseCard");
		}else {
			textLabel.setText("");
		}

	}

	public void setImage(CmdCard showingCard) {
		GraphicsContext cardGC = cardCanvas.getGraphicsContext2D();
		cardGC.clearRect(0, 0, 345, 192);
		if (showingCard instanceof BlueAttackCard) {
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.BLUE_ATTACK_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.BLUE_ATTACK_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.BLUE_ATTACK_CARD_3);
		} else if (showingCard instanceof BlueMoveCard) {
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.BLUE_MOVE_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.BLUE_MOVE_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.BLUE_MOVE_CARD_3);
		} else if (showingCard instanceof BlueRotateCard) {
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.BLUE_ROTATE_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.BLUE_ROTATE_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.BLUE_ROTATE_CARD_3);
		} else if (showingCard instanceof GreenAttackCard) {
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.GREEN_ATTACK_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.GREEN_ATTACK_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.GREEN_ATTACK_CARD_3);
		} else if (showingCard instanceof GreenMoveCard) {
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.GREEN_MOVE_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.GREEN_MOVE_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.GREEN_MOVE_CARD_3);
		} else if (showingCard instanceof GreenRotateCard) {
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.GREEN_ROTATE_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.GREEN_ROTATE_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.GREEN_ROTATE_CARD_3);
		} else if (showingCard instanceof RedAttackCard) {
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.RED_ATTACK_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.RED_ATTACK_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.RED_ATTACK_CARD_3);
		} else if (showingCard instanceof RedMoveCard) {
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.RED_MOVE_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.RED_MOVE_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.RED_MOVE_CARD_3);
		} else if (showingCard instanceof RedRotateCard) {
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.RED_ROTATE_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.RED_ROTATE_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.RED_ROTATE_CARD_3);
		} else if (showingCard instanceof YellowAttackCard) {
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.YELLOW_ATTACK_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.YELLOW_ATTACK_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.YELLOW_ATTACK_CARD_3);
		} else if (showingCard instanceof YellowMoveCard) {
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.YELLOW_MOVE_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.YELLOW_MOVE_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.YELLOW_MOVE_CARD_3);
		} else if (showingCard instanceof YellowRotateCard) {
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.YELLOW_ROTATE_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.YELLOW_ROTATE_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.YELLOW_ROTATE_CARD_3);
		} else if (showingCard instanceof BackMoveCard) {
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.BACK_MOVE);
		} else if (showingCard instanceof ForwardMoveCard) {
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.FORWARD_MOVE);
		} else if (showingCard instanceof LeftMoveCard) {
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.LEFT_MOVE);
		} else if (showingCard instanceof RightMoveCard) {
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.RIGHT_MOVE);
		} else if (showingCard instanceof Rotate90Card) {
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.TURN_RIGHT);
		} else if (showingCard instanceof Rotate180Card) {
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.TURN_AROUND);
		} else if (showingCard instanceof Rotate270Card) {
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.TURN_LEFT);
		} else if (showingCard instanceof Swap12card) {
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.DAMAGE_SWAP12);
		} else if (showingCard instanceof Swap34card) {
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.DAMAGE_SWAP34);
		} else if (showingCard instanceof Swap56card) {
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.DAMAGE_SWAP56);
		} else if (showingCard instanceof Reversecard) {
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.DAMAGE_REVERSE);
		}
	}

	public void setDescription(CmdCard showingCard) {
		if (showingCard instanceof BlueAttackCard) {
			descriptionLabel.setText("1:Damage 1 target in same line at any range\n"
					+ "2:Damage 2 target in same line at any range\n" + "3:Damage 3 target in same line at any range\n"
					+ "\nDamage first unique target(Minion or Mech)");
		} else if (showingCard instanceof BlueMoveCard) {
			descriptionLabel.setText("1:Move forward 1 steps\n" + "2:Move forward 2 steps\n"
					+ "3:Move forward 3 steps\n" + "\nDamage any target in the ways");
		} else if (showingCard instanceof BlueRotateCard) {
			descriptionLabel.setText("1:Rotate 90 or 270 degrees and damgage 1 target within 1 range\n"
					+ "2:Rotate 90, 180 or 270 degrees and damgage 2 target within 1 range\n"
					+ "3:Rotate to any directions and damgage 3 target within 1 range\n");
		} else if (showingCard instanceof GreenAttackCard) {
			descriptionLabel.setText("1:Damage 1 target within 1 range\n" + "2:Damage 1 target within 2 range\n"
					+ "3:Damage 1 target within 3 range\n");
		} else if (showingCard instanceof GreenMoveCard) {
			descriptionLabel.setText("1:Move 1 space forward, left or right\n"
					+ "2:Move 2 space forward, left or right\n" + "3:Move 3 space forward, left or right\n");
		} else if (showingCard instanceof GreenRotateCard) {
			descriptionLabel.setText("1:Rotate 90 or 270 degrees and damgage 1 target within 1 range\n"
					+ "2:Rotate 90, 180 or 270 degrees and damgage 1 target within 2 range\n"
					+ "3:Rotate to any directions and damgage 1 target within 3 range\n");
		} else if (showingCard instanceof RedAttackCard) {
			descriptionLabel.setText("1:Damage all target within area of effect\n"
					+ "2:Damage all target within area of effect\n" + "3:Damage all target within area of effect\n");
		} else if (showingCard instanceof RedMoveCard) {
			descriptionLabel.setText("1:Move forward 1 steps and damage 2 target(left&Right)\n"
					+ "2:Move forward 2 steps and damage 2 target(left&Right)\n"
					+ "3:Move forward 3 steps and damage 2 target(left&Right)\n");
		} else if (showingCard instanceof RedRotateCard) {
			descriptionLabel.setText("1:Rotate 90 or 270 degrees and damgage all target within area of effect\n"
					+ "2:Rotate 90, 180 or 270 degrees and damgage all target within area of effect\n"
					+ "3:Rotate to any directions and damgage all target within area of effect\n");
		} else if (showingCard instanceof YellowAttackCard) {
			descriptionLabel.setText("1:Damage 1 target and chain 1 times\n" + "2:Damage 1 target and chain 3 times\n"
					+ "3:Damage 1 target and chain 5 times\n");
		} else if (showingCard instanceof YellowMoveCard) {
			descriptionLabel.setText(
					"1:Move forward 1 - 2 steps\n" + "2:Move forward 2 - 4 steps\n" + "3:Move forward 3 - 6 steps\n");
		} else if (showingCard instanceof YellowRotateCard) {
			descriptionLabel.setText("1:Rotate 90 or 270 degrees and damgage 1 target within 1 range\n"
					+ "2:Rotate 90, 180 or 270 degrees and damgage 1 target within 2 range\n"
					+ "3:Rotate to any directions and damgage 1 target within 3 range\n");
		} else if (showingCard instanceof BackMoveCard) {
			descriptionLabel.setText("Move South 1 step\n");
		} else if (showingCard instanceof ForwardMoveCard) {
			descriptionLabel.setText("Move North 1 step\n");
		} else if (showingCard instanceof LeftMoveCard) {
			descriptionLabel.setText("Move West 1 step\n");
		} else if (showingCard instanceof RightMoveCard) {
			descriptionLabel.setText("Move East 1 step\n");
		} else if (showingCard instanceof Rotate90Card) {
			descriptionLabel.setText("Rotate 90 degrees clockwise\n");
		} else if (showingCard instanceof Rotate180Card) {
			descriptionLabel.setText("Rotate 180 degrees clockwise\n");
		} else if (showingCard instanceof Rotate270Card) {
			descriptionLabel.setText("Rotate 270 degrees clockwise\n");
		} else if (showingCard instanceof Swap12card) {
			String description = "Swap Command Card in slot 1 and 2 of ";
			if (showingCard.getProgrammedMech().equals(GameController.getRedMech())) {
				description += "RedMech";
			} else {
				description += "BlueMech";
			}
			descriptionLabel.setText(description);
		} else if (showingCard instanceof Swap34card) {
			String description = "Swap Command Cards in slot 3 and 4 of ";
			if (showingCard.getProgrammedMech().equals(GameController.getRedMech())) {
				description += "RedMech";
			} else {
				description += "BlueMech";
			}
			descriptionLabel.setText(description);
		} else if (showingCard instanceof Swap56card) {
			String description = "Swap Command Cards in slot 5 and 6 of ";
			if (showingCard.getProgrammedMech().equals(GameController.getRedMech())) {
				description += "RedMech";
			} else {
				description += "BlueMech";
			}
			descriptionLabel.setText(description);
		} else if (showingCard instanceof Reversecard) {
			String description = "Swap Command Cards in \nslot 1 and 6,\nslot 2 and 5, \nslot 3 and 4 \nof ";
			if (showingCard.getProgrammedMech().equals(GameController.getRedMech())) {
				description += "RedMech";
			} else {
				description += "BlueMech";
			}
			descriptionLabel.setText(description);
		} else {
			descriptionLabel.setText("");
		}
	}
	public CmdCard getSelectedCard() {
		return selectedCard;
	}
}
