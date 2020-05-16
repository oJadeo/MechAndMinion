package gui;

import application.DrawUtil;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import logic.GameController;

public class PhasePane extends HBox {
	private Canvas phaseCanvas;
	
	public PhasePane() {
		super();
		phaseCanvas = new Canvas(900, 60);
		this.getChildren().add(phaseCanvas);
		this.drawPhase();
	}
	
	public void drawPhase() {
		GraphicsContext phaseGC = phaseCanvas.getGraphicsContext2D();
		phaseGC.clearRect(0, 0, 900, 60);
		switch (GameController.getCurrentPhase()) {
		case Program:
			DrawUtil.drawPhase(phaseGC, 75, 0, 8);
			break;
		case Execute:
			DrawUtil.drawPhase(phaseGC, 225, 0, 8);
			break;
		case MinionMove:
			DrawUtil.drawPhase(phaseGC, 375, 0, 8);
			break;
		case MinionAttack:
			DrawUtil.drawPhase(phaseGC, 525, 0, 8);
			break;
		case MinionSpawn:
			DrawUtil.drawPhase(phaseGC, 675, 0, 8);
			break;
		default:
			break;
		}
		for (int i = 0; i < 5; i++) {
			DrawUtil.drawPhase(phaseGC, 150 * i + 75, 0, i);
		}
	}
}
