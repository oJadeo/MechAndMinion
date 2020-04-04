package application;



import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.GameController;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		GameController.initializeGame();
		primaryStage.setTitle("ProgMeth is You");
		primaryStage.setScene(playGame());
		primaryStage.show();
	}
	public Scene playGame() {
		VBox root = new VBox();
		
		HBox boardRoot = new HBox();
		Canvas boardCanvas = new Canvas(480,480);
		GraphicsContext boardGC = boardCanvas.getGraphicsContext2D();
		GameController.getBoard().drawGameBoard(boardGC);
		boardRoot.getChildren().add(boardCanvas);
		
		
		boardRoot.getChildren().add(GameController.drawUpRight());
		
		
		HBox cmdBoardRoot = new HBox();
		Canvas cmdBoardCanvas = new Canvas(1380,200);
		GraphicsContext cmdBoardGC = cmdBoardCanvas.getGraphicsContext2D();
		GameController.drawRedCmdBoard(cmdBoardGC);
		GameController.drawBlueCmdBoard(cmdBoardGC);
		cmdBoardRoot.getChildren().add(cmdBoardCanvas);
		

		root.getChildren().add(boardRoot);
		root.getChildren().add(cmdBoardRoot);
		Scene scene = new Scene(root);
		return scene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
