package token;

import logic.CmdBoard;
import logic.Direction;
import logic.Sprite;
import tile.Tile;

public class Mech extends Token{
	private CmdBoard cmdBoard;
	public Mech(Direction dir,Tile selfTile) {
		super(dir,selfTile);
		this.setSpriteValue(Sprite.MECH1);
		this.cmdBoard = new CmdBoard();
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
