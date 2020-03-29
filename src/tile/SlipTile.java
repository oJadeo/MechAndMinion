package tile;

import logic.Sprite;

public class SlipTile extends Tile {

	public SlipTile(int locationX, int locationY) {
		super(locationX, locationY);
		this.setSpriteValue(Sprite.SLIP_TILE);
	}
}
