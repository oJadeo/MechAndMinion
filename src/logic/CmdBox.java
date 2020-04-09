package logic;

import java.util.ArrayList;

import application.DrawUtil;
import card.base.*;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

public class CmdBox extends Button {

	private ArrayList<CmdCard> cmdCardList;
	private Canvas cmdCanvas;
	int no = 0;

	public CmdBox(int no) {
		super();

		cmdCanvas = new Canvas(115, 192);
		this.cmdCardList = new ArrayList<CmdCard>();
		this.cmdCardList.add(null);

		this.setPadding(new Insets(0));
		this.setPrefSize(115, 192);
		this.setGraphic(getCanvas(true));
		if (no == 0) {
			this.setOnMouseClicked((e) -> {
				if (GameController.getCurrentPhase() == Phase.Program) {
					GameController.setSelectedSlot(GameController.getRedMech(), this);
				}
			});
		} else {
			this.setOnMouseClicked((e) -> {
				if (GameController.getCurrentPhase() == Phase.Program) {
					GameController.setSelectedSlot(GameController.getBlueMech(), this);
				}
			});
		}

	}

	public void addCmdCard(CmdCard selectedCard) {
		if (this.cmdCardList.get(this.cmdCardList.size() - 1) == null) {
			this.cmdCardList.clear();
			this.cmdCardList.add(selectedCard);
		} else if (this.cmdCardList.get(this.cmdCardList.size() - 1).getCardType().equals(selectedCard.getCardType())) {
			switch (this.cmdCardList.size()) {
			case 0:
				this.cmdCardList.add(selectedCard);
				selectedCard.setSpriteValue(1);
				break;
			case 1:
				this.cmdCardList.add(selectedCard);
				selectedCard.setSpriteValue(2);
				break;
			case 2:
				this.cmdCardList.add(selectedCard);
				selectedCard.setSpriteValue(3);
				break;
			case 3:
				this.cmdCardList.set(2, selectedCard);
				selectedCard.setSpriteValue(3);
				break;
			}
		} else {
			cmdCardList.clear();
			cmdCardList.add(selectedCard);
		}
		selectedCard.setCmdBox(this);
	}

	public void addDamageCard(CmdCard damageCard) {
		this.cmdCardList.clear();
		this.cmdCardList.add(damageCard);
		damageCard.setCmdBox(this);
	}

	public Canvas getCanvas(boolean selected) {
		GraphicsContext cmdGC = this.cmdCanvas.getGraphicsContext2D();
		cmdGC.clearRect(0, 0, 115, 192);
		if (this.cmdCardList.get(this.cmdCardList.size() - 1) != null) {
			cmdGC.drawImage(DrawUtil.drawCard(this.cmdCardList.get(this.cmdCardList.size() - 1).getSpriteValue()), 0,
					0);
		}
		if (selected) {
			cmdGC.drawImage(DrawUtil.drawCard(CardSprite.SELECTED_CARD), 0, 0);
		}
		return cmdCanvas;
	}

	public void execute() {
		this.setDisable(false);
		GameController.getRedMech().getCmdBoard().setSelectedCmdBox(this);
		GameController.getBlueMech().getCmdBoard().setSelectedCmdBox(this);
		if (this.cmdCardList.get(this.cmdCardList.size() - 1) == null) {
			GameController.setProgramCount(GameController.getProgramCount() + 1);
			GameController.execute(GameController.getProgramCount());
		} else {
			GameController.setExecutingProgram(this.cmdCardList.get(this.cmdCardList.size() - 1));
			((OnGoing) this.cmdCardList.get(this.cmdCardList.size() - 1)).execute(this.cmdCardList.size());
		}
	}

	public void setCmdCardList(ArrayList<CmdCard> cmdCardList) {
		this.cmdCardList = cmdCardList;
	}

	public ArrayList<CmdCard> getCmdCardList() {
		return this.cmdCardList;
	}
}
