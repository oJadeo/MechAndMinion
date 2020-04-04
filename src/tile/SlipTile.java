package tile;

import logic.TileSprite;

public class SlipTile extends Tile {

	public SlipTile(int locationX, int locationY) {
		super(locationX, locationY);
		this.setSpriteValue(TileSprite.SLIP_TILE);
	}
}
