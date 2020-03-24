package logic;

import java.util.ArrayList;
import card.base.CmdCard;
import token.Mech;
import token.Token;

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
		draftedCard = new DraftedCard();
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
	}
	public static Board getBoard() {
		return board;
	}
	public static void update() {
		
	}
	public static void nextPhase() {
		switch(currentPhase) {
		case Program:
			execute(programCount);
			currentPhase = Phase.Execute;
			break;
		case Execute:
			//MakeMinionMove
			break;
		case MinionMove:
			//CreatNewMinion
			break;
		case MinionSpawn:
			//MakeMinionAttack
			break;
		case MinionAttack:
			draftedCard.reDeal();
			update();
			programCount = 0;
			break;
		}
		
	}
	public static void nextTurn() {
		
	}
	public static void setProgram(Mech selectedMech,int commardSlot,CmdCard selectedCard) {
		
	}
	public static void repair(Mech selectedMech) {
		
	}
	public static void reprogram(Mech selectedMech) {
		
	}
	public static void move(Token selectedToken, Direction dir) {
		
	}
	public static void rotate(Token selectedToken,int rotdegree) {
		
	}
	public static void damage(Token selectedToken) {
		
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
	
	
}
