package logic;

import java.util.ArrayList;

import javax.smartcardio.Card;

import card.CmdCard;
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
	private static boolean selectable;
	private static ArrayList<CmdCard> draftedCard;
	public static initializeBoard() {
		
	}
	public static Board getBoard() {
		return board;
	}
	public static void draw() {
		
	}
	public static void update() {
		
	}
	public static void nextPhase() {
		
	}
	public static void nextTurn() {
		
	}
	public static void setProgram(Mech selectedMech,int commardSlot,Card selectedCard) {
		
	}
	public static void repair(Mech selectedMech, Card selectedCard) {
		
	}
	public static void reprogram(Mech selectedMech, Card selectedCard) {
		
	}
	public static Boolean move(Token selectedToken, Direction dir) {
		
	}
	public static void rotate(Token selectedToken,int rotdegree) {
		
	}
	public static void damage(Token selectedToken) {
		
	}
	
	
	
}
