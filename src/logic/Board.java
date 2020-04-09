package logic;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import tile.*;
import token.*;

public class Board extends GridPane{
	public final int BOARDSIZEX = 10;
	public final int BOARDSIZEY = 10;
	private ArrayList<ArrayList<Tile>> tileBoard = new ArrayList<ArrayList<Tile>>();
	private ArrayList<SpawnTile> spawnTileList = new ArrayList<>();
	private ArrayList<Minion> minionList = new ArrayList<>();

	public Board() {
		super();
		this.setAlignment(Pos.CENTER);
		this.setPrefWidth(480);
		this.setPrefHeight(480);
		this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, 
				CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		for (int i = 0; i < BOARDSIZEX; i++) {
			ArrayList<Tile> tileRow = new ArrayList<Tile>();
			for (int j = 0; j < BOARDSIZEY; j++) {
				tileRow.add(new Tile(j, i)) ;
				this.add(tileRow.get(j), j, i);
			}
			tileBoard.add(tileRow);
		}
	}
	public void drawGameBoard() {
		for (int i = 0; i < BOARDSIZEX; i++) {
			for (int j = 0; j < BOARDSIZEY; j++) {
				this.tileBoard.get(j).get(i).draw();	
			}
		}
	}

	public Tile getTile(int x, int y) {
		return this.tileBoard.get(y).get(x);
	}

	public void setTile(int x, int y, Tile newTile) {
		this.tileBoard.get(y).set(x,  newTile);
		this.add(newTile, x, y);
	}

	public ArrayList<Tile> getAdjacentTile(Tile tile, int range, Direction dir) {
		ArrayList<Tile> result = new ArrayList<>();
		int x = tile.getLocationX();
		int y = tile.getLocationY();
		switch (dir) {
		case LEFT:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x - i, y)) {
					result.add(tileBoard.get(y).get(x-i));
				}
			}
			break;
		case UP:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x, y - i)) {
					result.add(tileBoard.get(y-i).get(x));
				}
			}
			break;
		case RIGHT:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x + i, y)) {
					result.add(this.tileBoard.get(y).get(x+i));
				}
			}
			break;
		case DOWN:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x, y + i)) {
					result.add(this.tileBoard.get(y+i).get(x));
				}
			}
			break;
		case ALL:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x - i, y)) {
					result.add(this.tileBoard.get(y).get(x-i));
				}
				if (isMovePossible(x, y - i)) {
					result.add(this.tileBoard.get(y-i).get(x));
				}
				if (isMovePossible(x + i, y)) {
					result.add(this.tileBoard.get(y).get(x+i));
				}
				if (isMovePossible(x, y + i)) {
					result.add(this.tileBoard.get(y+i).get(x));
				}
			}
			break;
		default:
			break;
		}
		return result;
	}

	public ArrayList<Tile> getDiagonalTile(Tile tile, int range, Direction dir) {
		ArrayList<Tile> result = new ArrayList<>();
		int x = tile.getLocationX();
		int y = tile.getLocationY();
		switch (dir) {
		case LEFT:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x - i, y - i)) {
					result.add(this.tileBoard.get(y-i).get(x-i));
				}
				if (isMovePossible(x - i, y + i)) {
					result.add(this.tileBoard.get(y+i).get(x-i));
				}
			}
			break;
		case UP:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x + i, y - i)) {
					result.add(this.tileBoard.get(y-i).get(x+i));
				}
				if (isMovePossible(x - i, y - i)) {
					result.add(this.tileBoard.get(y-i).get(x-i));
				}
			}
			break;
		case RIGHT:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x + i, y - i)) {
					result.add(this.tileBoard.get(y-i).get(x+i));
				}
				if (isMovePossible(x + i, y + i)) {
					result.add(this.tileBoard.get(y+i).get(x+i));
				}
			}
			break;
		case DOWN:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x + i, y + i)) {
					result.add(this.tileBoard.get(y+i).get(x+i));
				}
				if (isMovePossible(x - i, y + i)) {
					result.add(this.tileBoard.get(y+i).get(x-i));
				}
			}
			break;
		case ALL:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x + i, y - i)) {
					result.add(this.tileBoard.get(y-i).get(x+i));
				}
				if (isMovePossible(x - i, y - i)) {
					result.add(this.tileBoard.get(y-i).get(x-i));
				}
				if (isMovePossible(x + i, y + i)) {
					result.add(this.tileBoard.get(y+i).get(x+i));
				}
				if (isMovePossible(x - i, y + i)) {
					result.add(this.tileBoard.get(y+i).get(x-i));
				}
			}
			break;
		default:
			break;
		}
		return result;
	}

	public ArrayList<Tile> getBorder(Tile tile, int range) {
		ArrayList<Tile> result = new ArrayList<Tile>();
		int x = tile.getLocationX();
		int y = tile.getLocationY();
		for (int i = x - range; i <= x + range; i++) {
			for (int j = y - range; j <= y + range; j++) {
				if (this.isMovePossible(i, j)) {
					result.add(this.tileBoard.get(j).get(i));
				}
			}
		}
		return result;
	}

	public Boolean isMovePossible(int x, int y) {
		return x >= 0 && x < BOARDSIZEX && y >= 0 && y < BOARDSIZEY;
	}

	public ArrayList<Minion> getMinionList() {
		return this.minionList;
	}

	public ArrayList<SpawnTile> getSpawnTileList() {
		return this.spawnTileList;
	}

	public boolean isSpecial(int x, int y) {
		return (this.tileBoard.get(y).get(x) instanceof ExplosiveTile) || (this.tileBoard.get(y).get(x) instanceof MoveTile)
				|| (this.tileBoard.get(y).get(x) instanceof SlipTile) || (this.tileBoard.get(y).get(x) instanceof SpinTile)
				|| (this.tileBoard.get(y).get(x) instanceof SpawnTile) || (x == 0 && y == 0) || (x == 9 && y == 9);
	}
	public void clearSelectable() {
		for (int i = 0; i < BOARDSIZEX; i++) {
			for (int j = 0; j < BOARDSIZEY; j++) {
				this.tileBoard.get(j).get(i).setSelectable(false);
				this.tileBoard.get(j).get(i).setSelectToken(false);
			}
		}
	}
}
