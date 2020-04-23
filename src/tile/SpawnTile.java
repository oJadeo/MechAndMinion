package tile;

import javafx.event.Event;
import javafx.event.EventHandler;
import logic.Direction;
import logic.GameController;
import logic.Phase;
import logic.TileSprite;
import token.Mech;
import token.Minion;

public class SpawnTile extends Tile {

	public SpawnTile(int locationX, int locationY) {
		super(locationX, locationY);
		this.setSpriteValue(TileSprite.SPAWN_TILE);
		GameController.getBoard().getSpawnTileList().add(this);
		this.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (selectable) {
					if (GameController.getCurrentPhase() == Phase.Execute) {
						if (selectable) {
							if (selectToken) {
								GameController.select(token);
							} else {
								GameController.select(GameController.getBoard().getTile(locationX, locationY));
							}
						}
					} else if (GameController.getCurrentPhase() == Phase.MinionSpawn) {
						((SpawnTile) GameController.getBoard().getTile(locationX, locationY)).setSelectable(false);
						((SpawnTile) GameController.getBoard().getTile(locationX, locationY)).spawn();
					} else if (GameController.getCurrentPhase() == Phase.MinionAttack) {
						if (selectable && selectToken) {
							((Mech) token).damaged();
							GameController.getRedMech().getSelfTile().setSelectable(false);
							GameController.getRedMech().getSelfTile().setSelectToken(false);
							GameController.getBlueMech().getSelfTile().setSelectable(false);
							GameController.getBlueMech().getSelfTile().setSelectToken(false);
							if (GameController.getBlueMech().getAttackedTimes() == 0
									&& GameController.getRedMech().getAttackedTimes() == 0) {
								GameController.nextPhase();
							}
							GameController.getBoard().drawGameBoard();
						}
					}
				}
			}
		});
	}

	public void spawn() {
		GameController.setSelectTimes(GameController.getSelectTimes() - 1);
		if (this.getToken() == null) {
			new Minion(Direction.UP, this);
		}
		GameController.getBoard().drawGameBoard();
		if (GameController.getSelectTimes() == 0) {
			GameController.nextPhase();
		}
	}
}
