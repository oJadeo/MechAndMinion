package tile;

import java.util.ArrayList;

import logic.GameController;
import logic.Sprite;
import token.Minion;

public class ExplosiveTile extends Tile {
	public ExplosiveTile(int locationX, int locationY) {
		super(locationX, locationY);
		this.setSpriteValue(Sprite.EXPLOSIVE_TILE);
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
