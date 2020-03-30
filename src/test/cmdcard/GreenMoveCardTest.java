package test.cmdcard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmdcard.BlueRotateCard;
import cmdcard.GreenMoveCard;
import exception.IndexOutOfRangeException;
import exception.SelectEmptyCardException;
import exception.SelectMechException;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import test.base.GameTest;
import token.Mech;
import token.Minion;

public class GreenMoveCardTest extends GameTest{
	GreenMoveCard gmc = new GreenMoveCard();
	Mech redMech;
	Mech blueMech;
	@BeforeEach
	public void setUpBeforeEachTest() {
		super.setUpBeforeEachTest();
		redMech = new Mech(Direction.RIGHT, GameController.getBoard().getTile(5, 5),0);
		blueMech = new Mech(Direction.LEFT,GameController.getBoard().getTile(9, 9),1);
		GameController.setRedMech(redMech);
		GameController.setDraftedCard(gmc);
		GameController.setBlueMech(blueMech);
	}
	@Test
	public void testInitialize() {
		assertEquals(CardSprite.GREEN_MOVE_CARD_1,gmc.getSpriteValue());
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
		assertEquals(3,((GreenMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).move(1).size());
		assertEquals(3,((GreenMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).move(2).size());
		assertEquals(3,((GreenMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).move(3).size());
	}
	@Test
	public void testExecute() {
		try {
			GameController.setProgram(0, 0, 0);
			GameController.setProgram(0, 0, 1);
		} catch (SelectEmptyCardException e) {
			System.out.println(e.message);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		} catch (SelectMechException e) {
			System.out.println(e.message);
		}
		Minion m1 = GameController.setMinion(5, 4);
		assertEquals(redMech,GameController.getBoard().getTile(5, 5).getToken());
		assertEquals(m1,GameController.getBoard().getTile(5, 4).getToken());
		GameController.execute(0);
		try {
			GameController.select(0);
			GameController.select(0);
			GameController.select(0);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		}
		assertEquals(null,GameController.getBoard().getTile(5, 5).getToken());
		assertEquals(redMech,GameController.getBoard().getTile(5, 3).getToken());
		assertEquals(m1,GameController.getBoard().getTile(5, 2).getToken());
	}
	@Test
	public void testSpriteValue() {
		gmc.setSpriteValue(1);
		assertEquals(CardSprite.GREEN_MOVE_CARD_1,gmc.getSpriteValue());
		gmc.setSpriteValue(2);
		assertEquals(CardSprite.GREEN_MOVE_CARD_2,gmc.getSpriteValue());
		gmc.setSpriteValue(3);
		assertEquals(CardSprite.GREEN_MOVE_CARD_3,gmc.getSpriteValue());
	}
}
