package test.cmdcard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import cmdcard.GreenAttackCard;
import exception.IndexOutOfRangeException;
import exception.SelectEmptyCardException;
import exception.SelectMechException;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import test.base.GameTest;
import token.Mech;
import token.Minion;

public class GreenAttackCardTest extends GameTest{
	GreenAttackCard gac = new GreenAttackCard();
	Mech redMech;
	Mech blueMech;
	@BeforeEach
	public void setBeforeEachTest() {
		super.setUpBeforeEachTest();
		redMech = new Mech(Direction.RIGHT, GameController.getBoard().getTile(5, 5),0);
		blueMech = new Mech(Direction.LEFT,GameController.getBoard().getTile(5, 6),1);
		GameController.setRedMech(redMech);
		GameController.setDraftedCard(gac);
		GameController.setBlueMech(blueMech);
	}
	@Test
	public void testInitialize() {
		assertEquals(CardSprite.GREEN_ATTACK_CARD_1,gac.getSpriteValue());
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
		assertEquals(2,((GreenAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(1).size());
		GameController.setMinion(7, 5);
		//wrong diagonal
		assertEquals(3,((GreenAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(2).size());
		assertEquals(3,((GreenAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(3).size());
	}
	@Test
	public void testExecute() {
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
		Minion m1 = GameController.setMinion(4, 4);
		assertEquals(m1,GameController.getBoard().getTile(4, 4).getToken());
		GameController.execute(0);
		try {
			GameController.select(0);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		}
		assertEquals(null,GameController.getBoard().getTile(4, 4).getToken());
	}
	@Test
	public void testSpriteValue() {
		gac.setSpriteValue(1);
		assertEquals(CardSprite.GREEN_ATTACK_CARD_1,gac.getSpriteValue());
		gac.setSpriteValue(2);
		assertEquals(CardSprite.GREEN_ATTACK_CARD_2,gac.getSpriteValue());
		gac.setSpriteValue(3);
		assertEquals(CardSprite.GREEN_ATTACK_CARD_3,gac.getSpriteValue());
	}
	
}
