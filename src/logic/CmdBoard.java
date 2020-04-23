package logic;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import token.Mech;

public class CmdBoard extends HBox {
	private ArrayList<CmdBox> cmdBoxList = new ArrayList<CmdBox>();

	public CmdBoard(Mech programmedMech) {
		super(20);
		this.setPadding(new Insets(20));
		this.setPrefSize(1420, 280);
		for (int i = 0; i < 6; i++) {
			cmdBoxList.add(new CmdBox(programmedMech));
		}
		this.getChildren().addAll(cmdBoxList);

	}

	public void setSelectedCmdBox(CmdBox SelectedCmdBox) {
		for (CmdBox cmdBox : cmdBoxList) {
			if (cmdBox.equals(SelectedCmdBox)) {
				cmdBox.drawCanvas(true);
			} else {
				cmdBox.drawCanvas(false);
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
		this.cmdBoxList.get(slot).setCmdCardList(cmdBox.getCmdCardList());
	}

	public void draw() {
		for (CmdBox cmdBox : cmdBoxList) {
			cmdBox.drawCanvas(true);
		}
	}
}
