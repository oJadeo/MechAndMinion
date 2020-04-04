package application;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.GameController;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		GameController.initializeGame();
		primaryStage.setTitle("ProgMeth is You");
		primaryStage.setScene(update());
		primaryStage.show();
	}
	
	public Scene update() {
		VBox root = new VBox();

		HBox boardRoot = new HBox();
		Canvas boardCanvas = new Canvas(480, 480);
		GraphicsContext boardGC = boardCanvas.getGraphicsContext2D();
		GameController.getBoard().drawGameBoard(boardGC);
		boardRoot.getChildren().add(boardCanvas);

		boardRoot.getChildren().add(GameController.drawUpRight());
		
		//BottomHalf
		HBox cmdBoardRoot = new HBox();
		//Red Mech Command Board
		HBox redCmdBoard = new HBox();
		redCmdBoard.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		Canvas redCmdBoardCanvas = new Canvas(690, 200);
		GraphicsContext redCmdBoardGC = redCmdBoardCanvas.getGraphicsContext2D();
		GameController.drawRedCmdBoard(redCmdBoardGC);
		redCmdBoard.getChildren().add(redCmdBoardCanvas);
		cmdBoardRoot.getChildren().add(redCmdBoard);
		//Blue Mech Command Board
		HBox blueCmdBoard = new HBox();
		blueCmdBoard.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		Canvas blueCmdBoardCanvas = new Canvas(690, 200);
		GraphicsContext blueCmdBoardGC = blueCmdBoardCanvas.getGraphicsContext2D();
		GameController.drawBlueCmdBoard(blueCmdBoardGC);
		blueCmdBoard.getChildren().add(blueCmdBoardCanvas);
		cmdBoardRoot.getChildren().add(blueCmdBoard);

		root.getChildren().add(boardRoot);
		root.getChildren().add(cmdBoardRoot);
		Scene scene = new Scene(root);
		return scene;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
