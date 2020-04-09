package token;

import logic.Direction;
import tile.Tile;

public abstract class Token {
	private Direction dir;
	private Tile selfTile;
	protected int spriteValue;

	public Token(Direction dir, Tile selfTile) {
		this.setSelfTile(selfTile);
		this.setDirection(dir);
		selfTile.setToken(this);
	}

	public abstract void damaged();

	public Direction getDirection() {
		return this.dir;
	}

	public Tile getSelfTile() {
		return this.selfTile;
	}

	public int getSpriteValue() {
		return this.spriteValue;
	}

	public void setDirection(Direction dir) {
		this.dir = dir;
	}

	public void setSelfTile(Tile selftile) {
		this.selfTile = selftile;
	}

	public void setSpriteValue(int spriteValue) {
		this.spriteValue = spriteValue;
	}

}
