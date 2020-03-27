package application;

import java.util.Scanner;

import logic.GameController;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		GameController.initializeGame();
		while(!GameController.getGameEnd()) {
			switch(GameController.getCurrentPhase()) {
			case Program:
				System.out.print("Select Card :");
				int selectedCard = kb.nextInt();
				System.out.print("Select Mech :");
				int selectedMech = kb.nextInt();
				System.out.print("Select Slot :");
				int selectedSlot = kb.nextInt();
				GameController.setProgram(selectedMech-1, selectedSlot-1, selectedCard-1);
				break;
			case Execute:
				System.out.println("Select Target");
				int selectedObjected = kb.nextInt();
				GameController.select(selectedObjected-1);
				break;
			case MinionMove:
				System.out.println("Pass");
				
			default:
				break;
			}
		}
	}

}
