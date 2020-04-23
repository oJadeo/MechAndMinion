package token;

import java.util.ArrayList;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import logic.Direction;
import logic.GameController;
import tile.Tile;

public class Minion extends Token {
	private boolean move;

	public Minion(Direction dir, Tile selfTile) {
		super(dir, selfTile);
		GameController.getBoard().getMinionList().add(this);
	}

	@Override
	public void damaged() {
		Media musicFile = new Media(ClassLoader.getSystemResource("hit.mp3").toString());
		MediaPlayer mediaPlayer = new MediaPlayer(musicFile);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setVolume(0.08);
		GameController.getBoard().getMinionList().remove(this);
		this.getSelfTile().setToken(null);
		GameController.addScore();
	}

	public ArrayList<Token> attack() {
		ArrayList<Token> resultList = new ArrayList<Token>();
		for (Tile e : GameController.getBoard().getBorder(getSelfTile(), 1)) {
			if (e.getToken() instanceof Mech) {
				resultList.add(e.getToken());
				((Mech) e.getToken()).setAttackedTimes(((Mech) e.getToken()).getAttackedTimes() + 1);
			}
		}
		return resultList;
	}

	public void setMove(boolean move) {
		this.move = move;
	}

	public boolean getMove() {
		return move;
	}

}
