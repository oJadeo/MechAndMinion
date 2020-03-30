package test.cmdcard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmdcard.GreenAttackCard;
import cmdcard.GreenRotateCard;
import exception.IndexOutOfRangeException;
import exception.SelectEmptyCardException;
import exception.SelectMechException;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import test.base.GameTest;
import token.Mech;

public class GreenRotateCardTest extends GameTest{
	GreenRotateCard grc = new GreenRotateCard();
	Mech redMech;
	Mech blueMech;
	@BeforeEach
	public void setBeforeEachTest() {
		super.setUpBeforeEachTest();
		redMech = new Mech(Direction.RIGHT, GameController.getBoard().getTile(5, 5),0);
		blueMech = new Mech(Direction.LEFT,GameController.getBoard().getTile(9, 9),1);
		GameController.setRedMech(redMech);
		GameController.setDraftedCard(grc);
		GameController.setBlueMech(blueMech);
	}
	@Test
	public void testInitialize() {
		assertEquals(CardSprite.GREEN_ROTATE_CARD_1,grc.getSpriteValue());
	}
	@Test
	public void testRotate() {
		try {
			GameController.setProgram(0, 0, 0);
		} catch (SelectEmptyCardException e) {
			System.out.println(e.message);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		} catch (SelectMechException e) {
			System.out.println(e.message);
		}
		assertEquals(2,((GreenRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).rotate(1).size());
		assertEquals(3,((GreenRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).rotate(2).size());
		assertEquals(4,((GreenRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).rotate(3).size());
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
		GameController.setMinion(4, 4);
		GameController.setMinion(5, 3);
		GameController.setMinion(5, 6);
		GameController.setMinion(5, 8);
		assertEquals(1,((GreenRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(1).size());
		assertEquals(2,((GreenRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(2).size());
		assertEquals(3,((GreenRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(3).size());
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
		GameController.setMinion(4, 4);
		GameController.setMinion(5, 3);
		
		GameController.execute(0);
		try {
			GameController.select(0);
			GameController.select(0);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		}
		assertEquals(null,GameController.getBoard().getTile(5, 3).getToken());
		
	}
	@Test
	public void testSpriteValue() {
		grc.setSpriteValue(1);
		assertEquals(CardSprite.GREEN_ROTATE_CARD_1,grc.getSpriteValue());
		grc.setSpriteValue(2);
		assertEquals(CardSprite.GREEN_ROTATE_CARD_2,grc.getSpriteValue());
		grc.setSpriteValue(3);
		assertEquals(CardSprite.GREEN_ROTATE_CARD_3,grc.getSpriteValue());
	}
}
