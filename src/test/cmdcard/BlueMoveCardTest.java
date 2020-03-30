package test.cmdcard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmdcard.BlueMoveCard;
import exception.IndexOutOfRangeException;
import exception.SelectEmptyCardException;
import exception.SelectMechException;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import test.base.GameTest;
import token.Mech;
import token.Minion;

public class BlueMoveCardTest extends GameTest{
	BlueMoveCard bmc = new BlueMoveCard();
	Mech redMech;
	Mech blueMech;
	@BeforeEach
	public void setUpBeforeEachTest() {
		super.setUpBeforeEachTest();
		redMech = new Mech(Direction.RIGHT, GameController.getBoard().getTile(0, 0),0);
		blueMech = new Mech(Direction.LEFT,GameController.getBoard().getTile(5, 0),1);
		GameController.setRedMech(redMech);
		GameController.setDraftedCard(bmc);
		GameController.setBlueMech(blueMech);
	}
	@Test
	public void testInitialize() {
		assertEquals(CardSprite.BLUE_MOVE_CARD_1,bmc.getSpriteValue());
	}
	@Test
	public void testExecute() {
		try {
			GameController.setProgram(0,0,0);
			GameController.setProgram(0,1,1);
		} catch (SelectEmptyCardException e) {
			System.out.println(e.message);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		} catch (SelectMechException e) {
			System.out.println(e.message);
		}
		Minion m1 = GameController.setMinion(1, 0);
		Minion m2 = GameController.setMinion(2, 0);
		GameController.execute(0);
		assertEquals(m1,GameController.getSelectable().get(0));
		try {
			GameController.select(0);
			GameController.select(0);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		}
		assertEquals(redMech,GameController.getBoard().getTile(1, 0).getToken());
		GameController.execute(1);
		assertEquals(m2,GameController.getSelectable().get(0));
		try {
			GameController.select(0);
			GameController.select(0);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		}
		assertEquals(redMech,GameController.getBoard().getTile(2, 0).getToken());
	}
	@Test
	public void testAttack() {
		try {
			GameController.setProgram(0, 0, 0);
			GameController.setProgram(0, 1, 1);
		} catch (SelectEmptyCardException e) {
			System.out.println(e.message);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		} catch (SelectMechException e) {
			System.out.println(e.message);
		}
		Minion m1 = GameController.setMinion(1, 0);
		assertEquals(m1,((BlueMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(1).get(0));
		GameController.execute(0);
		try {
			GameController.select(0);
			GameController.select(0);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		}
		assertEquals(0,((BlueMoveCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(1).size());
	}
	@Test
	public void testSpriteValue() {
		bmc.setSpriteValue(1);
		assertEquals(CardSprite.BLUE_MOVE_CARD_1,bmc.getSpriteValue());
		bmc.setSpriteValue(2);
		assertEquals(CardSprite.BLUE_MOVE_CARD_2,bmc.getSpriteValue());
		bmc.setSpriteValue(3);
		assertEquals(CardSprite.BLUE_MOVE_CARD_3,bmc.getSpriteValue());
	}
	
	
	
}
