package tile;

import java.util.ArrayList;

import logic.GameController;
import logic.TileSprite;
import token.Minion;

public class ExplosiveTile extends Tile {
	public ExplosiveTile(int locationX, int locationY) {
		super(locationX, locationY);
		this.setSpriteValue(TileSprite.EXPLOSIVE_TILE);
	}

	public void trigger() {
		ArrayList<Tile> selectedTile = GameController.getBoard().getBorder(this, 1);
		for (Tile tile : selectedTile) {
			if (tile.getToken() instanceof Minion) {
				tile.getToken().damaged();
			}
		}
	}
}
