package application;

import logic.Direction;
import logic.GameController;
import tile.*;
import token.Minion;

public class Test {
	public static void main(String[] args) {
		GameController.initializeGame();
		GameController.getBoard().setTile(0, 1, new SlipTile(0, 1));
		new Minion(Direction.UP, GameController.getBoard().getTile(1, 0));
		new Minion(Direction.UP, GameController.getBoard().getTile(1, 1));
		new Minion(Direction.UP, GameController.getBoard().getTile(1, 2));
		GameController.move(GameController.redMech,Direction.DOWN);
		GameController.update();
	}
}
