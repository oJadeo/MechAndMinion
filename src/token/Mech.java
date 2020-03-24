package token;

import logic.CmdBoard;
import logic.Direction;
import logic.Sprite;
import tile.Tile;

public class Mech extends Token{
	private CmdBoard cmdBoard;
	private int no;
	public Mech(Direction dir,Tile selfTile,int no) {
		super(dir,selfTile);
		this.setSpriteValue(Sprite.MECH1);
		this.no = no;
		this.cmdBoard = new CmdBoard(no);
		
	}
	@Override
	public void damaged() {
		
	}
	public CmdBoard getCmdBoard() {
		return this.cmdBoard;
	}
	public void setCmdBoard(CmdBoard cmdBoard) {
		this.cmdBoard = cmdBoard;
	}
}
