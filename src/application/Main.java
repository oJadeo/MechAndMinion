package application;

import gui.DirectionPane;
import gui.PhasePane;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.CmdBoard;
import logic.DraftedCard;
import logic.GameController;
import logic.TileSprite;

public class Main extends Application {

	Scene firstScene;
	Stage window;
	ImageView tu;
	int pageTuorial;
	

	@Override
	public void start(Stage primaryStage) {

		VBox menu = new VBox();
		menu.setSpacing(20);
		BackgroundImage myImage= new BackgroundImage(new Image(ClassLoader.getSystemResource("Wallpaper.jpg").toString(),1380,680,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		menu.setBackground(new Background(myImage));
		menu.setAlignment(Pos.CENTER);
		menu.getChildren().add(new ImageView(new Image(ClassLoader.getSystemResource("Logo.png").toString())));
        
		VBox start = new VBox();
		start.setAlignment(Pos.CENTER);
		ImageView imageViewStart = new ImageView(new Image(ClassLoader.getSystemResource("Start.png").toString()));
		start.getChildren().add(imageViewStart);
		menu.getChildren().add(start);
		
		VBox tutorial = new VBox();
		tutorial.setAlignment(Pos.CENTER);
		ImageView imageViewTutorial = new ImageView(new Image(ClassLoader.getSystemResource("Tutorial.png").toString()));
		tutorial.getChildren().add(imageViewTutorial);
		menu.getChildren().add(tutorial);
		
		VBox exit = new VBox();
		exit.setAlignment(Pos.CENTER);
		ImageView imageViewExit = new ImageView(new Image(ClassLoader.getSystemResource("Exit.png").toString()));
		exit.getChildren().add(imageViewExit);
		menu.getChildren().add(exit);
		
		
		start.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent arg0) {
				GameController.initializeGame();
				primaryStage.setScene(update());
				primaryStage.show();
				
			}
		});
		start.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0.3,0);
			    imageViewStart.setEffect(colorAdjust);
			}
		});
		start.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0,0);
			    imageViewStart.setEffect(colorAdjust);
			}
		});
		tutorial.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent arg0) {
				primaryStage.setScene(tutorialGame());
				primaryStage.show();
				
			}
		});
		tutorial.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0.3,0);
			    imageViewTutorial.setEffect(colorAdjust);
			}
		});
		tutorial.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0,0);
			    imageViewTutorial.setEffect(colorAdjust);
			}
		});

		exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			public void handle(MouseEvent arg0) {
				primaryStage.close();
				
			}
		});
		exit.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0.3,0);
			    imageViewExit.setEffect(colorAdjust);
			}
		});
		exit.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0,0);
			    imageViewExit.setEffect(colorAdjust);
			}
		});
		
		firstScene = new Scene(menu,1380,680);
		window = primaryStage;
		primaryStage.setTitle("Mech and Minion");
		primaryStage.setScene(firstScene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	public Scene tutorialGame() {

		int minPage = 1;
		int maxPage = 7;
		VBox root = new VBox();
		BackgroundImage image= new BackgroundImage(new Image(ClassLoader.getSystemResource("Wallpaper.jpg").toString(),1380,680,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		root.setBackground(new Background(image));
		root.setSpacing(20);
		
		
		
		HBox upRoot = new HBox();
		VBox back = new VBox();
		back.setAlignment(Pos.TOP_LEFT);
		ImageView imageViewBack = new ImageView(new Image(ClassLoader.getSystemResource("Back.png").toString()));
		back.getChildren().add(imageViewBack);
		upRoot.getChildren().add(back);
		back.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				window.setScene(firstScene);
				window.show();
			}
		});
		back.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0.3,0);
			    imageViewBack.setEffect(colorAdjust);
			}
		});
		back.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0,0);
			    imageViewBack.setEffect(colorAdjust);
			}
		});
		
		
		HBox midRoot = new HBox();
		midRoot.setAlignment(Pos.TOP_CENTER);
		tu = new ImageView(new Image("page1.png"));
		midRoot.getChildren().add(tu);
		
		
		
		HBox lowRoot = new HBox();
		lowRoot.setSpacing(20);
		lowRoot.setAlignment(Pos.CENTER);
		VBox arrowLeft = new VBox();
		ImageView imageViewArrowLeft = new ImageView(new Image(ClassLoader.getSystemResource("ArrowLeft.png").toString()));
		imageViewArrowLeft.setVisible(false);
		arrowLeft.getChildren().add(imageViewArrowLeft);
		lowRoot.getChildren().add(arrowLeft);
		VBox arrowRight = new VBox();
		ImageView  imageViewArrowRight = new ImageView(new Image(ClassLoader.getSystemResource("ArrowRight.png").toString()));
		arrowRight.getChildren().add(imageViewArrowRight);
		lowRoot.getChildren().add(arrowRight);
		arrowRight.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if(pageTuorial < maxPage) {
					if(pageTuorial == maxPage-1) {
						imageViewArrowRight.setVisible(false);
					}
					if(pageTuorial != minPage+1) {
						imageViewArrowLeft.setVisible(true);
					}
					pageTuorial += 1;
					tu = new ImageView(new Image(ClassLoader.getSystemResource("Page" + pageTuorial + ".png").toString()));
					midRoot.getChildren().remove(0);
					midRoot.getChildren().add(tu);
				}
			}
		});
		arrowRight.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0.3,0);
			    imageViewArrowRight.setEffect(colorAdjust);
			}
		});
		arrowRight.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0,0);
				imageViewArrowRight.setEffect(colorAdjust);
			}
		});
		arrowLeft.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if(pageTuorial > minPage) {
					if(pageTuorial == minPage+1) {
						imageViewArrowLeft.setVisible(false);
					}
					if(pageTuorial != maxPage-1) {
						imageViewArrowRight.setVisible(true);
					}
					pageTuorial -= 1;
					tu = new ImageView(new Image(ClassLoader.getSystemResource("Page" + pageTuorial + ".png").toString()));
					midRoot.getChildren().remove(0);
					midRoot.getChildren().add(tu);
				}
			}
		});

		arrowLeft.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0.3,0);
				imageViewArrowLeft.setEffect(colorAdjust);
			}
		});
		arrowLeft.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0,0);
				imageViewArrowLeft.setEffect(colorAdjust);
			}
		});
		
		
		root.getChildren().add(upRoot);
		root.getChildren().add(midRoot);
		root.getChildren().add(lowRoot);
		Scene scene = new Scene(root,1380,680);
		return scene;
	}

	public Scene update() {
		VBox root = new VBox();
		
		
		HBox boardRoot = new HBox();
		boardRoot.getChildren().add(GameController.getBoard());
		boardRoot.getChildren().add(drawUpRight());

		// BottomHalf
		HBox cmdBoardRoot = new HBox();

		// Red Mech Command Board
		CmdBoard redCmdBoard = GameController.getRedMech().getCmdBoard();
		cmdBoardRoot.getChildren().add(redCmdBoard);

		// Blue Mech Command Board
		CmdBoard blueCmdBoard = GameController.getBlueMech().getCmdBoard();
		cmdBoardRoot.getChildren().add(blueCmdBoard);

		root.getChildren().add(boardRoot);
		root.getChildren().add(cmdBoardRoot);
		Scene scene = new Scene(root);
		return scene;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void drawScore(GraphicsContext gc) {
		DrawUtil.drawPhase(gc, 105, 0, 5);
		int point = GameController.getScore();
		for (int i = 0; i < 3; i++) {
			DrawUtil.drawTile(gc, 210 + 24 * (3 - i), 0, 23 + (point % 10));
			point = point / 10;
		}
	}

	public static void drawHealth(GraphicsContext gc) {
		DrawUtil.drawPhase(gc, 100, 0, 6);
		for (int i = 0; i < GameController.getDamageCount(); i++) {
			DrawUtil.drawTile(gc, 250 + 48 * i, 0, TileSprite.LOSE_HEALTH);
		}
		for (int i = 0; i < GameController.getHealth(); i++) {
			DrawUtil.drawTile(gc, 250 + 48 * (i + GameController.getDamageCount()), 0, TileSprite.REMAIN_HEALTH);
		}
	}

	public static VBox drawUpRight() {
		VBox upRight = new VBox();
		upRight.setAlignment(Pos.CENTER);
		upRight.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		HBox score = new HBox();
		Canvas scoreCanvas = new Canvas(900, 60);
		GraphicsContext scoreGC = scoreCanvas.getGraphicsContext2D();
		drawScore(scoreGC);
		score.getChildren().add(scoreCanvas);
		upRight.getChildren().add(score);

		HBox health = new HBox();
		Canvas healthCanvas = new Canvas(900, 60);
		GraphicsContext healthGC = healthCanvas.getGraphicsContext2D();
		drawHealth(healthGC);
		health.getChildren().add(healthCanvas);
		upRight.getChildren().add(health);

		PhasePane phase = GameController.getPhasePane();
		upRight.getChildren().add(phase);

		DirectionPane directionPane = GameController.getDirectionPane();
		upRight.getChildren().add(directionPane);

		DraftedCard draftedCardsBox = GameController.getDraftedCard();
		upRight.getChildren().add(draftedCardsBox);
		return upRight;
	}
}
