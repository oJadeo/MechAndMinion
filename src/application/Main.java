package application;

import java.util.Scanner;

import exception.IndexOutOfRangeException;
import exception.SelectEmptyCardException;
import exception.SelectMechException;
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
<<<<<<< HEAD
				GameController.setProgram(selectedMech-1, selectedSlot-1, selectedCard-1);
=======
				try{
					GameController.setProgram(selectedMech-1, selectedSlot-1, selectedCard-1);
				}catch(SelectEmptyCardException se){
					System.out.println(se.message);
				}catch(IndexOutOfRangeException io) {
					System.out.println(io.message);
				}catch(SelectMechException sm) {
					System.out.println(sm.message);
				}
>>>>>>> 7b2ea1071e58a4553e929d5a7d3b51302c17e070
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
