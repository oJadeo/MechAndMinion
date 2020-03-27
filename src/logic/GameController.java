package logic;

import java.util.ArrayList;
import card.base.CmdCard;
import tile.*;
import token.*;

public class GameController {
	private static Board board;
	private static Mech redMech;
	private static int redMechProgram;
	private static Mech blueMech;
	private static int blueMechProgram;
	private static Phase currentPhase;
	private static int turnCount;
	private static int score;
	private static int damageCount;
	private static ArrayList<Object> selectable = new ArrayList<Object>();
	private static int selectTimes;
	private static DraftedCard draftedCard;
	private static int programCount;
	private static boolean gameEnd;
	
	public static void initializeGame() {
		initializeBoard();
		draftedCard = new DraftedCard();
		redMech = new Mech(Direction.DOWN,board.getTile(0,0),0);
		redMechProgram = 0;
		blueMech = new Mech(Direction.UP,board.getTile(9,9),1);
		blueMechProgram = 0;
		currentPhase = Phase.Program;
		turnCount = 1;
		score = 0;
		damageCount =0;
		programCount = 0;
		gameEnd = false;
		update();
	}
	public static void initializeBoard() {
		board = new Board();
		int specialTileNum = 0;
		while(specialTileNum < 6) {
			GameController.randomTile();
			specialTileNum += 1;
		}
		for(int i = 0;i<3;i++) {
			creatSpawnTile();
		}
		Minion testMinion = new Minion(Direction.UP,board.getTile(0,1));
		Minion testMinion2 = new Minion(Direction.UP,board.getTile(0,2));
	}
	public static void randomTile() {
		//random location that is not already special tile
		int randomX;
		int randomY;
		do {
		randomX = (int) (Math.random()*10);
		randomY = (int) (Math.random()*10);
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
		}while(board.isSpecial(randomX,randomY));
		
		board.setTile(randomX, randomY,new SpawnTile(randomX, randomY));
	}
	public static Board getBoard() {
		return board;
	}
	public static void update() {
		switch(currentPhase) {
		case Program:
			board.update();
			draftedCard.update();
			redMech.update();
			blueMech.update();
			break;
		case Execute:
			board.update();
			redMech.update();
			blueMech.update();
			System.out.println("times = "+selectable.size());
			String result = "selectable = [";
			for(Object e: selectable) {
				if(e instanceof Tile) {
					result += " ["+((Tile)e).getLocationX()+","+((Tile)e).getLocationY()+"] ";
				}
				if(e instanceof Token) {
					result += " ["+((Token)e).getSelfTile().getLocationX()+","+((Token)e).getSelfTile().getLocationY()+"] ";
				}
				if(e instanceof Direction) {
					result += " ["+e+"] ";
				}
			}
			result += "]";
			System.out.println(result);
			break;
		default:
			board.update();
			draftedCard.update();
			redMech.update();
			blueMech.update();
			break;
		}
		
	}
	public static void nextPhase() {
		switch(currentPhase) {
		case Program:
			draftedCard.reDeal();
			redMechProgram = 0;
			blueMechProgram = 0;
			currentPhase = Phase.Execute;
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
	public static void setProgram(int no,int cmdSlot,int selectedCardSlot) {
		if(draftedCard.getDraftedCardList().get(selectedCardSlot)==null) {
			System.out.println("can select empty card");
		}if(no == 0 && redMechProgram < 2) {
			draftedCard.getDraftedCardList().get(selectedCardSlot).setProgrammedMech(redMech);
			redMech.getCmdBoard().getCmdBox(cmdSlot).addCmdCard(draftedCard.getDraftedCardList().get(selectedCardSlot));
			draftedCard.getDraftedCardList().set(selectedCardSlot, null);
			redMechProgram += 1;
		}else if(no == 1 && blueMechProgram < 2) {
			draftedCard.getDraftedCardList().get(selectedCardSlot).setProgrammedMech(blueMech);
			blueMech.getCmdBoard().getCmdBox(cmdSlot).addCmdCard(draftedCard.getDraftedCardList().get(selectedCardSlot));
			draftedCard.getDraftedCardList().set(selectedCardSlot, null);
			blueMechProgram += 1;
		}else {
			System.out.println("error");
		}
		if(redMechProgram + blueMechProgram == 4) {
			nextPhase();
		}	
		update();
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
	public static void setSelectable(ArrayList<Object> selectable) {
		GameController.selectable = selectable;
	}
	public static void setSelectTimes(int selectTimes) {
		GameController.selectTimes = selectTimes;
		if(selectTimes == 0) {
			execute(programCount+1);
		}
	}
	public static void select(int i) {
		if(i > selectable.size() || i < 0) {
			//exception
		}else if(selectable.get(i) instanceof Token) {
			((Token)selectable.get(i)).damaged();
			selectable.remove(i);
			selectTimes -= 1;
		}
		if(selectTimes == 0) {
			execute(programCount+1);
		}else {
			update();
		}
	}
	public static void execute(int programCount) {
		if(programCount==12) {
			nextPhase();
		}else {
			if(programCount<6) {
				redMech.getCmdBoard().getCmdBox(programCount).execute(); 
			}else {
				blueMech.getCmdBoard().getCmdBox(programCount-6).execute();
			}
			programCount++;
		}
	}
	public static void addDamgeCount(int i) {
		damageCount += 1;
		if(damageCount == 10) {
			System.out.println("You Die");
		}
	}
	public static boolean getGameEnd() {
		return gameEnd;
	}
	public static Phase getCurrentPhase() {
		return currentPhase;
	}
	public static int getProgramCount() {
		return programCount;
	}
}
