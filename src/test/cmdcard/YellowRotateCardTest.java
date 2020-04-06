package test.cmdcard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmdcard.RedRotateCard;
import cmdcard.YellowRotateCard;
import exception.IndexOutOfRangeException;
import exception.SelectEmptyCardException;
import exception.SelectMechException;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import test.base.GameTest;
import token.Mech;
import token.Minion;

public class YellowRotateCardTest extends GameTest {
	YellowRotateCard yrc = new YellowRotateCard();
	Mech redMech;
	Mech blueMech;
	@BeforeEach
	public void setBeforeEachTest() {
		super.setUpBeforeEachTest();
		redMech = new Mech(Direction.RIGHT, GameController.getBoard().getTile(5, 5),0);
		blueMech = new Mech(Direction.LEFT,GameController.getBoard().getTile(9, 9),1);
		GameController.setRedMech(redMech);
		GameController.setDraftedCard(yrc);
		GameController.setBlueMech(blueMech);
	}
	@Test
	public void testInitialize() {
		assertEquals(CardSprite.YELLOW_ROTATE_CARD_1,yrc.getSpriteValue());
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
		assertEquals(2,((YellowRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).rotate(1).size());
		assertEquals(3,((YellowRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).rotate(2).size());
		assertEquals(4,((YellowRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).rotate(3).size());
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
		GameController.setMinion(6, 6);
		GameController.setMinion(4, 4);
		GameController.setMinion(4, 6);
		GameController.setMinion(6, 4);
		GameController.setMinion(5, 6);
		GameController.setMinion(7, 7);
		GameController.setMinion(3, 3);
		GameController.setMinion(7, 3);
		assertEquals(4,((YellowRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(1).size());
		assertEquals(7,((YellowRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(2).size());
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
		Minion m1 = GameController.setMinion(6, 6);
		Minion m2 = GameController.setMinion(4, 4);
		assertEquals(m2,GameController.getBoard().getTile(4, 4).getToken());
		assertEquals(m1,GameController.getBoard().getTile(6, 6).getToken());
		GameController.execute(0);
		try {
			GameController.select(0);
			GameController.select(0);
			GameController.select(0);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		}
		assertEquals(null,GameController.getBoard().getTile(4, 4).getToken());
		assertEquals(null,GameController.getBoard().getTile(6, 6).getToken());
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
		Minion m3 = GameController.setMinion(7, 7);
		Minion m4 = GameController.setMinion(3, 3);
		GameController.execute(0);
		try {
			GameController.select(0);
			GameController.select(0);
			GameController.select(0);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		}
		assertEquals(null,GameController.getBoard().getTile(7, 7).getToken());
		assertEquals(null,GameController.getBoard().getTile(3, 3).getToken());
	}
	@Test
	public void testSpriteValue() {
		yrc.setSpriteValue(1);
		assertEquals(CardSprite.YELLOW_ROTATE_CARD_1,yrc.getSpriteValue());
		yrc.setSpriteValue(2);
		assertEquals(CardSprite.YELLOW_ROTATE_CARD_2,yrc.getSpriteValue());
		yrc.setSpriteValue(3);
		assertEquals(CardSprite.YELLOW_ROTATE_CARD_3,yrc.getSpriteValue());
	}
	
}
