package logic;

import java.util.ArrayList;
import card.base.CmdCard;
import tile.*;
import token.*;

public class GameController {
	private static Board board;
	private static Mech redMech;
	private static Mech blueMech;
	private static Phase currentPhase;
	private static int turnCount;
	private static int score;
	private static int damageCount;
	private static ArrayList<Object> selectable;
	private static DraftedCard draftedCard;
	private static int programCount;
	public static void initializeGame() {
		initializeBoard();
		//draftedCard = new DraftedCard();
		redMech = new Mech(Direction.DOWN,board.getTile(0,0),0);
		blueMech = new Mech(Direction.UP,board.getTile(9, 9),1);
		currentPhase = Phase.Program;
		turnCount = 1;
		score = 0;
		damageCount =0;
		programCount = 0;
	}
	public static void initializeBoard() {
		board = new Board();
		int specialTileNum = 0;
		while(specialTileNum < 6) {
			GameController.randomTile();
			specialTileNum += 1;
		}
	}
	public static void randomTile() {
		//random location that is not already special tile
		int randomX;
		int randomY;
		do {
		randomX = (int) (Math.random()*10);
		randomY = (int) (Math.random()*10);
		System.out.println(randomX+randomY);
		}while(board.isSpecial(randomX,randomY));
		
		int random = (int) (Math.random()*4);
		switch(random) {
		case 0:
			board.setTile(randomX,randomY,new ExplosiveTile(randomX,randomY));
			break;
		case 1:
			board.setTile(randomX,randomY,new MoveTile(randomX,randomY));
			break;
		case 2:
			board.setTile(randomX,randomY,new SpinTile(randomX,randomY));
			break;
		case 3:
			board.setTile(randomX,randomY,new SlipTile(randomX,randomY));
			break;
		default:
			break;
		}
	}
	public static void creatSpawnTile() {
		int randomX;
		int randomY;
		do {
		randomX = (int) (Math.random()*10);
		randomY = (int) (Math.random()*10);
		System.out.println(randomX+randomY);
		}while(board.isSpecial(randomX,randomY));
		
		board.setTile(randomX, randomY,new SpawnTile(randomX, randomY));
	}
	public static Board getBoard() {
		return board;
	}
	public static void update() {
		getBoard().update();
	}
	public static void nextPhase() {
		switch(currentPhase) {
		case Program:
			currentPhase = Phase.Execute;
			draftedCard.reDeal();
			execute(programCount);
			break;
		case Execute:
			programCount = 0;
			currentPhase = Phase.MinionMove;
			switch((int) (Math.random()*4)) {
			case 0:
				for(Minion e: getBoard().getMinionList()) {
					move(e,Direction.UP);
				}
				break;
			case 1:
				for(Minion e: getBoard().getMinionList()) {
					move(e,Direction.DOWN);
				}
				break;
			case 2:
				for(Minion e: getBoard().getMinionList()) {
					move(e,Direction.LEFT);
				}
				break;
			case 3:
				for(Minion e: getBoard().getMinionList()) {
					move(e,Direction.RIGHT);
				}
				break;
			default:
				break;
			}
			break;
		case MinionMove:
			currentPhase = Phase.MinionAttack;
			for(Minion minion:getBoard().getMinionList()) {
				minion.attack();
			}
			nextPhase();
			break;
		case MinionAttack:
			currentPhase = Phase.MinionSpawn;
			for(SpawnTile spawnTile: getBoard().getSpawnTileList()) {
				spawnTile.spawn();
			}
			nextPhase();
			break;
		case MinionSpawn:
			currentPhase = Phase.Program;
			update();
			turnCount += 1;
			if(turnCount%3 == 0) {
				creatSpawnTile();
			}
			break;
		}
	}
	public static void setProgram(Mech selectedMech,int commardSlot,CmdCard selectedCard) {
		
	}
	public static boolean move(Token selectedToken, Direction dir) {
		if(getBoard().getAdjacentTile(selectedToken.getSelfTile(), 1, dir).size() == 0) {
			return false;
		}
		if(getBoard().getAdjacentTile(selectedToken.getSelfTile(), 1, dir).get(0).getToken() == null ||
				move(getBoard().getAdjacentTile(selectedToken.getSelfTile(), 1, dir).get(0).getToken(),dir)) {
			getBoard().getAdjacentTile(selectedToken.getSelfTile(), 1, dir).get(0).setToken(selectedToken);
			selectedToken.getSelfTile().setToken(null);
			selectedToken.setSelfTile(getBoard().getAdjacentTile(selectedToken.getSelfTile(), 1, dir).get(0));
			if(selectedToken.getSelfTile() instanceof SlipTile) {
				move(selectedToken,dir);
			}
			return true;
		};
		return false;
	}
	public static void select(ArrayList<Object> selectable) {
	}
	public static void execute(int programCount) {
		if(programCount==12) {
			nextPhase();
		}else {
			boolean keepGoing;
			if(programCount<6) {
				keepGoing = redMech.getCmdBoard().getCmdBox(programCount).execute(); 
			}else {
				keepGoing = blueMech.getCmdBoard().getCmdBox(programCount-6).execute();
			}
			programCount++;
			if(keepGoing) {
				execute(programCount);
			}
		}
	}
	public static void addDamgeCount(int i) {
		damageCount += 1;
		if(damageCount == 10) {
			System.out.println("You Die");
		}
	}
	
}
