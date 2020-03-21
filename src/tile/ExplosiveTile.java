package tile;

import java.util.ArrayList;

import logic.Sprite;

public class ExplosiveTile extends Tile{
	public ExplosiveTile(int locationX,int locationY) {
		super(locationX,locationY);
		this.setSpriteValue(Sprite.EXPLOSIVE_TILE);
	}
	public void trigger(Token token) {
		ArrayList<Tile> selectedTile = GameController.getBoard().getBorder(token.getSelfTile(),1);
	}
}
