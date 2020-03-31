package test.cmdcard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmdcard.RedAttackCard;
import cmdcard.RedRotateCard;
import exception.IndexOutOfRangeException;
import exception.SelectEmptyCardException;
import exception.SelectMechException;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import test.base.GameTest;
import token.Mech;

public class RedRotateCardTest extends GameTest {
	RedRotateCard rrc = new RedRotateCard();
	RedAttackCard rac = new RedAttackCard();
	Mech redMech;
	Mech blueMech;
	@BeforeEach
	public void setBeforeEachTest() {
		super.setUpBeforeEachTest();
		redMech = new Mech(Direction.RIGHT, GameController.getBoard().getTile(5, 5),0);
		blueMech = new Mech(Direction.LEFT,GameController.getBoard().getTile(9, 9),1);
		GameController.setRedMech(redMech);
		GameController.setDraftedCard(rrc);
		GameController.setBlueMech(blueMech);
	}
	@Test
	public void testInitialize() {
		assertEquals(CardSprite.RED_ROTATE_CARD_1,rrc.getSpriteValue());
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
		assertEquals(2,((RedRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).rotate(1).size());
		assertEquals(3,((RedRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).rotate(2).size());
		assertEquals(4,((RedRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).rotate(3).size());
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
		GameController.setMinion(7, 5);
		assertEquals(1,((RedRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(1).size());
		GameController.setMinion(6, 5);
		assertEquals(2,((RedRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(2).size());
		redMech.setDirection(Direction.UP);
		GameController.setMinion(5, 4);
		assertEquals(1,((RedRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(2).size());
		redMech.setDirection(Direction.DOWN);
		GameController.setMinion(5, 6);
		GameController.setMinion(5, 7);
		assertEquals(2,((RedRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(2).size());
		redMech.setDirection(Direction.LEFT);
		GameController.setMinion(3, 5);
		assertEquals(1,((RedRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(2).size());
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
		GameController.setMinion(5, 6);
		GameController.setMinion(5, 7);
		GameController.execute(0);
		try {
			GameController.select(0);
			GameController.select(0);
			GameController.select(0);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		}
		assertEquals(null,GameController.getBoard().getTile(5, 6).getToken());
		assertEquals(null,GameController.getBoard().getTile(5, 7).getToken());
	}
}
