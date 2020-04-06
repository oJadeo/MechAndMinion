package tile;

import logic.Direction;
import logic.GameController;
import logic.TileSprite;

public class MoveTile extends Tile {
	private Direction dir;

	public MoveTile(int locationX, int locationY) {
		super(locationX, locationY);
		double random = Math.random();
		if (random < 0.25) {
			dir = Direction.UP;
			this.setSpriteValue(TileSprite.MOVE_TILE_UP);
		} else if (random < 0.5) {
			dir = Direction.RIGHT;
			this.setSpriteValue(TileSprite.MOVE_TILE_RIGHT);
		} else if (random < 0.75) {
			dir = Direction.DOWN;
			this.setSpriteValue(TileSprite.MOVE_TILE_DOWN);
		} else {
			dir = Direction.LEFT;
			this.setSpriteValue(TileSprite.MOVE_TILE_LEFT);
		}
	}

	public void trigger() {
		GameController.move(token, this.dir);
	}
}
