package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import application.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameController;

public class EndGamePane extends VBox {
	private GridPane scoreGridPane;
	private TextField nameInput;
	private ArrayList<Integer> scoreList = new ArrayList<Integer>();
	private ArrayList<Integer> topScoreList = new ArrayList<Integer>();
	private ArrayList<Integer> amountList = new ArrayList<Integer>();
	private ArrayList<Integer> sortedScoreList = new ArrayList<Integer>();
	private ArrayList<String> nameList = new ArrayList<String>();
	private ArrayList<String> topNameList = new ArrayList<String>();

	public EndGamePane() {
		super();
		this.setPrefSize(1920, 1080);
		this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setAlignment(Pos.CENTER);

		scoreGridPane = new GridPane();
		scoreGridPane.setAlignment(Pos.CENTER);
		scoreGridPane.setPrefSize(1920, 880);
		
		HBox buttonPane = new HBox();
		buttonPane.setAlignment(Pos.CENTER);
		buttonPane.setPrefSize(1920, 200);
		buttonPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		VBox exit = new VBox();
		exit.setAlignment(Pos.CENTER);
		ImageView imageViewExit = new ImageView(new Image(ClassLoader.getSystemResource("Exit.png").toString()));
		exit.getChildren().add(imageViewExit);
		exit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				MediaPlayer mediaPlayer = new MediaPlayer(
						new Media(ClassLoader.getSystemResource("click.mp3").toString()));
				mediaPlayer.setAutoPlay(true);
				mediaPlayer.setVolume(0.3);
				Main.getWindow().close();
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
		exit.setDisable(true);
		exit.setVisible(false);
		buttonPane.getChildren().add(exit);
		
		HBox headRankPane = new HBox();
		headRankPane.setPrefSize(100, 40);
		headRankPane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
				BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
				CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
		Label headrankText = new Label("Rank");
		headrankText.setFont(new Font(30));
		headRankPane.getChildren().add(headrankText);
		scoreGridPane.add(headRankPane, 0, 0);

		HBox headNamePane = new HBox();
		headNamePane.setPrefSize(600, 40);
		headNamePane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
				BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
				CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
		Label headNameText = new Label("Name");
		headNameText.setFont(new Font(30));
		headNamePane.getChildren().add(headNameText);
		scoreGridPane.add(headNamePane, 1, 0);

		HBox headScorePane = new HBox();
		headScorePane.setPrefSize(100, 40);
		headScorePane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
				BorderStrokeStyle.NONE, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.SOLID,
				CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
		Label headScoreText = new Label("Score");
		headScoreText.setFont(new Font(30));
		headScorePane.getChildren().add(headScoreText);
		scoreGridPane.add(headScorePane, 2, 0);

		StackPane nameInputPane = new StackPane();
		nameInput = new TextField();
		nameInput.setPromptText("Enter your name");
		nameInputPane.getChildren().add(nameInput);

		Label playerNameLabel = new Label();
		playerNameLabel.setFont(new Font(30));
		playerNameLabel.textProperty().bind(nameInput.textProperty());
		playerNameLabel.setVisible(false);
		scoreGridPane.add(playerNameLabel, 1, 11);

		nameInput.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				saveNewScore(nameInput.getText());
				playerNameLabel.setVisible(true);
				nameInput.setDisable(true);
				nameInput.setVisible(false);
				exit.setDisable(false);
				exit.setVisible(true);
			}
		});

		scoreGridPane.add(nameInput, 1, 11);

		try {
			getTopScore();
		} catch (FileNotFoundException e) {
			// TODO 
			System.out.println("Create score.txt");
		}
		int rank = getRank();
		setTopScore(rank);

		Label rankText = new Label(Integer.toString(rank + 1));
		rankText.setFont(new Font(30));
		scoreGridPane.add(rankText, 0, 11);

		Label scoreText = new Label(Integer.toString(GameController.getScore()));
		scoreText.setFont(new Font(30));
		scoreGridPane.add(scoreText, 2, 11);
		this.getChildren().add(scoreGridPane);

		this.getChildren().add(buttonPane);
	}

	public void getTopScore() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("score.txt"));
		while (scanner.hasNext()) {
			String read = scanner.next();
			try {
				int score = Integer.parseInt(read);
				scoreList.add(score);
			} catch (NumberFormatException nfe) {
				nameList.add(read);
			}
		}
		scanner.close();

		int amount = 0;
		while (true) {
			// find max score that less last one
			int amountTemp = 0;
			int scoreMax = 0;
			for (int score : scoreList) {
				if (topScoreList.size() == 0 || score < topScoreList.get(topScoreList.size() - 1)) {
					if (score > scoreMax) {
						scoreMax = score;
					}
				}
			}
			topScoreList.add(scoreMax);
			// check amount
			for (int score : scoreList) {
				if (score == scoreMax) {
					amountTemp++;
				}
			}
			amountList.add(amountTemp);
			amount += amountTemp;
			if (amount == scoreList.size() || amount >= 10) {
				break;
			}
		}

		for (int i = 0; i < amountList.size(); i++) {
			int amountTemp = amountList.get(i);
			for (int j = 0; j < scoreList.size(); j++) {
				if (scoreList.get(j) == topScoreList.get(i)) {
					topNameList.add(nameList.get(j));
					amountTemp--;
					if (amountTemp == 0) {
						break;
					}
				}
			}
		}
	}

	public int getRank() {
		for (int score : scoreList) {
			sortedScoreList.add(score);
		}
		Collections.sort(sortedScoreList, Collections.reverseOrder());
		for (int i = 0; i < sortedScoreList.size(); i++) {
			if (sortedScoreList.get(i) < GameController.getScore()) {
				return i;
			}
		}
		return sortedScoreList.size();
	}

	public void setTopScore(int rank) {
		if (rank < 10) {
			if (scoreList.size() < 10) {
				for (int i = 0; i < scoreList.size() + 1; i++) {
					if (i < rank) {
						Label rankLabel = new Label(Integer.toString(i + 1));
						rankLabel.setFont(new Font(30));
						scoreGridPane.add(rankLabel, 0, i + 1);
						HBox namePane = new HBox();
						namePane.setPrefSize(600, 40);
						namePane.setPadding(new Insets(8));
						namePane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK,
								Color.BLACK, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
								BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
						Label nameLabel = new Label(topNameList.get(i));
						nameLabel.setFont(new Font(30));
						namePane.getChildren().add(nameLabel);
						scoreGridPane.add(namePane, 1, i + 1);
						Label scoreLabel = new Label(Integer.toString(sortedScoreList.get(i)));
						scoreLabel.setFont(new Font(30));
						scoreGridPane.add(scoreLabel, 2, i + 1);
					} else if (i == rank) {
						Label rankLabel = new Label(Integer.toString(i + 1));
						rankLabel.setFont(new Font(30));
						scoreGridPane.add(rankLabel, 0, i + 1);
						HBox namePane = new HBox();
						namePane.setPrefSize(600, 40);
						namePane.setPadding(new Insets(8));
						namePane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK,
								Color.BLACK, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
								BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
						Label playerNameLabel = new Label();
						playerNameLabel.setFont(new Font(30));
						playerNameLabel.textProperty().bind(nameInput.textProperty());
						namePane.getChildren().add(playerNameLabel);
						scoreGridPane.add(namePane, 1, i + 1);
						Label scoreLabel = new Label(Integer.toString(GameController.getScore()));
						scoreLabel.setFont(new Font(30));
						scoreGridPane.add(scoreLabel, 2, i + 1);
					} else if (i > rank) {
						Label rankLabel = new Label(Integer.toString(i + 1));
						rankLabel.setFont(new Font(30));
						scoreGridPane.add(rankLabel, 0, i + 1);
						HBox namePane = new HBox();
						namePane.setPrefSize(600, 40);
						namePane.setPadding(new Insets(8));
						namePane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK,
								Color.BLACK, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
								BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
						Label nameLabel = new Label(topNameList.get(i - 1));
						nameLabel.setFont(new Font(30));
						namePane.getChildren().add(nameLabel);
						scoreGridPane.add(namePane, 1, i + 1);
						Label scoreLabel = new Label(Integer.toString(sortedScoreList.get(i - 1)));
						scoreLabel.setFont(new Font(30));
						scoreGridPane.add(scoreLabel, 2, i + 1);
					}
				}
				for (int i = 0; i < 10 - (scoreList.size() + 1); i++) {
					HBox namePane = new HBox();
					namePane.setPrefSize(600, 40);
					namePane.setPadding(new Insets(8));
					namePane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK,
							BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
							BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
					scoreGridPane.add(namePane, 1, i + scoreList.size() + 1);
				}
			} else {
				for (int i = 0; i < 10; i++) {
					if (i < rank) {
						Label rankLabel = new Label(Integer.toString(i + 1));
						rankLabel.setFont(new Font(30));
						scoreGridPane.add(rankLabel, 0, i + 1);
						HBox namePane = new HBox();
						namePane.setPrefSize(600, 40);
						namePane.setPadding(new Insets(8));
						namePane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK,
								Color.BLACK, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
								BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
						Label nameLabel = new Label(topNameList.get(i));
						nameLabel.setFont(new Font(30));
						namePane.getChildren().add(nameLabel);
						scoreGridPane.add(namePane, 1, i + 1);
						Label scoreLabel = new Label(Integer.toString(sortedScoreList.get(i)));
						scoreLabel.setFont(new Font(30));
						scoreGridPane.add(scoreLabel, 2, i + 1);
					} else if (i == rank) {
						Label rankLabel = new Label(Integer.toString(i + 1));
						rankLabel.setFont(new Font(30));
						scoreGridPane.add(rankLabel, 0, i + 1);
						HBox namePane = new HBox();
						namePane.setPrefSize(600, 40);
						namePane.setPadding(new Insets(8));
						namePane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK,
								Color.BLACK, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
								BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
						Label playerNameLabel = new Label();
						playerNameLabel.setFont(new Font(30));
						playerNameLabel.textProperty().bind(nameInput.textProperty());
						namePane.getChildren().add(playerNameLabel);
						scoreGridPane.add(namePane, 1, i + 1);
						Label scoreLabel = new Label(Integer.toString(GameController.getScore()));
						scoreLabel.setFont(new Font(30));
						scoreGridPane.add(scoreLabel, 2, i + 1);
					} else if (i > rank) {
						Label rankLabel = new Label(Integer.toString(i + 1));
						rankLabel.setFont(new Font(30));
						scoreGridPane.add(rankLabel, 0, i + 1);
						HBox namePane = new HBox();
						namePane.setPrefSize(600, 40);
						namePane.setPadding(new Insets(8));
						namePane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK,
								Color.BLACK, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
								BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
						Label nameLabel = new Label(topNameList.get(i - 1));
						nameLabel.setFont(new Font(30));
						namePane.getChildren().add(nameLabel);
						scoreGridPane.add(namePane, 1, i + 1);
						Label scoreLabel = new Label(Integer.toString(sortedScoreList.get(i - 1)));
						scoreLabel.setFont(new Font(30));
						scoreGridPane.add(scoreLabel, 2, i + 1);
					}
				}
			}
		} else {
			int order = 1;
			for (int i = 0; i < amountList.size(); i++) {
				for (int j = 0; j < amountList.get(i); j++) {
					if (order < 11) {
						Label rankLabel = new Label(Integer.toString(order));
						rankLabel.setFont(new Font(30));
						scoreGridPane.add(rankLabel, 0, order);
						HBox namePane = new HBox();
						namePane.setPrefSize(600, 40);
						namePane.setPadding(new Insets(8));
						namePane.setBorder(new Border(new BorderStroke(Color.BLACK, Color.BLACK, Color.BLACK,
								Color.BLACK, BorderStrokeStyle.NONE, BorderStrokeStyle.SOLID, BorderStrokeStyle.NONE,
								BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1), Insets.EMPTY)));
						Label nameLabel = new Label(topNameList.get(order - 1));
						nameLabel.setFont(new Font(30));
						namePane.getChildren().add(nameLabel);
						scoreGridPane.add(namePane, 1, order);
						Label scoreLabel = new Label(Integer.toString(topScoreList.get(i)));
						scoreLabel.setFont(new Font(30));
						scoreGridPane.add(scoreLabel, 2, order);
						order++;
					}
				}
			}
		}

	}

	public void saveNewScore(String Name) {
		try {
			FileWriter writer = new FileWriter("score.txt", true);
			writer.write(Name.trim() + "\n");
			writer.write(GameController.getScore() + "\n");
			writer.close();
		} catch (IOException e) {
			System.out.println("IOExceoption in saveNewScore");
		}
	}
}
