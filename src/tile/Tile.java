package tile;

import application.DrawUtil;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import logic.GameController;
import logic.TileSprite;
import token.Token;

public class Tile extends Pane{

	private int locationX;
	private int locationY;
	protected Token token;
	private int spriteValue;
	private boolean selectable;
	private Canvas tileCanvas;

	public Tile(int locationX, int locationY) {
		super();
		this.locationX = locationX;
		this.locationY = locationY;
		this.selectable = false;
		this.setToken(null);
		this.spriteValue = TileSprite.NORMAL_TILE;
		this.setPrefSize(48, 48);
		this.tileCanvas = new Canvas(48,48);
		this.getChildren().add(tileCanvas);
		this.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				if(selectable) {
					GameController.select(this);
				}
			}
			
		});
	}
	
	public void draw() {
		GraphicsContext tileGC = tileCanvas.getGraphicsContext2D();
		DrawUtil.drawTile(tileGC, 0, 0, this.getSpriteValue());
		if(this.token != null) {
			DrawUtil.drawTile(tileGC, 0, 0, this.token.getSpriteValue());
		}
		if(selectable) {
			DrawUtil.drawTile(tileGC, 0, 0,TileSprite.SELECTED_TILE);
		}
	}
	
	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}

	public void setLocationY(int locationY) {
		this.locationY = locationY;
	}

	public Token getToken() {
		return this.token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public int getLocationX() {
		return this.locationX;
	}

	public int getLocationY() {
		return this.locationY;
	}

	public int getSpriteValue() {
		return this.spriteValue;
	}

	public void setSpriteValue(int spriteValue) {
		this.spriteValue = spriteValue;
	}
	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}
	public void trigger() {
	}
}
