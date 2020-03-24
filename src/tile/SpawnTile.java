package tile;

import logic.Sprite;

public class SpawnTile extends Tile{

	public SpawnTile(int locationX, int locationY) {
		super(locationX, locationY);
		this.setSpriteValue(Sprite.SPAWN_TILE);
		
	}
	public void spawn() {
		Minion newMinion = new Minion(this.getLocationX(),this.getLocationY());
		GameController.getBoard().getMinionList().add(newMinion);
	}

}
