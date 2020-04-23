package gui;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.layout.Pane;

public class endGameScene extends Pane{
	private ArrayList<ArrayList<String>> topScore;
	public endGameScene() {
		
	}
	public ArrayList<ArrayList<String>> getTopScore(){
		
		return topScore;
		
	}
	public void saveNewScore(String Name,int Score) {
		try {
			FileWriter writer = new FileWriter("res/score.txt", true);
			writer.write(Name.trim()+"\n");
			writer.write(Score);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
