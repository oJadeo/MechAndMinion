package tile;

import logic.Direction;
import logic.GameController;
import logic.Sprite;
import token.Minion;

public class SpawnTile extends Tile{

	public SpawnTile(int locationX, int locationY) {
		super(locationX, locationY);
		this.setSpriteValue(Sprite.SPAWN_TILE);
		
	}
	public void spawn() {
		Minion newMinion = new Minion(Direction.UP,this);
		GameController.getBoard().getMinionList().add(newMinion);
	}

}
