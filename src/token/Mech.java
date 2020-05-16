package token;

import logic.*;
import card.base.CmdCard;
import damagecard.*;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import tile.Tile;

public class Mech extends Token {
	private CmdBoard cmdBoard;
	private int programedCount;
	private int attackedTimes;
	private int no;

	public Mech(Direction dir, Tile selfTile, int no) {
		super(dir, selfTile);
		this.cmdBoard = new CmdBoard(this);
		this.no = no;
		this.programedCount = 0;
		this.attackedTimes = 0;
		switch (no) {
		case 0:
			cmdBoard.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
			switch (dir) {
			case UP:
				this.setSpriteValue(TileSprite.MECH1_UP);
				break;
			case RIGHT:
				this.setSpriteValue(TileSprite.MECH1_RIGHT);
				break;
			case DOWN:
				this.setSpriteValue(TileSprite.MECH1_DOWN);
				break;
			case LEFT:
				this.setSpriteValue(TileSprite.MECH1_LEFT);
				break;
			default:
				break;
			}
			break;
		case 1:
			cmdBoard.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			switch (dir) {
			case UP:
				this.setSpriteValue(TileSprite.MECH2_UP);
				break;
			case RIGHT:
				this.setSpriteValue(TileSprite.MECH2_RIGHT);
				break;
			case DOWN:
				this.setSpriteValue(TileSprite.MECH2_DOWN);
				break;
			case LEFT:
				this.setSpriteValue(TileSprite.MECH2_LEFT);
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void damaged() {
		Media musicFile = new Media(ClassLoader.getSystemResource("hited.mp3").toString());
		MediaPlayer mediaPlayer = new MediaPlayer(musicFile);
		mediaPlayer.setAutoPlay(true);
		mediaPlayer.setVolume(0.08);
		
		attackedTimes -= 1;
		CmdCard damageCard = null ;
		switch ((int) (Math.random() * 11)) {
		case 0:
			damageCard = new BackMoveCard(this);
			break;
		case 1:
			damageCard = new ForwardMoveCard(this);
			break;
		case 2:
			damageCard = new LeftMoveCard(this);
			break;
		case 3:
			damageCard = new RightMoveCard(this);
			break;
		case 4:
			damageCard = new Rotate90Card(this);
			break;
		case 5:
			damageCard = new Rotate180Card(this);
			break;
		case 6:
			damageCard = new Rotate270Card(this);
			break;
		case 7:
			damageCard = new Swap12card(this);
			break;
		case 8:
			damageCard = new Swap34card(this);
			break;
		case 9:
			damageCard = new Swap56card(this);
			break;
		case 10:
			damageCard =  new Reversecard(this);
			break;
		default:
			break;
		}
		GameController.getCardPane().setDmgCard(damageCard);
		GameController.addDamgeCount();
	}

	public CmdBoard getCmdBoard() {
		return this.cmdBoard;
	}

	public void setCmdBoard(CmdBoard cmdBoard) {
		this.cmdBoard = cmdBoard;
	}
	
	public void setDirection(Direction dir) {
		super.setDirection(dir);
		switch (no) {
		case 0:
			switch (dir) {
			case UP:
				this.setSpriteValue(TileSprite.MECH1_UP);
				break;
			case RIGHT:
				this.setSpriteValue(TileSprite.MECH1_RIGHT);
				break;
			case DOWN:
				this.setSpriteValue(TileSprite.MECH1_DOWN);
				break;
			case LEFT:
				this.setSpriteValue(TileSprite.MECH1_LEFT);
				break;
			default:
				break;
			}
			break;
		case 1:
			switch (dir) {
			case UP:
				this.setSpriteValue(TileSprite.MECH2_UP);
				break;
			case RIGHT:
				this.setSpriteValue(TileSprite.MECH2_RIGHT);
				break;
			case DOWN:
				this.setSpriteValue(TileSprite.MECH2_DOWN);
				break;
			case LEFT:
				this.setSpriteValue(TileSprite.MECH2_LEFT);
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
		getSelfTile().draw();
	}
	public void setProgramedCount(int programedCount) {
		this.programedCount = programedCount;
	}
	public int getProgramedCount() {
		return programedCount;
	}
	public void setAttackedTimes(int attackedTimes) {
		this.attackedTimes = attackedTimes;
	}
	public int getAttackedTimes() {
		return attackedTimes;
	}
}
