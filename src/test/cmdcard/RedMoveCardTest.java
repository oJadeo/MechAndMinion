package test.cmdcard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import cmdcard.RedMoveCard;
import exception.IndexOutOfRangeException;
import exception.SelectEmptyCardException;
import exception.SelectMechException;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import test.base.GameTest;
import token.Mech;

public class RedMoveCardTest extends GameTest{
	RedMoveCard rmc = new RedMoveCard();
	Mech redMech;
	Mech blueMech;
	@BeforeEach
	public void setBeforeEachTest() {
		super.setUpBeforeEachTest();
		redMech = new Mech(Direction.RIGHT, GameController.getBoard().getTile(5, 5),0);
		blueMech = new Mech(Direction.LEFT,GameController.getBoard().getTile(9, 9),1);
		GameController.setRedMech(redMech);
		GameController.setDraftedCard(rmc);
		GameController.setBlueMech(blueMech);
	}
	@Test
	public void testInitialize() {
		assertEquals(CardSprite.RED_MOVE_CARD_1,rmc.getSpriteValue());
	}
	@Test
	public void testMove() {
		try {
			GameController.setProgram(0, 0, 0);
		} catch (SelectEmptyCardException e) {
			System.out.println(e.message);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		} catch (SelectMechException e) {
			System.out.println(e.message);
		}
		assertEquals(GameController.getBoard().getTile(6, 5),((RedMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).move(1).get(0));
		assertEquals(1,((RedMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).move(1).size());
	}
	@Test
	public void testAttack() {
		try {
			GameController.setProgram(0, 0, 0);
		} catch (SelectEmptyCardException e) {
			System.out.println(e.message);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		} catch (SelectMechException e) {
			System.out.println(e.message);
		}
		GameController.setMinion(5, 6);
		assertEquals(1,((RedMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(1).size());
		GameController.setMinion(5, 4);
		assertEquals(2,((RedMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(2).size());
		redMech.setDirection(Direction.LEFT);
		assertEquals(2,((RedMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(3).size());
		redMech.setDirection(Direction.UP);
		assertEquals(0,((RedMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(1).size());
		redMech.setDirection(Direction.DOWN);
		assertEquals(0,((RedMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(2).size());
	}
	@Test
	public void testExecute() {
		try {
			GameController.setProgram(0, 0, 0);
		} catch (SelectEmptyCardException e) {
			System.out.println(e.message);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		} catch (SelectMechException e) {
			System.out.println(e.message);
		}
		GameController.setMinion(6, 6);
		GameController.setMinion(6, 4);
		GameController.execute(0);
		try {
			GameController.select(0);
			GameController.select(0);
			GameController.select(0);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		}
		assertEquals(null,GameController.getBoard().getTile(6, 6).getToken());
		assertEquals(null,GameController.getBoard().getTile(6, 4).getToken());
		try {
			GameController.setProgram(0, 0, 1);
		} catch (SelectEmptyCardException e) {
			System.out.println(e.message);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		} catch (SelectMechException e) {
			System.out.println(e.message);
		}
		GameController.setMinion(8, 6);
		GameController.setMinion(8, 4);
		GameController.setProgramCount(0);
		GameController.execute(0);
		try {
			GameController.select(0);
			GameController.select(0);
			GameController.select(0);
			GameController.select(0);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		}
		assertEquals(null,GameController.getBoard().getTile(8, 6).getToken());
		assertEquals(null,GameController.getBoard().getTile(8, 4).getToken());
	}
	@Test
	public void testSpriteValue() {
		rmc.setSpriteValue(1);
		assertEquals(CardSprite.RED_MOVE_CARD_1,rmc.getSpriteValue());
		rmc.setSpriteValue(2);
		assertEquals(CardSprite.RED_MOVE_CARD_2,rmc.getSpriteValue());
		rmc.setSpriteValue(3);
		assertEquals(CardSprite.RED_MOVE_CARD_3,rmc.getSpriteValue());
	}
}
