package tile;

import logic.Sprite;
import token.Token;

public class Tile {
	public static final int DIMENSIONX = 48;
	public static final int DIMENSIONY = 48;
	private int locationX;
	private int locationY;
	protected Token token;
	private int spriteValue;

	public Tile(int locationX, int locationY) {
		this.locationX = locationX;
		this.locationY = locationY;
		this.setToken(null);
		this.spriteValue = Sprite.NORMAL_TILE;
	}

	public void update() {

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

	public void trigger() {
	}
}
