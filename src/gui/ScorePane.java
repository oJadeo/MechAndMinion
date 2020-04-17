package gui;

import application.DrawUtil;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import logic.GameController;

public class ScorePane extends HBox {
	private Canvas scoreCanvas;
	public ScorePane() {
		super();
		scoreCanvas = new Canvas(900,60);
		this.getChildren().add(scoreCanvas);
		this.drawScore();
	}
	public void drawScore() {
		GraphicsContext scoreGC = scoreCanvas.getGraphicsContext2D();
		scoreGC.clearRect(0, 0, 900, 60);
		DrawUtil.drawPhase(scoreGC, 105, 0, 5);
		int point = GameController.getScore();
		for (int i = 0; i < 3; i++) {
			DrawUtil.drawTile(scoreGC, 210 + 24 * (3 - i), 0, 23 + (point % 10));
			point = point / 10;
		}
	}
}
