package token;

import logic.Direction;
import logic.GameController;
import tile.Tile;

public class Minion extends Token {
	public Minion(Direction dir, Tile selfTile) {
		super(dir, selfTile);
	}

	public void damaged() {
		GameController.getBoard().getMinionList().remove(this);
		this.getSelfTile().setToken(null);
		GameController.addScore();
	}

	public void attack() {
		for (Tile e : GameController.getBoard().getBorder(getSelfTile(), 1)) {
			if (e.getToken() instanceof Mech) {
				e.getToken().damaged();
			}
		}
	}

}
