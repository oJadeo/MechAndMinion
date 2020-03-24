package logic;

import java.util.ArrayList;

import tile.SpawnTile;
import tile.Tile;
import token.Minion;

public class Board {
	private static final Direction ALL = null;
	public final int BOARDSIZEX = 10;
	public final int BOARDSIZEY = 10;
	public final int STARTPOSITIONX = 0;
	public final int STARTPOSITIONY = 0;
	private Tile[][] tileBoard = new Tile[BOARDSIZEX][BOARDSIZEY];
	private ArrayList<SpawnTile> spawnTileList  = new ArrayList<>();
	private ArrayList<Minion> minionList = new ArrayList<>();
	public Board() {
		for(int i=0;i<BOARDSIZEX;i++) {
			for(int j=0;j<BOARDSIZEY;j++) {
				this.tileBoard[i][j] = new Tile(i,j);
			}
		}
	}
	public Tile getTile(int x,int y) {
		return this.tileBoard[x][y];
	}
	public ArrayList<Tile> getAdjacentTile(Tile tile,int range,Direction dir){
		ArrayList<Tile> result = new ArrayList<>();
		int x = tile.getLocationX();
		int y = tile.getLocationY();
		switch(dir) {
		case LEFT:
			for (int i=1;i<=range;i++) {
				if (x-i >= 0) {
					result.add(tileBoard[x-i][y]);
				}
			}
			break;
		case UP:
			for(int i=1;i<=range;i++) {
				if(y-i >= 0) {
					result.add(tileBoard[x][y-i]);
				}
			}
			break;
		case RIGHT:
			for(int i=1;i<=range;i++) {
				if(x+i < BOARDSIZEX) {
					result.add(tileBoard[x+i][y]);
				}
			}
			break;
		case DOWN:
			for(int i=1;i<=range;i++) {
				if(y+i < BOARDSIZEY) {
					result.add(tileBoard[x][y+i]);
				}
			}
			break;
		case ALL:
			for (int i=1;i<=range;i++) {
				if (x-i >= 0) {
					result.add(tileBoard[x-i][y]);
				}
				if(y-i >= 0) {
					result.add(tileBoard[x][y-i]);
				}
				if(x+i < BOARDSIZEX) {
					result.add(tileBoard[x+i][y]);
				}
				if(y+i < BOARDSIZEY) {
					result.add(tileBoard[x][y+i]);
				}
			}
			break;
		default:
			break;
		}
		return result;
	}
	public ArrayList<Tile> getDiagonalTile(Tile tile,int range,Direction dir){
		ArrayList<Tile> result = new ArrayList<>();
		int x = tile.getLocationX();
		int y = tile.getLocationY();
		switch(dir) {
		case LEFT:
			for(int i=1;i<range;i++) {
				if(x-i>=0) {
					if(y-i>=0) {
						result.add(tileBoard[x-i][y-i]);
					}
					if(y+i<BOARDSIZEY) {
						result.add(tileBoard[x-i][y+i]);
					}
				}
			}
			break;
		case UP:
			for(int i=1;i<=range;i++) {
				if(y-i >= 0) {
					if(x+i<BOARDSIZEX) {
						result.add(tileBoard[x+i][y-i]);
					}
					if(x-i>=0) {
						result.add(tileBoard[x-i][y-i]);
					}
				}
			}
			break;
		case RIGHT:
			for(int i=1;i<range;i++) {
				if(x+i<BOARDSIZEX) {
					if(y-i>=0) {
						result.add(tileBoard[x+i][y-i]);
					}
					if(y+i<BOARDSIZEY) {
						result.add(tileBoard[x+i][y+i]);
					}
				}
			}
			break;
		case DOWN:
			for(int i=1;i<=range;i++) {
				if(y+i < BOARDSIZEY) {
					if(x+i<BOARDSIZEX) {
						result.add(tileBoard[x+i][y+i]);
					}
					if(x-i>=0) {
						result.add(tileBoard[x-i][y+i]);
					}
				}
			}
			break;
		case ALL:
			for(int i=1;i<=range;i++) {
				if(y-i >= 0) {
					if(x+i<BOARDSIZEX) {
						result.add(tileBoard[x+i][y-i]);
					}
					if(x-i>=0) {
						result.add(tileBoard[x-i][y-i]);
					}
				}
				if(y+i < BOARDSIZEY) {
					if(x+i<BOARDSIZEX) {
						result.add(tileBoard[x+i][y+i]);
					}
					if(x-i>=0) {
						result.add(tileBoard[x-i][y+i]);
					}
				}
			}
			break;
		default:
			break;
		}
		return result;
	}
	public ArrayList<Tile> getBorder(Tile tile,int range){
		Direction dir = ALL;
		ArrayList<Tile> result = this.getAdjacentTile(tile, range, dir);
		result.addAll(this.getDiagonalTile(tile, range, dir));
		return result;
	}
	public Boolean isMovePossible(int x,int y) {
		return x>=0 && x<BOARDSIZEX && y>=0 && y<BOARDSIZEY;
	}
	public void update() {
		
	}
	public ArrayList<Minion> getMinionList() {
		// TODO Auto-generated method stub
		return this.minionList;
	}
	public ArrayList<SpawnTile> getSpawnTileList(){
		return this.spawnTileList;
	}
}
