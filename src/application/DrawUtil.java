package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class DrawUtil {
	private static String TilePath = ClassLoader.getSystemResource("TileSprite.png").toString();
	private static Image TileSprite = new Image(TilePath);
	
	private static String CardPath = ClassLoader.getSystemResource("CardSprite.png").toString();
	private static Image CardSprite = new Image(CardPath);
	
	private static String PhasePath = ClassLoader.getSystemResource("PhaseSprite.png").toString();
	private static Image PhaseSprite = new Image(PhasePath);
	
	
	public static void drawTile(GraphicsContext gc,int x,int y,int index) {
		WritableImage img = new WritableImage(TileSprite.getPixelReader(),index*48,0,48,48);
		gc.drawImage(img, x, y);
	}
	public static void drawCard(GraphicsContext gc,int x,int y,int index) {
		WritableImage img = new WritableImage(CardSprite.getPixelReader(),index*115,0,115,192);
		gc.drawImage(img, x, y);
	}
	public static void drawPhase(GraphicsContext gc,int x,int y,int index) {
		WritableImage img = new WritableImage(PhaseSprite.getPixelReader(),index*150,0,150,60);
		gc.drawImage(img, x, y);
	}
	
}
