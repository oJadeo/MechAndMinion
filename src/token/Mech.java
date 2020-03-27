package token;

import logic.*;
import damagecard.*;
import tile.Tile;

public class Mech extends Token{
	private CmdBoard cmdBoard;
	private int no;
	public Mech(Direction dir,Tile selfTile,int no) {
		super(dir,selfTile);
		if (no == 0) {
			this.setSpriteValue(Sprite.MECH1);
		}else {
			this.setSpriteValue(Sprite.MECH2);
		}
		this.no = no;
		this.cmdBoard = new CmdBoard(no);
	}
	@Override
	public void damaged() {
		int slot = (int) (Math.random()*6);
		switch((int) (Math.random()*11)) {
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
		GameController.addDamgeCount(1);
	}
	public CmdBoard getCmdBoard() {
		return this.cmdBoard;
	}
	public void setCmdBoard(CmdBoard cmdBoard) {
		this.cmdBoard = cmdBoard;
	}
	public void update() {
		this.cmdBoard.update();
	}
	public int getNo() {
		return no;
	}
}
