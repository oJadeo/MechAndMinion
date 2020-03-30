package test.cmdcard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import card.base.CmdCard;
import cmdcard.BlueAttackCard;
import exception.IndexOutOfRangeException;
import exception.SelectEmptyCardException;
import exception.SelectMechException;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import test.base.GameTest;
import token.Mech;
import token.Minion;

public class BlueAttackCardTest extends GameTest{
	BlueAttackCard bac = new BlueAttackCard();
	
	@BeforeEach
	public void setUpBeforeEachTest() {
		super.setUpBeforeEachTest();
		GameController.setRedMech(new Mech(Direction.LEFT, GameController.getBoard().getTile(5, 0),0));
		GameController.setBlueMech(new Mech(Direction.RIGHT, GameController.getBoard().getTile(0, 0),1));
		GameController.setDraftedCard(bac);
	}
	@Test
	public void testInitialize() {
		assertEquals(CardSprite.BLUE_ATTACK_CARD_1,bac.getSpriteValue());
	}
	@Test
	public void testAttack() {
		try {
			GameController.setProgram(1,5,2);
		} catch (SelectEmptyCardException e) {
			System.out.println(e.message);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		} catch (SelectMechException e) {
			System.out.println(e.message);
		}
		assertEquals(1,bac.attack(1).size());
		assertEquals(1,bac.attack(1).size());
		assertEquals(1,bac.attack(1).size());
		GameController.setMinion(1, 0);
		assertEquals(1,bac.attack(1).size());
		assertEquals(2,bac.attack(2).size());
		assertEquals(2,bac.attack(3).size());
		GameController.setMinion(2, 0);
		assertEquals(1,bac.attack(1).size());
		assertEquals(2,bac.attack(2).size());
		assertEquals(3,bac.attack(3).size());
		GameController.setMinion(6, 0);
		assertEquals(1,bac.attack(1).size());
		assertEquals(2,bac.attack(2).size());
		assertEquals(3,bac.attack(3).size());
	}
	@Test
	public void testExecute()  {
		try {
			GameController.setProgram(1,5,2);
			
		} catch (SelectEmptyCardException e) {
			System.out.println(e.message);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		} catch (SelectMechException e) {
			System.out.println(e.message);
		}
		Minion m1 = GameController.setMinion(1, 0);
		Minion m2 = GameController.setMinion(2, 0);
		GameController.execute(5);
		try {
			GameController.select(0);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		}
		assertEquals(null,GameController.getBoard().getTile(1, 0).getToken());
		assertEquals(m2,GameController.getBoard().getTile(2, 0).getToken());
	}
	@Test
	public void testSpriteValue() {
		bac.setSpriteValue(1);
		assertEquals(CardSprite.BLUE_ATTACK_CARD_1,bac.getSpriteValue());
		bac.setSpriteValue(2);
		assertEquals(CardSprite.BLUE_ATTACK_CARD_2,bac.getSpriteValue());
		bac.setSpriteValue(3);
		assertEquals(CardSprite.BLUE_ATTACK_CARD_3,bac.getSpriteValue());
	}
}
