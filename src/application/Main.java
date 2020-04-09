package application;

import card.base.CmdCard;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.CardSprite;
import logic.GameController;
import logic.TileSprite;

public class Main extends Application {
	
	static Button[] draftedBox = new Button[6];
	static Canvas[] draftedCardCanvas = new Canvas[6];
	Scene firstScene;
	Stage window;
	ImageView tu;
	int pageTuorial;
	
	@Override
	public void start(Stage primaryStage) {
		
		VBox menu = new VBox();
		menu.setSpacing(20);
		BackgroundImage myImage= new BackgroundImage(new Image("Wallpaper.jpg",1380,680,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		menu.setBackground(new Background(myImage));
		menu.setAlignment(Pos.CENTER);
		menu.getChildren().add(new ImageView(new Image("Logo.png")));
        
		VBox start = new VBox();
		start.setAlignment(Pos.CENTER);
		ImageView imageViewStart = new ImageView(new Image("Start.png"));
		start.getChildren().add(imageViewStart);
		menu.getChildren().add(start);
		
		VBox tutorial = new VBox();
		tutorial.setAlignment(Pos.CENTER);
		ImageView imageViewTutorial = new ImageView(new Image("Tutorial.png"));
		tutorial.getChildren().add(imageViewTutorial);
		menu.getChildren().add(tutorial);
		
		VBox exit = new VBox();
		exit.setAlignment(Pos.CENTER);
		ImageView imageViewExit = new ImageView(new Image("Exit.png"));
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
				// TODO Auto-generated method stub
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0.3,0);
			    imageViewStart.setEffect(colorAdjust);
			}
		});
		start.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0.3,0);
			    imageViewTutorial.setEffect(colorAdjust);
			}
		});
		tutorial.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0.3,0);
			    imageViewExit.setEffect(colorAdjust);
			}
		});
		exit.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0,0);
			    imageViewExit.setEffect(colorAdjust);
			}
		});
		
		firstScene = new Scene(menu,1380,680);
		window = primaryStage;
		primaryStage.setTitle("Mech and Minion");
		primaryStage.setScene(firstScene);
		primaryStage.show();

	}
	
	public Scene tutorialGame() {
		int minPage = 1;
		int maxPage = 7;
		VBox root = new VBox();
		BackgroundImage image= new BackgroundImage(new Image("Wallpaper.jpg",1380,680,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		root.setBackground(new Background(image));
		root.setSpacing(20);
		
		
		
		HBox upRoot = new HBox();
		VBox back = new VBox();
		back.setAlignment(Pos.TOP_LEFT);
		ImageView imageViewBack = new ImageView(new Image("Back.png"));
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
				// TODO Auto-generated method stub
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0.3,0);
			    imageViewBack.setEffect(colorAdjust);
			}
		});
		back.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
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
		ImageView imageViewArrowLeft = new ImageView();
		arrowLeft.getChildren().add(imageViewArrowLeft);
		lowRoot.getChildren().add(arrowLeft);
		VBox arrowRight = new VBox();
		ImageView  imageViewArrowRight = new ImageView(new Image("ArrowRight.png"));
		arrowRight.getChildren().add(imageViewArrowRight);
		lowRoot.getChildren().add(arrowRight);
		arrowRight.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if(pageTuorial < maxPage) {
					if(pageTuorial == maxPage-1) {
						imageViewArrowRight.setImage(null);
					}
					if(pageTuorial == minPage) {
						imageViewArrowLeft.setImage(new Image("ArrowLeft.png"));
					}
					pageTuorial += 1;
					tu = new ImageView(new Image("Page" + pageTuorial + ".png"));
					midRoot.getChildren().remove(0);
					midRoot.getChildren().add(tu);
				}
			}
		});
		arrowRight.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0.3,0);
			    imageViewArrowRight.setEffect(colorAdjust);
			}
		});
		arrowRight.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0,0);
				imageViewArrowRight.setEffect(colorAdjust);
			}
		});
		arrowLeft.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent arg0) {
				if(pageTuorial > minPage) {
					if(pageTuorial == minPage+1) {
						imageViewArrowLeft.setImage(null);
					}
					if(pageTuorial == maxPage) {
						imageViewArrowRight.setImage(new Image("ArrowRight.png"));
					}
					pageTuorial -= 1;
					tu = new ImageView(new Image("Page" + pageTuorial + ".png"));
					midRoot.getChildren().remove(0);
					midRoot.getChildren().add(tu);
				}
			}
		});
		arrowLeft.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ColorAdjust colorAdjust = new ColorAdjust(0,0,0.3,0);
				imageViewArrowLeft.setEffect(colorAdjust);
			}
		});
		arrowLeft.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
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
		Canvas boardCanvas = new Canvas(480, 480);
		GraphicsContext boardGC = boardCanvas.getGraphicsContext2D();
		GameController.getBoard().drawGameBoard(boardGC);
		boardRoot.getChildren().add(boardCanvas);
		boardRoot.getChildren().add(drawUpRight());

		// BottomHalf
		HBox cmdBoardRoot = new HBox();

		//Red Mech Command Board
		HBox redCmdBoard = new HBox();
		redCmdBoard.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		Canvas redCmdBoardCanvas = new Canvas(690, 200);
		GraphicsContext redCmdBoardGC = redCmdBoardCanvas.getGraphicsContext2D();
		drawRedCmdBoard(redCmdBoardGC);
		redCmdBoard.getChildren().add(redCmdBoardCanvas);
		cmdBoardRoot.getChildren().add(redCmdBoard);
		// Blue Mech Command Board
		HBox blueCmdBoard = new HBox();
		blueCmdBoard.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		Canvas blueCmdBoardCanvas = new Canvas(690, 200);
		GraphicsContext blueCmdBoardGC = blueCmdBoardCanvas.getGraphicsContext2D();
		drawBlueCmdBoard(blueCmdBoardGC);
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

	public static void drawPhase(GraphicsContext gc) {
		switch (GameController.getCurrentPhase()) {
		case Program:
			DrawUtil.drawPhase(gc, 75, 0, 8);
			break;
		case Execute:
			DrawUtil.drawPhase(gc, 225, 0, 8);
			break;
		case MinionMove:
			DrawUtil.drawPhase(gc, 375, 0, 8);
			break;
		case MinionAttack:
			DrawUtil.drawPhase(gc, 525, 0, 8);
			break;
		case MinionSpawn:
			DrawUtil.drawPhase(gc, 675, 0, 8);
			break;
		default:
			break;
		}
		for (int i = 0; i < 5; i++) {
			DrawUtil.drawPhase(gc, 150 * i + 75, 0, i);
		}
	}

	public static void drawDirection(GraphicsContext gc) {
		DrawUtil.drawPhase(gc, 250, 0, 7);
		for (int i = 0; i < 4; i++) {
			DrawUtil.drawTile(gc, (48 * i) + 400, 0, 14 + i);
		}
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

		HBox phase = new HBox();
		Canvas phaseCanvas = new Canvas(900, 60);
		GraphicsContext phaseGC = phaseCanvas.getGraphicsContext2D();
		drawPhase(phaseGC);
		phase.getChildren().add(phaseCanvas);
		upRight.getChildren().add(phase);

		HBox direction = new HBox();
		Canvas dirCanvas = new Canvas(750, 60);
		GraphicsContext dirGC = dirCanvas.getGraphicsContext2D();
		drawDirection(dirGC);
		direction.getChildren().add(dirCanvas);
		upRight.getChildren().add(direction);

		HBox draftedCardsBox = new HBox();
		draftedCardsBox.setPrefSize(900, 200);
		drawDraftedCard(draftedCardsBox);
		upRight.getChildren().add(draftedCardsBox);
		return upRight;
	}

	public static void drawRedCmdBoard(GraphicsContext gc) {
		for (int i = 0; i < 6; i++) {
			CmdCard cmdCard = GameController.getRedMech().getCmdBoard().getCmdBox(i).getCmdCardList()
					.get(GameController.getRedMech().getCmdBoard().getCmdBox(i).getCmdCardList().size() - 1);
			if (cmdCard != null) {
				// DrawUtil.drawCard(gc, i * 115, 4, cmdCard.getSpriteValue());
			} else {
				// DrawUtil.drawCard(gc, i * 115, 4, CardSprite.SELECTED_CARD);
			}
		}
	}

	public static void drawBlueCmdBoard(GraphicsContext gc) {
		for (int i = 0; i < 6; i++) {
			CmdCard cmdCard = GameController.getBlueMech().getCmdBoard().getCmdBox(i).getCmdCardList()
					.get(GameController.getBlueMech().getCmdBoard().getCmdBox(i).getCmdCardList().size() - 1);
			if (cmdCard != null) {
				// DrawUtil.drawCard(gc, i * 115, 4, cmdCard.getSpriteValue());
			} else {
				// DrawUtil.drawCard(gc, i * 115, 4, CardSprite.SELECTED_CARD);
			}
		}
	}

	public static void drawDraftedCard(HBox draftedCardsBox) {
		Canvas emptyCanvas = new Canvas(105, 200);
		draftedCardsBox.getChildren().add(emptyCanvas);
		for (int i = 0; i < 6; i++) {
			CmdCard cmdCard = GameController.getDraftedCard().getDraftedCardList().get(i);
			draftedBox[i] = new Button();
			draftedBox[i].setPadding(new Insets(0));
			draftedBox[i].setPrefSize(115, 192);
			draftedCardCanvas[i] = new Canvas(115, 192);
			GraphicsContext selectedCardGC = draftedCardCanvas[i].getGraphicsContext2D();
			if (cmdCard != null) {
				selectedCardGC.drawImage(DrawUtil.drawCard(cmdCard.getSpriteValue()), 0, 0);
				selectedCardGC.drawImage(DrawUtil.drawCard(CardSprite.SELECTED_CARD), 0, 0);
				draftedBox[i].setGraphic(draftedCardCanvas[i]);
			}
			draftedBox[i].setOnMouseClicked((e) -> {
				System.out.println("Click");
				GameController.setSelectedCard(cmdCard);
				setSelectedDraftedCard();
			});
		}
		draftedCardsBox.getChildren().addAll(draftedBox);

	}

	public static void setSelectedDraftedCard() {
		for (int i = 0; i < 6; i++) {
			CmdCard cmdCard = GameController.getDraftedCard().getDraftedCardList().get(i);
			GraphicsContext selectedCardGC = draftedCardCanvas[i].getGraphicsContext2D();
			if (cmdCard == GameController.getSelectedCard()) {
				selectedCardGC.drawImage(DrawUtil.drawCard(cmdCard.getSpriteValue()), 0, 0);
				selectedCardGC.drawImage(DrawUtil.drawCard(CardSprite.SELECTED_CARD), 0, 0);
				draftedBox[i].setGraphic(draftedCardCanvas[i]);
			}else {
				selectedCardGC.drawImage(DrawUtil.drawCard(cmdCard.getSpriteValue()), 0, 0);
				draftedBox[i].setGraphic(draftedCardCanvas[i]);
			}
		}
	}
}
