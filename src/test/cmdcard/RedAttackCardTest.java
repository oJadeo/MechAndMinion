package test.cmdcard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmdcard.GreenRotateCard;
import cmdcard.RedAttackCard;
import exception.IndexOutOfRangeException;
import exception.SelectEmptyCardException;
import exception.SelectMechException;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import test.base.GameTest;
import token.Mech;

public class RedAttackCardTest extends GameTest {
	RedAttackCard rac = new RedAttackCard();
	Mech redMech;
	Mech blueMech;
	@BeforeEach
	public void setBeforeEachTest() {
		super.setUpBeforeEachTest();
		redMech = new Mech(Direction.UP, GameController.getBoard().getTile(5, 5),0);
		blueMech = new Mech(Direction.LEFT,GameController.getBoard().getTile(9, 9),1);
		GameController.setRedMech(redMech);
		GameController.setDraftedCard(rac);
		GameController.setBlueMech(blueMech);
	}
	@Test
	public void testInitialize() {
		assertEquals(CardSprite.RED_ATTACK_CARD_1,rac.getSpriteValue());
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
		GameController.setMinion(5, 3);
		GameController.setMinion(5, 4);
		GameController.setMinion(4, 4);
		GameController.setMinion(6, 4);
		GameController.setMinion(3, 3);
		GameController.setMinion(7, 3);
		GameController.setMinion(4, 3);
		GameController.setMinion(6, 3);
		assertEquals(2,((RedAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(1).size());
		assertEquals(6,((RedAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(2).size());
		assertEquals(8,((RedAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(3).size());
		redMech.setDirection(Direction.RIGHT);
		redMech.setSelfTile(GameController.getBoard().getTile(3, 4));
		assertEquals(2,((RedAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(1).size());
		assertEquals(3,((RedAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(2).size());
		assertEquals(4,((RedAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(3).size());
		redMech.setDirection(Direction.LEFT);
		redMech.setSelfTile(GameController.getBoard().getTile(7, 4));
		assertEquals(2,((RedAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(1).size());
		assertEquals(3,((RedAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(2).size());
		assertEquals(4,((RedAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(3).size());
		redMech.setDirection(Direction.DOWN);
		redMech.setSelfTile(GameController.getBoard().getTile(5, 2));
		assertEquals(2,((RedAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(1).size());
		assertEquals(4,((RedAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(2).size());
		assertEquals(6,((RedAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(3).size());
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
		GameController.setMinion(5, 3);
		GameController.execute(0);
		assertEquals(1,((RedAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(1).size());
		try {
			GameController.select(0);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		}
		assertEquals(null,GameController.getBoard().getTile(5, 3).getToken());
	}
	@Test
	public void testSpriteValue() {
		rac.setSpriteValue(1);
		assertEquals(CardSprite.RED_ATTACK_CARD_1,rac.getSpriteValue());
		rac.setSpriteValue(2);
		assertEquals(CardSprite.RED_ATTACK_CARD_2,rac.getSpriteValue());
		rac.setSpriteValue(3);
		assertEquals(CardSprite.RED_ATTACK_CARD_3,rac.getSpriteValue());
	}
}
