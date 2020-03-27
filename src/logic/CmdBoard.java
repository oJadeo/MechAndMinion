package logic;

import java.util.ArrayList;

public class CmdBoard {
	private ArrayList<CmdBox> cmdBoxList;
	private int startPositionX=0;
	private int startPositionY=480;
	public final int OFFSETX = 100;
	public final int OFFSETY = 120;
	
	public CmdBoard(int no) {
		for(int i =0;i<6;i++) {
		this.cmdBoxList.add(new CmdBox());
		}
		this.setStartPostionX(1000*no);
	}
	private void setStartPostionX(int startPositionX) {
		// TODO Auto-generated method stub
		this.startPositionX = startPositionX;
		
	}
	public CmdBox getCmdBox(int slot) {
		return this.getCmdBox(slot);
	}
	public void setCmdBox(int slot, CmdBox cmdBox) {
		this.cmdBoxList.set(slot, cmdBox);
	}
	public void Update() {
		
	}	
}
