package card.base;

import java.util.ArrayList;

import tile.Tile;

public interface Move {
	public ArrayList<Tile> move(int tier);
}
