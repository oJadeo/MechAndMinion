package application;

import gui.DirectionPane;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.CmdBoard;
import logic.DraftedCard;
import logic.GameController;


public class Main extends Application {

	Scene firstScene;
	static Stage window;
	ImageView tu;
	int pageTuorial;
	

	@Override
	public void start(Stage primaryStage) {
		VBox menu = new VBox();
		menu.setSpacing(20);

		BackgroundImage myImage= new BackgroundImage(new Image(ClassLoader.getSystemResource("Wallpaper.jpg").toString(),1920,1080,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		menu.setBackground(new Background(myImage));
		menu.setAlignment(Pos.CENTER);
		menu.setSpacing(450);
		VBox logo = new VBox();
		ImageView imageViewLogo = new ImageView(new Image(ClassLoader.getSystemResource("Logo.png").toString()));
		logo.getChildren().add(imageViewLogo);
		logo.setAlignment(Pos.CENTER);
		menu.getChildren().add(logo);
        VBox menu1 = new VBox();
        
		VBox start = new VBox();
		start.setAlignment(Pos.CENTER);
		ImageView imageViewStart = new ImageView(new Image(ClassLoader.getSystemResource("Start.png").toString()));
		start.getChildren().add(imageViewStart);
		menu1.getChildren().add(start);

		VBox tutorial = new VBox();
		tutorial.setAlignment(Pos.CENTER);
		ImageView imageViewTutorial = new ImageView(new Image(ClassLoader.getSystemResource("Tutorial.png").toString()));
		tutorial.getChildren().add(imageViewTutorial);
		menu1.getChildren().add(tutorial);

		VBox exit = new VBox();
		exit.setAlignment(Pos.CENTER);
		ImageView imageViewExit = new ImageView(new Image(ClassLoader.getSystemResource("Exit.png").toString()));
		exit.getChildren().add(imageViewExit);
		menu1.getChildren().add(exit);
		
		menu.getChildren().add(menu1);
		start.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent arg0) {
				MediaPlayer mediaPlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource("click.mp3").toString()));
				mediaPlayer.setAutoPlay(true);
				mediaPlayer.setVolume(0.3);
				GameController.initializeGame();
				primaryStage.setScene(creatGameScene());
				primaryStage.setFullScreen(true);
				primaryStage.show();

			}
		});
		start.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				imageViewStart.setImage(new Image(ClassLoader.getSystemResource("Start1.png").toString()));
			}
		});
		start.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				imageViewStart.setImage(new Image(ClassLoader.getSystemResource("Start.png").toString()));
			}
		});
		tutorial.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent arg0) {
				MediaPlayer mediaPlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource("click.mp3").toString()));
				mediaPlayer.setAutoPlay(true);
				mediaPlayer.setVolume(0.3);
				primaryStage.setScene(tutorialGame());
				primaryStage.setResizable(false);
				primaryStage.setFullScreen(true);
				primaryStage.show();

			}
		});
		tutorial.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				imageViewTutorial.setImage(new Image(ClassLoader.getSystemResource("Tutorial1.png").toString()));
			}
		});
		tutorial.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				imageViewTutorial.setImage(new Image(ClassLoader.getSystemResource("Tutorial.png").toString()));
			}
		});

		exit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent arg0) {
				MediaPlayer mediaPlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource("click.mp3").toString()));
				mediaPlayer.setAutoPlay(true);
				mediaPlayer.setVolume(0.3);
				primaryStage.close();

			}
		});
		exit.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				imageViewExit.setImage(new Image(ClassLoader.getSystemResource("Exit1.png").toString()));
			}
		});
		exit.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				imageViewExit.setImage(new Image(ClassLoader.getSystemResource("Exit.png").toString()));
			}
		});

		firstScene = new Scene(menu, 1920, 1080);
		window = primaryStage;
		primaryStage.setTitle("Mech and Minion");
		primaryStage.setScene(firstScene);
		primaryStage.setResizable(false);
		primaryStage.setFullScreen(true);
		primaryStage.show();

	}

	public Scene tutorialGame() {

		int minPage = 1;
		int maxPage = 4;
		VBox root = new VBox();

		BackgroundImage image= new BackgroundImage(new Image(ClassLoader.getSystemResource("Wallpaper.jpg").toString(),1920,1080,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);

		root.setBackground(new Background(image));
		root.setSpacing(50);

		HBox upRoot = new HBox();
		VBox back = new VBox();
		back.setAlignment(Pos.TOP_LEFT);
		ImageView imageViewBack = new ImageView(new Image(ClassLoader.getSystemResource("Back.png").toString()));
		back.getChildren().add(imageViewBack);
		upRoot.getChildren().add(back);
		back.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent arg0) {
				MediaPlayer mediaPlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource("click.mp3").toString()));
				mediaPlayer.setAutoPlay(true);
				mediaPlayer.setVolume(0.3);
				window.setScene(firstScene);
				window.setResizable(false);
				window.setFullScreen(true);
				window.show();
			}
		});
		back.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				imageViewBack.setImage(new Image(ClassLoader.getSystemResource("Back1.png").toString()));
				
			}
		});
		back.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				imageViewBack.setImage(new Image(ClassLoader.getSystemResource("Back.png").toString()));
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
		lowRoot.setSpacing(30);
		arrowRight.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				MediaPlayer mediaPlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource("click.mp3").toString()));
				mediaPlayer.setAutoPlay(true);
				mediaPlayer.setVolume(0.3);
				if (pageTuorial < maxPage) {
					if (pageTuorial == maxPage - 1) {
						imageViewArrowRight.setVisible(false);
					}
					if (pageTuorial != minPage + 1) {
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
				ColorAdjust colorAdjust = new ColorAdjust(0, 0, 0.3, 0);
				imageViewArrowRight.setEffect(colorAdjust);
			}
		});
		arrowRight.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				ColorAdjust colorAdjust = new ColorAdjust(0, 0, 0, 0);
				imageViewArrowRight.setEffect(colorAdjust);
			}
		});
		arrowLeft.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				MediaPlayer mediaPlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource("click.mp3").toString()));
				mediaPlayer.setAutoPlay(true);
				mediaPlayer.setVolume(0.3);
				if (pageTuorial > minPage) {
					if (pageTuorial == minPage + 1) {
						imageViewArrowLeft.setVisible(false);
					}
					if (pageTuorial != maxPage - 1) {
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
				ColorAdjust colorAdjust = new ColorAdjust(0, 0, 0.3, 0);
				imageViewArrowLeft.setEffect(colorAdjust);
			}
		});
		arrowLeft.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				ColorAdjust colorAdjust = new ColorAdjust(0, 0, 0, 0);
				imageViewArrowLeft.setEffect(colorAdjust);
			}
		});

		root.getChildren().add(upRoot);
		root.getChildren().add(midRoot);
		root.getChildren().add(lowRoot);
		Scene scene = new Scene(root, 1920, 1080);
		return scene;
	}

	public Scene creatGameScene() {
		StackPane root = new StackPane();
		HBox sceneRoot = new HBox();
		
		// UpHalf
		VBox basicRoot = new VBox();
		HBox boardRoot = new HBox();
		boardRoot.getChildren().add(GameController.getBoard());
		boardRoot.getChildren().add(drawUpRight());

		// BottomHalf
		VBox cmdBoardRoot = new VBox();
		// Red Mech Command Board
		CmdBoard redCmdBoard = GameController.getRedMech().getCmdBoard();
		redCmdBoard.setAlignment(Pos.BOTTOM_LEFT);
		cmdBoardRoot.getChildren().add(redCmdBoard);
		// Blue Mech Command Board
		CmdBoard blueCmdBoard = GameController.getBlueMech().getCmdBoard();
		blueCmdBoard.setAlignment(Pos.BOTTOM_RIGHT);
		cmdBoardRoot.getChildren().add(blueCmdBoard);
		
		basicRoot.getChildren().add(boardRoot);
		basicRoot.getChildren().add(cmdBoardRoot);
		
		//CardPart
		sceneRoot.getChildren().add(basicRoot);
		sceneRoot.getChildren().add(GameController.getCardPane());
		root.getChildren().add(sceneRoot);
		GameController.setGamePane(root);
		return new Scene(root);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static VBox drawUpRight() {
		VBox upRight = new VBox(10);
		upRight.setAlignment(Pos.CENTER);
		upRight.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));


		upRight.getChildren().add(GameController.getScorePane());
		upRight.getChildren().add(GameController.getHealthPane());
		upRight.getChildren().add(GameController.getPhasePane());

		DirectionPane directionPane = GameController.getDirectionPane();
		upRight.getChildren().add(directionPane);

		DraftedCard draftedCardsBox = GameController.getDraftedCard();
		upRight.getChildren().add(draftedCardsBox);
		return upRight;
	}
	
	public static Stage getWindow() {
		return window;
	}
	
}
