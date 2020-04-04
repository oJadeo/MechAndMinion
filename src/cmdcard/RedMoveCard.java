package cmdcard;

import card.base.CmdCard;

import java.util.ArrayList;

import card.base.*;
import logic.*;
import tile.*;
import token.*;

public class RedMoveCard extends CmdCard implements Attack, Move, OnGoing {

	public RedMoveCard() {
		this.spriteValue = CardSprite.RED_MOVE_CARD_1;
	}

	public ArrayList<Object> move(int tier) {
		ArrayList<Object> resultList = new ArrayList<Object>();
		ArrayList<Tile> tileList = GameController.getBoard().getAdjacentTile(this.getProgrammedMech().getSelfTile(), 1,
				this.getProgrammedMech().getDirection());
		for (Tile e : tileList) {
			resultList.add((Object) e);
		}
		return resultList;
	}

	public ArrayList<Object> attack(int tier) {
		ArrayList<Object> resultList = new ArrayList<Object>();
		ArrayList<Tile> tileList = new ArrayList<Tile>();
		switch (this.getProgrammedMech().getDirection()) {
		case UP:
			tileList.addAll(GameController.getBoard().getAdjacentTile(this.getProgrammedMech().getSelfTile(), 1,
					Direction.RIGHT));
			tileList.addAll(GameController.getBoard().getAdjacentTile(this.getProgrammedMech().getSelfTile(), 1,
					Direction.LEFT));
			break;
		case DOWN:
			tileList.addAll(GameController.getBoard().getAdjacentTile(this.getProgrammedMech().getSelfTile(), 1,
					Direction.RIGHT));
			tileList.addAll(GameController.getBoard().getAdjacentTile(this.getProgrammedMech().getSelfTile(), 1,
					Direction.LEFT));
			break;
		case RIGHT:
			tileList.addAll(
					GameController.getBoard().getAdjacentTile(this.getProgrammedMech().getSelfTile(), 1, Direction.UP));
			tileList.addAll(GameController.getBoard().getAdjacentTile(this.getProgrammedMech().getSelfTile(), 1,
					Direction.DOWN));
			break;
		case LEFT:
			tileList.addAll(
					GameController.getBoard().getAdjacentTile(this.getProgrammedMech().getSelfTile(), 1, Direction.UP));
			tileList.addAll(GameController.getBoard().getAdjacentTile(this.getProgrammedMech().getSelfTile(), 1,
					Direction.DOWN));
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
		if (move(tier).size() != 0) {
			GameController.setSelectable(move(tier));
			GameController.setSelectTimes(tier);
		} else {
			GameController.setSelectTimes(0);
			GameController.setProgramCount(GameController.getProgramCount()+1);
			GameController.execute(GameController.getProgramCount());
		}
	}

	@Override
	public void setSpriteValue(int tier) {
		switch (tier) {
		case 1:
			this.spriteValue = CardSprite.RED_MOVE_CARD_1;
			break;
		case 2:
			this.spriteValue = CardSprite.RED_MOVE_CARD_2;
			break;
		case 3:
			this.spriteValue = CardSprite.RED_MOVE_CARD_3;
			break;

		}

	}

}
