package logic;

import java.util.ArrayList;

import application.DrawUtil;

import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
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
	public final int STARTPOSITIONX = 0;
	public final int STARTPOSITIONY = 0;
	private Tile[][] tileBoard = new Tile[BOARDSIZEX][BOARDSIZEY];
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
			for (int j = 0; j < BOARDSIZEY; j++) {
				this.tileBoard[j][i] = new Tile(j, i);
<<<<<<< HEAD
=======
			}
		}
		for(int i = 0 ; i < BOARDSIZEX; i++) {
			for(int j = 0; j < BOARDSIZEY; j++) {
				this.add(tileBoard[j][i], j, i);
			}
		}
	}
	public void drawGameBoard(GraphicsContext gc) {
		
		
		int x = 0;
		int y = 0;
		
		for(Tile[] row:tileBoard) {
			x = 0;
			for(Tile tile:row) {
				DrawUtil.drawTile(gc,STARTPOSITIONX+x*48,STARTPOSITIONY+y*48,tile.getSpriteValue());
				if(tile.getToken() != null) {
					DrawUtil.drawTile(gc,STARTPOSITIONX+x*48,STARTPOSITIONY+y*48,tile.getToken().getSpriteValue());
				}
				x+=1;
>>>>>>> b1adfa2fbd0a9a3d258f195bd4ff66b3fbd0f81c
			}
			y+=1;
		}
	}

	public Tile getTile(int x, int y) {
		return this.tileBoard[y][x];
	}

	public void setTile(int x, int y, Tile newTile) {
		this.tileBoard[y][x] = newTile;
	}

	public ArrayList<Tile> getAdjacentTile(Tile tile, int range, Direction dir) {
		ArrayList<Tile> result = new ArrayList<>();
		int x = tile.getLocationX();
		int y = tile.getLocationY();
		switch (dir) {
		case LEFT:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x - i, y)) {
					result.add(tileBoard[y][x - i]);
				}
			}
			break;
		case UP:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x, y - i)) {
					result.add(tileBoard[y - i][x]);
				}
			}
			break;
		case RIGHT:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x + i, y)) {
					result.add(tileBoard[y][x + i]);
				}
			}
			break;
		case DOWN:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x, y + i)) {
					result.add(tileBoard[y + i][x]);
				}
			}
			break;
		case ALL:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x - i, y)) {
					result.add(tileBoard[y][x - i]);
				}
				if (isMovePossible(x, y - i)) {
					result.add(tileBoard[y - i][x]);
				}
				if (isMovePossible(x + i, y)) {
					result.add(tileBoard[y][x + i]);
				}
				if (isMovePossible(x, y + i)) {
					result.add(tileBoard[y + i][x]);
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
					result.add(tileBoard[y - i][x - i]);
				}
				if (isMovePossible(x - i, y + i)) {
					result.add(tileBoard[y + i][x - i]);
				}
			}
			break;
		case UP:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x + i, y - i)) {
					result.add(tileBoard[y - i][x + i]);
				}
				if (isMovePossible(x - i, y - i)) {
					result.add(tileBoard[y - i][x - i]);
				}
			}
			break;
		case RIGHT:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x + i, y - i)) {
					result.add(tileBoard[y - i][x + i]);
				}
				if (isMovePossible(x + i, y + i)) {
					result.add(tileBoard[y + i][x + i]);
				}
			}
			break;
		case DOWN:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x + i, y + i)) {
					result.add(tileBoard[y + i][x + i]);
				}
				if (isMovePossible(x - i, y + i)) {
					result.add(tileBoard[y + i][x - i]);
				}
			}
			break;
		case ALL:
			for (int i = 1; i <= range; i++) {
				if (isMovePossible(x + i, y - i)) {
					result.add(tileBoard[y - i][x + i]);
				}
				if (isMovePossible(x - i, y - i)) {
					result.add(tileBoard[y - i][x - i]);
				}
				if (isMovePossible(x + i, y + i)) {
					result.add(tileBoard[y + i][x + i]);
				}
				if (isMovePossible(x - i, y + i)) {
					result.add(tileBoard[y + i][x - i]);
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
					result.add(tileBoard[j][i]);
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
		return (this.tileBoard[y][x] instanceof ExplosiveTile) || (this.tileBoard[y][x] instanceof MoveTile)
				|| (this.tileBoard[y][x] instanceof SlipTile) || (this.tileBoard[y][x] instanceof SpinTile)
				|| (this.tileBoard[y][x] instanceof SpawnTile) || (x == 0 && y == 0) || (x == 9 && y == 9);
	}

	public void update() {
		for (int i = 0; i < BOARDSIZEX; i++) {
			String result = "[";
			for (int j = 0; j < BOARDSIZEY; j++) {
				if (tileBoard[i][j].getToken() == null) {
					if (tileBoard[i][j] instanceof ExplosiveTile) {
						result += " E ";
					} else if (tileBoard[i][j] instanceof MoveTile) {
						result += " M ";
					} else if (tileBoard[i][j] instanceof SpinTile) {
						result += " S ";
					} else if (tileBoard[i][j] instanceof SlipTile) {
						result += " L ";
					} else if (tileBoard[i][j] instanceof SpawnTile) {
						result += " # ";
					} else {
						result += " 0 ";
					}
				}
				if (tileBoard[i][j].getToken() instanceof Mech) {
					if (((Mech) tileBoard[i][j].getToken()).getNo() == 0) {
						switch (tileBoard[i][j].getToken().getDirection()) {
						case UP:
							result += " ^ ";
							break;
						case DOWN:
							result += " v ";
							break;
						case LEFT:
							result += " < ";
							break;
						case RIGHT:
							result += " > ";
							break;
						default:
							break;
						}
					}
					if (((Mech) tileBoard[i][j].getToken()).getNo() == 1) {
						switch (tileBoard[i][j].getToken().getDirection()) {
						case UP:
							result += " ^^";
							break;
						case DOWN:
							result += " vv";
							break;
						case LEFT:
							result += " <<";
							break;
						case RIGHT:
							result += " >>";
							break;
						default:
							break;
						}

					}
				}
				if (tileBoard[i][j].getToken() instanceof Minion) {
					result += " * ";
				}

			}
			result += "]";
			System.out.println(result);
		}
	}
}
