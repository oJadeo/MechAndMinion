package gui;

import application.DrawUtil;
import card.base.CmdCard;
import card.base.Instant;
import card.base.OnGoing;
import cmdcard.*;
import damagecard.BackMoveCard;
import damagecard.ForwardMoveCard;
import damagecard.LeftMoveCard;
import damagecard.Reversecard;
import damagecard.RightMoveCard;
import damagecard.Rotate180Card;
import damagecard.Rotate270Card;
import damagecard.Rotate90Card;
import damagecard.Swap12card;
import damagecard.Swap34card;
import damagecard.Swap56card;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class CardPane extends VBox {
	private CmdCard selectedCard;
	private Label textLabel;
	private Canvas cardCanvas;
	private Label descriptionLabel;
	private Button executeButton;

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
		descriptionPane.getChildren().add(descriptionLabel);
		this.getChildren().add(descriptionPane);

		executeButton = new Button();
		executeButton.setPrefSize(420, 80);
		executeButton.setText("Trigger");
		executeButton.setFont(new Font(30));
		executeButton.setDisable(true);
		this.getChildren().add(executeButton);
	}

	public void setDmgCard(CmdCard damageCard) {
		executeButton.setDisable(false);
		selectedCard = damageCard;
		setTextLabel(damageCard);
		setImage(damageCard);
		if (damageCard instanceof OnGoing) {
			String description = "";
			if (damageCard instanceof BackMoveCard) {
				description = "when execute: move back 1 step \n";
			} else if (damageCard instanceof ForwardMoveCard) {
				description = "when execute: move back 1 step \n";
			} else if (damageCard instanceof LeftMoveCard) {
				description = "when execute: move back 1 step \n";
			} else if (damageCard instanceof RightMoveCard) {
				description = "when execute: move back 1 step \n";
			} else if (damageCard instanceof Rotate90Card) {
				description = "when execute: move back 1 step \n";
			} else if (damageCard instanceof Rotate180Card) {
				description = "when execute: move back 1 step \n";
			} else if (damageCard instanceof Rotate270Card) {
				description = "when execute: move back 1 step \n";
			}
			int slot = (int) (Math.random() * 6);
			description += "when trigger: this will be added to " + slot + " slot of ";
			if (damageCard.getProgrammedMech().equals(GameController.getRedMech())) {
				description += "RedMech";
			} else {
				description += "BlueMech";
			}
			descriptionLabel.setText(description);
			executeButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					damageCard.getProgrammedMech().getCmdBoard().getCmdBox(slot).addCmdCard(damageCard);
					damageCard.getProgrammedMech().getCmdBoard().draw();
					selectedCard = null;
					executeButton.setDisable(true);
				}
			});

		} else if (damageCard instanceof Instant) {
			setDescription(damageCard);
			executeButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					((Instant) damageCard).trigger();
					damageCard.getProgrammedMech().getCmdBoard().draw();
					selectedCard = null;
					executeButton.setDisable(true);
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
			textLabel.setText("BackMoveCard");
		} else if (showingCard instanceof ForwardMoveCard) {
			textLabel.setText("ForwardMoveCard");
		} else if (showingCard instanceof LeftMoveCard) {
			textLabel.setText("LeftMoveCard");
		} else if (showingCard instanceof RightMoveCard) {
			textLabel.setText("RightMoveCard");
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
		}
	}

	public void setImage(CmdCard showingCard) {
		GraphicsContext cardGC = cardCanvas.getGraphicsContext2D();
		cardGC.restore();
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
		// TODO description for everyCard
	}
	public CmdCard getSelectedCard() {
		return selectedCard;
	}
}
