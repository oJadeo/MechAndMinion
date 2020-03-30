package test.base;

import org.junit.jupiter.api.BeforeEach;

import logic.Board;
import logic.Direction;
import logic.GameController;
import token.Mech;
import token.Minion;

public class GameTest {
	@BeforeEach
	public void setUpBeforeEachTest() {
		GameController.initializeTest();
	}
	
	
}
