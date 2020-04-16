package test.base;

import org.junit.jupiter.api.BeforeEach;
import logic.GameController;

public class GameTest {
	@BeforeEach
	public void setUpBeforeEachTest() {
		GameController.initializeTest();
	}
	
	
}
