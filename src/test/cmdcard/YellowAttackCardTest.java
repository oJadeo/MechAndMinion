package test.cmdcard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmdcard.GreenRotateCard;
import cmdcard.YellowAttackCard;
import exception.IndexOutOfRangeException;
import exception.SelectEmptyCardException;
import exception.SelectMechException;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import test.base.GameTest;
import token.Mech;

public class YellowAttackCardTest extends GameTest{
	YellowAttackCard yac = new YellowAttackCard();
	Mech redMech;
	Mech blueMech;
	@BeforeEach
	public void setBeforeEachTest() {
		super.setUpBeforeEachTest();
		redMech = new Mech(Direction.RIGHT, GameController.getBoard().getTile(5, 5),0);
		blueMech = new Mech(Direction.LEFT,GameController.getBoard().getTile(9, 9),1);
		GameController.setRedMech(redMech);
		GameController.setDraftedCard(yac);
		GameController.setBlueMech(blueMech);
	}
	@Test
	public void testInitialize() {
		assertEquals(CardSprite.YELLOW_ATTACK_CARD_1,yac.getSpriteValue());
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
		GameController.setMinion(6, 6);
		GameController.setMinion(6, 4);
		GameController.setMinion(4, 6);
		assertEquals(4,((YellowAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(1).size());
		assertEquals(4,((YellowAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(2).size());
		assertEquals(4,((YellowAttackCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(3).size());
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
		GameController.setMinion(4, 4);
		GameController.setMinion(3, 3);
		GameController.setMinion(2, 2);
		GameController.execute(0);
		try {
			GameController.select(0);
			GameController.select(0);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		}
		assertEquals(null,GameController.getBoard().getTile(3, 3).getToken());
		assertEquals(null,GameController.getBoard().getTile(4, 4).getToken());
		assertEquals(0,GameController.getSelectTimes());
	}
	@Test
	public void testSpriteValue() {
		yac.setSpriteValue(1);
		assertEquals(CardSprite.YELLOW_ATTACK_CARD_1,yac.getSpriteValue());
		yac.setSpriteValue(2);
		assertEquals(CardSprite.YELLOW_ATTACK_CARD_2,yac.getSpriteValue());
		yac.setSpriteValue(3);
		assertEquals(CardSprite.YELLOW_ATTACK_CARD_3,yac.getSpriteValue());
	}
	
}
