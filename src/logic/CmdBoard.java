package logic;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class CmdBoard extends HBox {
	private ArrayList<CmdBox> cmdBoxList = new ArrayList<CmdBox>();

	public CmdBoard(int no) {
		super();
		this.setPrefSize(690, 200);
		this.setAlignment(Pos.CENTER);
		for (int i = 0; i < 6; i++) {
			cmdBoxList.add(new CmdBox(no));
			this.getChildren().add(cmdBoxList.get(i));
		}
		if (no == 0) {
			this.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
		} else {
			this.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		}
	}

	public void setSelectedCmdBox(CmdBox SelectedCmdBox) {
		for (CmdBox cmdBox : cmdBoxList) {
			if (cmdBox.equals(SelectedCmdBox)) {
				cmdBox.setGraphic(cmdBox.getCanvas(true));
			} else {
				cmdBox.setGraphic(cmdBox.getCanvas(false));
			}
		}
	}

	public void setDisableSlot(boolean disable) {
		for(CmdBox cmdBox:cmdBoxList) {
			cmdBox.setDisable(disable);
		}
	}

	public CmdBox getCmdBox(int slot) {
		return this.cmdBoxList.get(slot);
	}

	public void setCmdBox(int slot, CmdBox cmdBox) {
		this.cmdBoxList.set(slot, cmdBox);
	}

	public void draw() {
		for (CmdBox cmdBox : cmdBoxList) {
			cmdBox.setGraphic(cmdBox.getCanvas(true));
		}
	}
}
