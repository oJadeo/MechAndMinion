package gui;

import application.DrawUtil;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import logic.GameController;
import logic.TileSprite;

public class HealthPane extends HBox {
	Canvas healthCanvas;
	public HealthPane() {
		super();
		healthCanvas = new Canvas(900, 60);
		this.getChildren().add(healthCanvas);
		this.drawHealth();
	}
	public void drawHealth() {
		GraphicsContext healthGC = healthCanvas.getGraphicsContext2D();
		healthGC.clearRect(0, 0, 900, 60);
		DrawUtil.drawPhase(healthGC, 100, 0, 6);
		for (int i = 0; i < GameController.getDamageCount(); i++) {
			DrawUtil.drawTile(healthGC, 250 + 48 * i, 0, TileSprite.LOSE_HEALTH);
		}
		for (int i = 0; i < GameController.getHealth(); i++) {
			DrawUtil.drawTile(healthGC, 250 + 48 * (i + GameController.getDamageCount()), 0, TileSprite.REMAIN_HEALTH);
		}
	}
}
