package token;

import java.util.ArrayList;

import logic.Direction;
import logic.GameController;
import tile.Tile;

public class Minion extends Token {
	public Minion(Direction dir, Tile selfTile) {
		super(dir, selfTile);
		GameController.getBoard().getMinionList().add(this);
	}
	
	@Override
	public void damaged() {
		GameController.getBoard().getMinionList().remove(this);
		this.getSelfTile().setToken(null);
		GameController.addScore();
	}

	public ArrayList<Token> attack() {
		ArrayList<Token> resultList = new ArrayList<Token>();
		for (Tile e : GameController.getBoard().getBorder(getSelfTile(), 1)) {
			if (e.getToken() instanceof Mech) {
				resultList.add(e.getToken());
				((Mech) e.getToken()).setAttackedTimes(((Mech) e.getToken()).getAttackedTimes()+1);
			}
		}
		return resultList;
	}


}
