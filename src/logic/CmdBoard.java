package logic;

import java.util.ArrayList;

public class CmdBoard {
	private ArrayList<CmdBox> cmdBoxList;
	private int startPositionX=0;
	private int startPositionY=480;
	public final int OFFSETX = 100;
	public final int OFFSETY = 120;
	
	public CmdBoard(int no) {
		this.cmdBoxList = new ArrayList<CmdBox>();
		this.cmdBoxList.add(new CmdBox());
		CmdBox cmdbox2 = new CmdBox(); 
		this.cmdBoxList.add(cmdbox2);
		CmdBox cmdbox3 = new CmdBox(); 
		this.cmdBoxList.add(cmdbox3);
		CmdBox cmdbox4 = new CmdBox(); 
		this.cmdBoxList.add(cmdbox4);
		CmdBox cmdbox5 = new CmdBox(); 
		this.cmdBoxList.add(cmdbox5);
		CmdBox cmdbox6 = new CmdBox(); 
		this.cmdBoxList.add(cmdbox6);
		this.setStartPostionX(1000*no);
	}
	private void setStartPostionX(int startPositionX) {
		// TODO Auto-generated method stub
		this.startPositionX = startPositionX;
		
	}
	public CmdBox getCmdBox(int slot) {
		return this.getCmdBox(slot);
	}
	public void Update() {
		
	}	
}
