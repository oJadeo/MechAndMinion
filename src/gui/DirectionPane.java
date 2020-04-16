package gui;

import java.util.ArrayList;

import application.DrawUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import logic.Direction;
import logic.GameController;
import logic.Phase;
import logic.TileSprite;

public class DirectionPane extends HBox {
	ArrayList<Canvas> directionCanvas;
	ArrayList<Button> directionButton;

	public DirectionPane() {
		Canvas textCanvas = new Canvas(400, 48);
		GraphicsContext textGC = textCanvas.getGraphicsContext2D();
		DrawUtil.drawPhase(textGC, 250, 0, 7);
		this.getChildren().add(textCanvas);
		directionButton = new ArrayList<Button>();
		directionCanvas = new ArrayList<Canvas>();
		for (int i = 0; i < 4; i++) {
			directionButton.add(new Button());
			directionButton.get(i).setPrefSize(48, 48);
			directionButton.get(i).setPadding(new Insets(0));
			directionCanvas.add(new Canvas(48, 48));
			GraphicsContext directionGC = directionCanvas.get(i).getGraphicsContext2D();
			DrawUtil.drawTile(directionGC, 0, 0, 14 + i);
			directionButton.get(i).setGraphic(directionCanvas.get(i));
			switch (i) {
			case 0:
				directionButton.get(i).setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						GameController.getDirectionPane().drawDirection();
						if (GameController.getCurrentPhase() == Phase.Execute) {
							GameController.select(Direction.UP);
						} else {
							GameController.minionMove(Direction.UP);
						}
					}
				});
				break;
			case 1:
				directionButton.get(i).setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						GameController.getDirectionPane().drawDirection();
						if (GameController.getCurrentPhase() == Phase.Execute) {
							GameController.select(Direction.DOWN);
						} else {
							GameController.minionMove(Direction.DOWN);
						}
					}
				});
				break;
			case 2:
				directionButton.get(i).setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						GameController.getDirectionPane().drawDirection();
						if (GameController.getCurrentPhase() == Phase.Execute) {
							GameController.select(Direction.RIGHT);
						} else {
							GameController.minionMove(Direction.RIGHT);
						}
					}
				});
				break;
			case 3:
				directionButton.get(i).setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						GameController.getDirectionPane().drawDirection();
						if (GameController.getCurrentPhase() == Phase.Execute) {
							GameController.select(Direction.LEFT);
						} else {
							GameController.minionMove(Direction.LEFT);
						}
					}
				});
				break;
			default:
				break;
			}
			directionButton.get(i).setDisable(true);
		}
		this.getChildren().addAll(directionButton);
	}

	public void drawDirection() {
		for (int i = 0; i < 4; i++) {
			directionCanvas.set(i, new Canvas(48, 48));
			GraphicsContext directionGC = directionCanvas.get(i).getGraphicsContext2D();
			DrawUtil.drawTile(directionGC, 0, 0, 14 + i);
			directionButton.get(i).setGraphic(directionCanvas.get(i));
			directionButton.get(i).setDisable(true);
		}
	}

	public void drawSelectableDirection(Direction dir) {
		int selectedButton = 0;
		switch (dir) {
		case UP:
			selectedButton = 0;
			break;
		case DOWN:
			selectedButton = 1;
			break;
		case RIGHT:
			selectedButton = 2;
			break;
		case LEFT:
			selectedButton = 3;
			break;
		default:
			break;
		}
		directionButton.get(selectedButton).setDisable(false);
		GraphicsContext directionGC = directionCanvas.get(selectedButton).getGraphicsContext2D();
		DrawUtil.drawTile(directionGC, 0, 0, TileSprite.SELECTED_TILE);
	}
}
