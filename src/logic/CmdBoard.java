package logic;

import java.util.ArrayList;

import card.base.CmdCard;
import cmdcard.BlueAttackCard;
import cmdcard.BlueMoveCard;
import cmdcard.BlueRotateCard;
import cmdcard.GreenAttackCard;
import cmdcard.GreenMoveCard;
import cmdcard.GreenRotateCard;
import cmdcard.RedAttackCard;
import cmdcard.RedMoveCard;
import cmdcard.RedRotateCard;
import cmdcard.YellowAttackCard;
import cmdcard.YellowMoveCard;
import cmdcard.YellowRotateCard;

public class CmdBoard {
	private ArrayList<CmdBox> cmdBoxList;
	private int startPositionX=0;
	private int startPositionY=480;
	private int no;
	public final int OFFSETX = 100;
	public final int OFFSETY = 120;
	
	public CmdBoard(int no) {
		this.cmdBoxList = new ArrayList<CmdBox>();
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
		return this.cmdBoxList.get(slot);
	}
	public void setCmdBox(int slot, CmdBox cmdBox) {
		this.cmdBoxList.set(slot, cmdBox);
	}
	public void update() {
		String result = no+" Mech : [";
		for(CmdBox cmdBox:cmdBoxList) {
			CmdCard card = cmdBox.getCmdCardList().get(cmdBox.getCmdCardList().size()-1);
			if(card instanceof BlueAttackCard) {
				result += " BA ";
			}else if(card instanceof BlueMoveCard) {
				result += " BM ";
			}else if(card instanceof BlueRotateCard) {
				result += " BR ";
			}else if(card instanceof RedAttackCard) {
				result += " RA ";
			}else if(card instanceof RedRotateCard) {
				result += " RR ";
			}else if(card instanceof RedMoveCard) {
				result += " RM ";
			}else if(card instanceof GreenAttackCard) {
				result += " GA ";
			}else if(card instanceof GreenMoveCard) {
				result += " GM ";
			}else if(card instanceof GreenRotateCard) {
				result += " GR ";
			}else if(card instanceof YellowAttackCard) {
				result += " YA ";
			}else if(card instanceof YellowMoveCard) {
				result += " YM ";
			}else if(card instanceof YellowRotateCard) {
				result += " YR ";
			}else if(card == null) {
				result += " [] ";
			}
		}
		result += "]";
		System.out.println(result);
	}	
}
