package logic;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import token.Mech;

public class CmdBoard extends HBox {
	private ArrayList<CmdBox> cmdBoxList = new ArrayList<CmdBox>();

	public CmdBoard(Mech programmedMech) {
		super();
		this.setPrefSize(690, 200);
		this.setAlignment(Pos.CENTER);
		for (int i = 0; i < 6; i++) {
			cmdBoxList.add(new CmdBox(programmedMech));
		}
		this.getChildren().addAll(cmdBoxList);

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
		for (CmdBox cmdBox : cmdBoxList) {
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
