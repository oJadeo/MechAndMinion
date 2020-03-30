package tile;

import logic.Sprite;
import logic.Direction;

public class SpinTile extends Tile {
	private int spinValue;

	public SpinTile(int locationX, int locationY) {
		super(locationX, locationY);
		double random = Math.random();
		if (random <= 1.0 / 3.0) {
			this.spinValue = 90;
			this.setSpriteValue(Sprite.SPIN_TILE_90);
		} else if (random <= 2.0 / 3.0) {
			this.spinValue = 180;
			this.setSpriteValue(Sprite.SPIN_TILE_180);
		} else {
			this.spinValue = 270;
			this.setSpriteValue(Sprite.SPIN_TILE_270);
		}
	}

	public void trigger() {
		switch (this.spinValue) {
		case 90:
			switch (token.getDirection()) {
			case LEFT:
				token.setDirection(Direction.UP);
				break;
			case RIGHT:
				token.setDirection(Direction.DOWN);
				break;
			case UP:
				token.setDirection(Direction.RIGHT);
				break;
			case DOWN:
				token.setDirection(Direction.LEFT);
				break;
			default:
				break;
			}
			break;
		case 180:
			switch (token.getDirection()) {
			case LEFT:
				token.setDirection(Direction.RIGHT);
				break;
			case RIGHT:
				token.setDirection(Direction.LEFT);
				break;
			case UP:
				token.setDirection(Direction.DOWN);
				break;
			case DOWN:
				token.setDirection(Direction.UP);
				break;
			default:
				break;
			}
			break;
		case 270:
			switch (token.getDirection()) {
			case LEFT:
				token.setDirection(Direction.DOWN);
				break;
			case RIGHT:
				token.setDirection(Direction.UP);
				break;
			case UP:
				token.setDirection(Direction.LEFT);
				break;
			case DOWN:
				token.setDirection(Direction.RIGHT);
				break;
			default:
				break;
			}
			break;
		}
	}
}
