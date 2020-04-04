package cmdcard;

import card.base.*;
import logic.*;
import tile.*;
import token.*;

import java.util.ArrayList;

import card.base.Attack;

public class RedAttackCard extends CmdCard implements Attack, OnGoing {

	public RedAttackCard() {
		this.spriteValue = CardSprite.RED_ATTACK_CARD_1;
	}

	@Override
	public ArrayList<Object> attack(int tier) {
		ArrayList<Object> resultList = new ArrayList<Object>();
		Mech mech = this.getProgrammedMech();
		int x = mech.getSelfTile().getLocationX();
		int y = mech.getSelfTile().getLocationY();
		Direction dir = mech.getDirection();
		ArrayList<Tile> tileList = new ArrayList<>();
		switch (tier) {
		case 1:
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(), 2, dir));
			break;
		case 2:
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(), 2, dir));
			tileList.addAll(GameController.getBoard().getDiagonalTile(mech.getSelfTile(), 2, dir));
			break;
		case 3:
			tileList.addAll(GameController.getBoard().getAdjacentTile(mech.getSelfTile(), 2, dir));
			tileList.addAll(GameController.getBoard().getDiagonalTile(mech.getSelfTile(), 2, dir));
			switch (dir) {
			case UP:
				if (GameController.getBoard().isMovePossible(x - 1, y - 2)) {
					tileList.add(GameController.getBoard().getTile(x - 1, y - 2));
				}
				if (GameController.getBoard().isMovePossible(x + 1, y - 2)) {
					tileList.add(GameController.getBoard().getTile(x + 1, y - 2));
				}
				break;
			case DOWN:
				if (GameController.getBoard().isMovePossible(x - 1, y + 2)) {
					tileList.add(GameController.getBoard().getTile(x - 1, y + 2));
				}
				if (GameController.getBoard().isMovePossible(x + 1, y + 2)) {
					tileList.add(GameController.getBoard().getTile(x + 1, y + 2));
				}
				break;
			case RIGHT:
				if (GameController.getBoard().isMovePossible(x + 2, y - 1)) {
					tileList.add(GameController.getBoard().getTile(x + 2, y - 1));
				}
				if (GameController.getBoard().isMovePossible(x + 2, y + 1)) {
					tileList.add(GameController.getBoard().getTile(x + 2, y + 1));
				}
				break;
			case LEFT:
				if (GameController.getBoard().isMovePossible(x - 2, y - 1)) {
					tileList.add(GameController.getBoard().getTile(x - 2, y - 1));
				}
				if (GameController.getBoard().isMovePossible(x - 2, y + 1)) {
					tileList.add(GameController.getBoard().getTile(x - 2, y + 1));
				}
			default:
				break;
			}
			break;
		default:
			break;
		}
		for (Tile tile : tileList) {
			if (tile.getToken() instanceof Token && !tile.getToken().equals(this.getProgrammedMech())) {
				resultList.add((Object) tile.getToken());
			}
		}
		return resultList;

	}

	@Override
	public void execute(int tier) {
		// TODO Auto-generated method stub
		GameController.setSelectable(attack(tier));
		GameController.setSelectTimes(attack(tier).size());
		if(attack(tier).size()==0) {
			GameController.setProgramCount(GameController.getProgramCount()+1);
			GameController.execute(GameController.getProgramCount());
		}
	}

	@Override
	public void setSpriteValue(int tier) {
		switch (tier) {
		case 1:
			this.spriteValue = CardSprite.RED_ATTACK_CARD_1;
			break;
		case 2:
			this.spriteValue = CardSprite.RED_ATTACK_CARD_2;
			break;
		case 3:
			this.spriteValue = CardSprite.RED_ATTACK_CARD_3;
			break;

		}
	}
}
