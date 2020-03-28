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
			GameController.update();
			switch(GameController.getCurrentPhase()) {
			case Program:
				System.out.print("Select Card :");
				int selectedCard = kb.nextInt();
				System.out.print("Select Mech :");
				int selectedMech = kb.nextInt();
				System.out.print("Select Slot :");
				int selectedSlot = kb.nextInt();
				try{
					GameController.setProgram(selectedMech-1, selectedSlot-1, selectedCard-1);
				}catch(SelectEmptyCardException se){
					System.out.println(se.message);
				}catch(IndexOutOfRangeException io) {
					System.out.println(io.message);
				}catch(SelectMechException sm) {
					System.out.println(sm.message);
				}
				break;
			case Execute:
				int selectedObjected = kb.nextInt();
				try{
					GameController.select(selectedObjected-1);
				}catch(IndexOutOfRangeException io) {
					System.out.println(io.message);
				}
				break;
			case MinionMove:
				GameController.addDamgeCount();
				break;
			default:
				break;
			}
		}
	}

}
