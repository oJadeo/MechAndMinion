package tile;

import logic.Direction;
import logic.GameController;
import logic.TileSprite;
import token.Minion;

public class SpawnTile extends Tile {

	public SpawnTile(int locationX, int locationY) {
		super(locationX, locationY);
		this.setSpriteValue(TileSprite.SPAWN_TILE);
		GameController.getBoard().getSpawnTileList().add(this);
	}

	public void spawn() {
		if(this.getToken()==null) {
			new Minion(Direction.UP, this);
		}
	}
}
