package gui;

import application.DrawUtil;
import card.base.CmdCard;
import cmdcard.*;
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

public class CardPane extends VBox {
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
		textLabel = new Label("CardName");
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
		descriptionLabel = new Label("CardDescription");
		descriptionLabel.setFont(new Font(30));
		descriptionLabel.relocate(40, 40);
		descriptionPane.getChildren().add(descriptionLabel);
		this.getChildren().add(descriptionPane);
		
		executeButton = new Button();
		executeButton.setPrefSize(420, 80);
		this.getChildren().add(executeButton);
	}
	public void setDmgCard(CmdCard damgeCard) {
		//TODO for trigger damageCard
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
			textLabel.setText("BlueRotateCard");
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.BLUE_ROTATE_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.BLUE_ROTATE_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.BLUE_ROTATE_CARD_3);
		} else if (showingCard instanceof GreenAttackCard) {
			textLabel.setText("GreenAttackCard");
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.GREEN_ATTACK_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.GREEN_ATTACK_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.GREEN_ATTACK_CARD_3);
		} else if (showingCard instanceof GreenMoveCard) {
			textLabel.setText("GreenMoveCard");
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.GREEN_MOVE_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.GREEN_MOVE_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.GREEN_MOVE_CARD_3);
		} else if (showingCard instanceof GreenRotateCard) {
			textLabel.setText("GreenRotateCard");
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.GREEN_ROTATE_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.GREEN_ROTATE_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.GREEN_ROTATE_CARD_3);
		} else if (showingCard instanceof RedAttackCard) {
			textLabel.setText("RedAttackCard");
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.RED_ATTACK_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.RED_ATTACK_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.RED_ATTACK_CARD_3);
		} else if (showingCard instanceof RedMoveCard) {
			textLabel.setText("RedMoveCard");
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.RED_MOVE_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.RED_MOVE_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.RED_MOVE_CARD_3);
		} else if (showingCard instanceof RedRotateCard) {
			textLabel.setText("RedRotateCard");
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.RED_ROTATE_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.RED_ROTATE_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.RED_ROTATE_CARD_3);
		} else if (showingCard instanceof YellowAttackCard) {
			textLabel.setText("YellowAttackCard");
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.YELLOW_ATTACK_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.YELLOW_ATTACK_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.YELLOW_ATTACK_CARD_3);
		} else if (showingCard instanceof YellowMoveCard) {
			textLabel.setText("YellowMoveCard");
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.YELLOW_MOVE_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.YELLOW_MOVE_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.YELLOW_MOVE_CARD_3);
		} else if (showingCard instanceof YellowRotateCard) {
			textLabel.setText("YellowRotateCard");
			DrawUtil.drawCard(cardGC, 0, 0, CardSprite.YELLOW_ROTATE_CARD_1);
			DrawUtil.drawCard(cardGC, 115, 0, CardSprite.YELLOW_ROTATE_CARD_2);
			DrawUtil.drawCard(cardGC, 230, 0, CardSprite.YELLOW_ROTATE_CARD_3);
		}
	}

	public void setDescription(CmdCard showingCard) {
		//TODO description for everyCard
	}
}
