package tile;

import logic.Sprite;

public class SlipTile extends MoveTile{

	public SlipTile(int locationX, int locationY) {
		super(locationX, locationY);
		this.setSpriteValue(Sprite.SLIP_TILE);
	}
	public void trigger(Token token) {
		GameController.move(token,token.getDirection());
}
