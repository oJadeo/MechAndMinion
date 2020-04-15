package token;

import logic.*;
import damagecard.*;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
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
				this.spriteValue = TileSprite.MECH1_UP;
				break;
			case RIGHT:
				this.spriteValue = TileSprite.MECH1_RIGHT;
				break;
			case DOWN:
				this.spriteValue = TileSprite.MECH1_DOWN;
				break;
			case LEFT:
				this.spriteValue = TileSprite.MECH1_LEFT;
				break;
			default:
				break;
			}
			break;
		case 1:
			cmdBoard.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
			switch (dir) {
			case UP:
				this.spriteValue = TileSprite.MECH2_UP;
				break;
			case RIGHT:
				this.spriteValue = TileSprite.MECH2_RIGHT;
				break;
			case DOWN:
				this.spriteValue = TileSprite.MECH2_DOWN;
				break;
			case LEFT:
				this.spriteValue = TileSprite.MECH2_LEFT;
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
		//Make it show
		attackedTimes -= 1;
		int slot = (int) (Math.random() * 6);
		switch ((int) (Math.random() * 11)) {
		case 0:
			this.cmdBoard.getCmdBox(slot).addDamageCard(new BackMoveCard(this));
			break;
		case 1:
			this.cmdBoard.getCmdBox(slot).addDamageCard(new ForwardMoveCard(this));
			break;
		case 2:
			this.cmdBoard.getCmdBox(slot).addDamageCard(new LeftMoveCard(this));
			break;
		case 3:
			this.cmdBoard.getCmdBox(slot).addDamageCard(new RightMoveCard(this));
			break;
		case 4:
			this.cmdBoard.getCmdBox(slot).addDamageCard(new Rotate90Card(this));
			break;
		case 5:
			this.cmdBoard.getCmdBox(slot).addDamageCard(new Rotate180Card(this));
			break;
		case 6:
			this.cmdBoard.getCmdBox(slot).addDamageCard(new Rotate270Card(this));
			break;
		case 7:
			Swap12card swap12Card = new Swap12card(this);
			swap12Card.trigger();
			break;
		case 8:
			Swap34card swap34Card = new Swap34card(this);
			swap34Card.trigger();
			break;
		case 9:
			Swap56card swap56Card = new Swap56card(this);
			swap56Card.trigger();
			break;
		case 10:
			Reversecard reverseCard = new Reversecard(this);
			reverseCard.trigger();
			break;
		default:
			break;
		}
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
				this.spriteValue = TileSprite.MECH1_UP;
				break;
			case RIGHT:
				this.spriteValue = TileSprite.MECH1_RIGHT;
				break;
			case DOWN:
				this.spriteValue = TileSprite.MECH1_DOWN;
				break;
			case LEFT:
				this.spriteValue = TileSprite.MECH1_LEFT;
				break;
			default:
				break;
			}
			break;
		case 1:
			switch (dir) {
			case UP:
				this.spriteValue = TileSprite.MECH2_UP;
				break;
			case RIGHT:
				this.spriteValue = TileSprite.MECH2_RIGHT;
				break;
			case DOWN:
				this.spriteValue = TileSprite.MECH2_DOWN;
				break;
			case LEFT:
				this.spriteValue = TileSprite.MECH2_LEFT;
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
