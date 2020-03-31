package test.cmdcard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmdcard.RedMoveCard;
import cmdcard.YellowMoveCard;
import exception.IndexOutOfRangeException;
import exception.SelectEmptyCardException;
import exception.SelectMechException;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import test.base.GameTest;
import token.Mech;

public class YellowMoveCardTest extends GameTest{
	YellowMoveCard ymc = new YellowMoveCard();
	Mech redMech;
	Mech blueMech;
	@BeforeEach
	public void setBeforeEachTest() {
		super.setUpBeforeEachTest();
		redMech = new Mech(Direction.RIGHT, GameController.getBoard().getTile(5, 5),0);
		blueMech = new Mech(Direction.LEFT,GameController.getBoard().getTile(9, 9),1);
		GameController.setRedMech(redMech);
		GameController.setDraftedCard(ymc);
		GameController.setBlueMech(blueMech);
	}
	@Test
	public void testInitialize() {
		assertEquals(CardSprite.YELLOW_MOVE_CARD_1,ymc.getSpriteValue());
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
		assertEquals(GameController.getBoard().getTile(6, 5),((YellowMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).move(1).get(0));
		assertEquals(1,((YellowMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).move(1).size());
		redMech.setDirection(Direction.LEFT);
		assertEquals(GameController.getBoard().getTile(4, 5),((YellowMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).move(1).get(0));
		assertEquals(1,((YellowMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).move(1).size());
		redMech.setDirection(Direction.UP);
		assertEquals(GameController.getBoard().getTile(5, 4),((YellowMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).move(1).get(0));
		assertEquals(1,((YellowMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).move(1).size());
		redMech.setDirection(Direction.DOWN);
		assertEquals(GameController.getBoard().getTile(5, 6),((YellowMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).move(1).get(0));
		assertEquals(1,((YellowMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).move(1).size());
		
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
		GameController.execute(0);
		try {
			GameController.select(0);
			GameController.select(0);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		}
		assertEquals(redMech,GameController.getBoard().getTile(7, 5).getToken());
		GameController.setProgramCount(0);
		try {
			GameController.setProgram(0, 0, 1);
		} catch (SelectEmptyCardException e) {
			System.out.println(e.message);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		} catch (SelectMechException e) {
			System.out.println(e.message);
		}
		redMech.setDirection(Direction.UP);
		GameController.execute(0);
		try {
			GameController.select(0);
			GameController.select(0);
			GameController.select(0);
			GameController.select(1);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		}
		assertEquals(redMech,GameController.getBoard().getTile(7, 2).getToken());
		assertEquals(0,GameController.getSelectTimes());
	}
	@Test
	public void testSpriteValue() {
		ymc.setSpriteValue(1);
		assertEquals(CardSprite.YELLOW_MOVE_CARD_1,ymc.getSpriteValue());
		ymc.setSpriteValue(2);
		assertEquals(CardSprite.YELLOW_MOVE_CARD_1,ymc.getSpriteValue());
		ymc.setSpriteValue(3);
		assertEquals(CardSprite.YELLOW_MOVE_CARD_1,ymc.getSpriteValue());
	}
}
