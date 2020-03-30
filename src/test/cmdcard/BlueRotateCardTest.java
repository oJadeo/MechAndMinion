package test.cmdcard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cmdcard.BlueMoveCard;
import cmdcard.BlueRotateCard;
import exception.IndexOutOfRangeException;
import exception.SelectEmptyCardException;
import exception.SelectMechException;
import logic.CardSprite;
import logic.Direction;
import logic.GameController;
import test.base.GameTest;
import token.Mech;
import token.Minion;

public class BlueRotateCardTest extends GameTest{
	BlueRotateCard brc = new BlueRotateCard();
	Mech redMech;
	Mech blueMech;
	@BeforeEach
	public void setUpBeforeEachTest() {
		super.setUpBeforeEachTest();
		redMech = new Mech(Direction.RIGHT, GameController.getBoard().getTile(5, 5),0);
		blueMech = new Mech(Direction.LEFT,GameController.getBoard().getTile(5, 6),1);
		GameController.setRedMech(redMech);
		GameController.setDraftedCard(brc);
		GameController.setBlueMech(blueMech);
	}
	@Test
	public void testInitialize() {
		assertEquals(CardSprite.BLUE_ROTATE_CARD_1,brc.getSpriteValue());
	}
	@Test
	public void testRotate() {
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
		assertEquals(Direction.DOWN,((BlueRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).rotate(1).get(0));
		assertEquals(Direction.UP,((BlueRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).rotate(1).get(1));
		assertEquals(Direction.DOWN,((BlueRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).rotate(2).get(0));
		assertEquals(Direction.LEFT,((BlueRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).rotate(2).get(1));
		assertEquals(Direction.UP,((BlueRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).rotate(2).get(2));
		assertEquals(2,((BlueRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).rotate(1).size());
		assertEquals(3,((BlueRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).rotate(2).size());
		assertEquals(4,((BlueRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).rotate(3).size());
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
		assertEquals(1,((BlueRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(1).size());
		GameController.setMinion(4, 4);
		assertEquals(2,((BlueRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(1).size());
		GameController.setMinion(6, 6);
		assertEquals(3,((BlueRotateCard)redMech.getCmdBoard().getCmdBox(0).getCmdCardList().get(0)).attack(1).size());
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
		GameController.execute(0);
		try {
			GameController.select(0);
			GameController.select(1);//error
			GameController.select(0);
		} catch (IndexOutOfRangeException e) {
			System.out.println(e.message);
		}
		assertEquals(null,GameController.getBoard().getTile(4, 4).getToken());
		
	}
	@Test
	public void testSpriteValue() {
		brc.setSpriteValue(1);
		assertEquals(CardSprite.BLUE_ROTATE_CARD_1,brc.getSpriteValue());
		brc.setSpriteValue(2);
		assertEquals(CardSprite.BLUE_ROTATE_CARD_2,brc.getSpriteValue());
		brc.setSpriteValue(3);
		assertEquals(CardSprite.BLUE_ROTATE_CARD_3,brc.getSpriteValue());
	}
}
