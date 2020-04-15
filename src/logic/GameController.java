package logic;

import java.util.ArrayList;
import card.base.*;
import cmdcard.*;
import damagecard.*;
import exception.SelectMechException;
import gui.DirectionPane;
import gui.PhasePane;
import tile.*;
import token.*;

public class GameController {
	private static Board board;
	private static Mech redMech;
	private static Mech blueMech;
	private static Phase currentPhase;
	private static int turnCount;
	private static int score;
	private static int damageCount;
	private static int selectedCard;
	private static CmdBox selectedCmdBox;
	private static Mech selectedMech;
	private static PhasePane phasePane;
	private static DirectionPane directionPane;
	private static int spawnNo;

	// Drafted Card Variable
	private static DraftedCard draftedCard;

	// ExecutintProgram variable
	private static int programCount;
	private static ArrayList<Object> selectable = new ArrayList<Object>();
	private static int selectTimes;
	private static CmdCard executingProgram;
	private static Direction movingDirection;
	private static int stepCount;

	// Main Game Logic
	public static void initializeGame() {
		initializeBoard();
		draftedCard = new DraftedCard();
		selectedCard = 6;
		redMech = new Mech(Direction.DOWN, board.getTile(0, 0), 0);
		blueMech = new Mech(Direction.UP, board.getTile(9, 9), 1);
		currentPhase = Phase.Program;
		turnCount = 1;
		score = 0;
		damageCount = 0;
		programCount = 0;
		phasePane = new PhasePane();
		directionPane = new DirectionPane();
		board.drawGameBoard();
	}

	public static void initializeBoard() {
		board = new Board();
		int specialTileNum = 0;
		while (specialTileNum < 6) {
			GameController.randomTile();
			specialTileNum += 1;
		}
		for (int i = 0; i < 3; i++) {
			creatSpawnTile();
		}
	}

	public static void randomTile() {
		// random location that is not already special tile
		int randomX;
		int randomY;
		do {
			randomX = (int) (Math.random() * 10);
			randomY = (int) (Math.random() * 10);
		} while (board.isSpecial(randomX, randomY));

		int random = (int) (Math.random() * 4);
		switch (random) {
		case 0:
			board.setTile(randomX, randomY, new ExplosiveTile(randomX, randomY));
			break;
		case 1:
			board.setTile(randomX, randomY, new MoveTile(randomX, randomY));
			break;
		case 2:
			board.setTile(randomX, randomY, new SpinTile(randomX, randomY));
			break;
		case 3:
			board.setTile(randomX, randomY, new SlipTile(randomX, randomY));
			break;
		default:
			break;
		}
	}

	public static void creatSpawnTile() {
		int randomX;
		int randomY;
		do {
			randomX = (int) (Math.random() * 10);
			randomY = (int) (Math.random() * 10);
		} while (board.isSpecial(randomX, randomY));

		board.setTile(randomX, randomY, new SpawnTile(randomX, randomY));
	}

	public static void nextPhase() {
		switch (currentPhase) {
		case Program:
			draftedCard.reDeal();
			draftedCard.setDisableButton(true);
			redMech.setProgramedCount(0);
			blueMech.setProgramedCount(0);
			currentPhase = Phase.Execute;
			programCount = 0;
			selectedCard = 6;
			selectedCmdBox = null;
			selectedMech = null;
			execute(programCount);
			break;
		case Execute:
			redMech.getCmdBoard().setDisableSlot(true);
			blueMech.getCmdBoard().setDisableSlot(true);
			if (board.getMinionList().size() != 0) {
				currentPhase = Phase.MinionMove;
				switch ((int) (Math.random() * 4)) {
				case 0:
					directionPane.drawSelectableDirection(Direction.UP);
					break;
				case 1:
					directionPane.drawSelectableDirection(Direction.DOWN);
					break;
				case 2:
					directionPane.drawSelectableDirection(Direction.LEFT);
					break;
				case 3:
					directionPane.drawSelectableDirection(Direction.RIGHT);
					break;
				default:
					break;
				}
			} else {
				currentPhase = Phase.MinionSpawn;
				minionSpawn();
			}
			break;
		case MinionMove:
			currentPhase = Phase.MinionAttack;
			minionAttack();
			break;
		case MinionAttack:
			currentPhase = Phase.MinionSpawn;
			minionSpawn();
			break;
		case MinionSpawn:
			currentPhase = Phase.Program;
			draftedCard.setDisableButton(false);
			redMech.getCmdBoard().setDisableSlot(false);
			redMech.getCmdBoard().draw();
			blueMech.getCmdBoard().setDisableSlot(false);
			blueMech.getCmdBoard().draw();
			turnCount += 1;
			if (turnCount % 3 == 0) {
				creatSpawnTile();
				System.out.println("SpawnTile is created");
			}
			selectedCard = 6;
			selectedCmdBox = null;
			selectedMech = null;
			break;
		default:
			break;
		}
		phasePane.drawPhase();
	}

	public static void minionAttack() {
		ArrayList<Token> damagedMechList = new ArrayList<Token>();
		for (Minion minion : getBoard().getMinionList()) {
			damagedMechList.addAll(minion.attack());
		}
		if (damagedMechList.size() == 0) {
			nextPhase();
		} else {
			for (Token damagedMech : damagedMechList) {
				damagedMech.getSelfTile().setSelectable(true);
				damagedMech.getSelfTile().setSelectToken(true);
			}
		}
		board.drawGameBoard();
	}

	public static void minionSpawn() {
		selectTimes = 0;
		for (SpawnTile spawnTile : getBoard().getSpawnTileList()) {
			if (spawnTile.getToken() == null) {
				spawnTile.setSelectable(true);
				selectTimes += 1;
			}
		}
		if (selectTimes == 0) {
			nextPhase();
		}
	}

	public static void minionMove(Direction dir) {
		for (Minion minion : getBoard().getMinionList()) {
			move(minion, dir);
		}
		board.drawGameBoard();
		nextPhase();
	}

	public static void setProgram(Mech selectedMech, CmdBox selectedCmdBox, int selectedDraftedCard)
			throws SelectMechException {
		CmdCard selectedCard = draftedCard.getDraftedCardList().get(selectedDraftedCard);
		if (selectedMech.getProgramedCount() == 2) {
			throw new SelectMechException(selectedMech);
		}
		selectedCard.setProgrammedMech(selectedMech);
		selectedCmdBox.addCmdCard(selectedCard);
		draftedCard.remove(selectedDraftedCard);
		selectedMech.setProgramedCount(selectedMech.getProgramedCount() + 1);
		redMech.getCmdBoard().draw();
		blueMech.getCmdBoard().draw();
		GameController.selectedMech = null;
		GameController.selectedCmdBox = null;
		GameController.selectedCard = 6;
		draftedCard.setSelectedDraftedCard(false);
		if (redMech.getProgramedCount() + blueMech.getProgramedCount() == 4) {
			nextPhase();
		}
	}

	public static boolean move(Token selectedToken, Direction dir) {
		if (getBoard().getAdjacentTile(selectedToken.getSelfTile(), 1, dir).size() == 0) {
			return false;
		}
		if (getBoard().getAdjacentTile(selectedToken.getSelfTile(), 1, dir).get(0).getToken() == null
				|| move(getBoard().getAdjacentTile(selectedToken.getSelfTile(), 1, dir).get(0).getToken(), dir)) {
			getBoard().getAdjacentTile(selectedToken.getSelfTile(), 1, dir).get(0).setToken(selectedToken);
			selectedToken.getSelfTile().setToken(null);
			selectedToken.setSelfTile(getBoard().getAdjacentTile(selectedToken.getSelfTile(), 1, dir).get(0));
			// add trigger SpecialTile
			if (selectedToken.getSelfTile() instanceof ExplosiveTile || selectedToken.getSelfTile() instanceof MoveTile
					|| selectedToken.getSelfTile() instanceof SpinTile) {
				selectedToken.getSelfTile().trigger();
			} else if (selectedToken.getSelfTile() instanceof SlipTile) {
				move(selectedToken, dir);
			}
			return true;
		}
		;
		return false;
	}

	public static void select(Object selectedObject) {
		if (selectedObject instanceof Tile) {
			((Tile) selectedObject).setSelectable(false);
		}
		if (selectedObject instanceof Token) {
			((Token) selectedObject).getSelfTile().setSelectable(false);
			((Token) selectedObject).getSelfTile().setSelectToken(false);
		}
		if (executingProgram instanceof BlueAttackCard) {
			if (selectedObject instanceof Token) {
				((Token) selectedObject).damaged();
				selectable.remove(selectedObject);
				selectTimes -= 1;
				if (((BlueAttackCard) executingProgram).attack(1).size() != 0) {
					setSelectable(((BlueAttackCard) executingProgram).attack(1));
				} else {
					setSelectTimes(0);
				}
			}
		} else if (executingProgram instanceof BlueMoveCard) {
			if (selectedObject instanceof Tile) {
				move(executingProgram.getProgrammedMech(), executingProgram.getProgrammedMech().getDirection());
				selectTimes -= 1;
				if (selectTimes != 0) {
					setSelectable(((BlueMoveCard) executingProgram).attack(1));
					if (selectable.size() == 0) {
						setSelectable(((BlueMoveCard) executingProgram).move(1));
					}
				}
			} else if (selectedObject instanceof Token) {
				((Token) selectedObject).damaged();
				selectable.remove(selectedObject);
				setSelectable(((BlueMoveCard) executingProgram).move(1));
			}
		} else if (executingProgram instanceof BlueRotateCard) {
			if (selectedObject instanceof Direction) {
				executingProgram.getProgrammedMech().setDirection((Direction) selectedObject);
				setSelectable(((BlueRotateCard) executingProgram)
						.attack(executingProgram.getCmdBox().getCmdCardList().size()));
				if (selectable.size() > executingProgram.getCmdBox().getCmdCardList().size()) {
					setSelectTimes(executingProgram.getCmdBox().getCmdCardList().size());
				} else {
					setSelectTimes(selectable.size());
				}
			} else if (selectedObject instanceof Token) {
				((Token) selectedObject).damaged();
				selectable.remove(selectedObject);
				selectTimes -= 1;
			}
		} else if (executingProgram instanceof GreenAttackCard) {
			if (selectedObject instanceof Token) {
				((Token) selectedObject).damaged();
				selectable.remove(selectedObject);
				selectTimes -= 1;
			}
		} else if (executingProgram instanceof GreenMoveCard) {
			if (selectedObject instanceof Direction) {
				movingDirection = ((Direction) selectedObject);
				ArrayList<Object> newSelectableList = new ArrayList<Object>();
				for (Tile e : getBoard().getAdjacentTile(executingProgram.getProgrammedMech().getSelfTile(), 1,
						movingDirection)) {
					newSelectableList.add((Object) e);
				}
				setSelectable(newSelectableList);
				if (selectable.size() != 0) {
					setSelectTimes(executingProgram.getCmdBox().getCmdCardList().size());
				} else {
					setSelectTimes(0);
				}
			} else if (selectedObject instanceof Tile) {
				move(executingProgram.getProgrammedMech(), movingDirection);
				selectTimes -= 1;
				if (selectTimes != 0) {
					ArrayList<Object> newSelectableList = new ArrayList<Object>();
					for (Tile e : getBoard().getAdjacentTile(executingProgram.getProgrammedMech().getSelfTile(), 1,
							movingDirection)) {
						newSelectableList.add((Object) e);
					}
					setSelectable(newSelectableList);
				}
				if (selectable.size() == 0) {
					setSelectTimes(0);
				}
			}
		} else if (executingProgram instanceof GreenRotateCard) {
			if (selectedObject instanceof Direction) {
				executingProgram.getProgrammedMech().setDirection((Direction) selectedObject);
				setSelectable(((GreenRotateCard) executingProgram)
						.attack(executingProgram.getCmdBox().getCmdCardList().size()));
				if (selectable.size() != 0) {
					setSelectTimes(1);
				} else {
					setSelectTimes(0);
				}
			} else if (selectedObject instanceof Token) {
				((Token) selectedObject).damaged();
				selectable.remove(selectedObject);
				selectTimes -= 1;
			}
		} else if (executingProgram instanceof RedAttackCard) {
			if (selectedObject instanceof Token) {
				((Token) selectedObject).damaged();
				selectable.remove(selectedObject);
				selectTimes -= 1;
			}
		} else if (executingProgram instanceof RedMoveCard) {
			if (selectedObject instanceof Tile) {
				GameController.move(executingProgram.getProgrammedMech(),
						executingProgram.getProgrammedMech().getDirection());
				setSelectable(((RedMoveCard) executingProgram).move(1));
				selectTimes -= 1;
				if (selectable.size() == 0 || selectTimes == 0) {
					setSelectable(((RedMoveCard) executingProgram).attack(1));
					setSelectTimes(selectable.size());
				}
			} else if (selectedObject instanceof Token) {
				((Token) selectedObject).damaged();
				selectable.remove(selectedObject);
				selectTimes -= 1;
			}
		} else if (executingProgram instanceof RedRotateCard) {
			if (selectedObject instanceof Direction) {
				executingProgram.getProgrammedMech().setDirection((Direction) selectedObject);
				setSelectable(((RedRotateCard) executingProgram)
						.attack(executingProgram.getCmdBox().getCmdCardList().size()));
				setSelectTimes(selectable.size());
			} else if (selectedObject instanceof Token) {
				((Token) selectedObject).damaged();
				selectable.remove(selectedObject);
				selectTimes -= 1;
			}
		} else if (executingProgram instanceof YellowAttackCard) {
			if (selectedObject instanceof Token) {
				ArrayList<Object> newSelectable = new ArrayList<Object>();
				for (Tile tile : getBoard().getDiagonalTile(((Token) selectedObject).getSelfTile(), 1, Direction.ALL)) {
					if (tile.getToken() instanceof Token
							&& !tile.getToken().equals(executingProgram.getProgrammedMech())) {
						newSelectable.add((Object) tile.getToken());
					}
				}
				((Token) selectedObject).damaged();
				selectable.remove(selectedObject);
				setSelectable(newSelectable);
				setSelectTimes(newSelectable.size());
			}
		} else if (executingProgram instanceof YellowMoveCard) {
			if (selectedObject instanceof Tile) {
				if (selectedObject.equals(executingProgram.getProgrammedMech().getSelfTile())) {
					setSelectTimes(0);
				} else {
					GameController.move(executingProgram.getProgrammedMech(),
							executingProgram.getProgrammedMech().getDirection());
					selectTimes -= 1;
					stepCount += 1;
					ArrayList<Object> newSelectable = new ArrayList<Object>();
					for (Tile tile : getBoard().getAdjacentTile(executingProgram.getProgrammedMech().getSelfTile(), 1,
							executingProgram.getProgrammedMech().getDirection())) {
						newSelectable.add((Object) tile);
					}
					if (newSelectable.size() != 0) {
						if (stepCount >= executingProgram.getCmdBox().getCmdCardList().size()) {
							newSelectable.add(executingProgram.getProgrammedMech().getSelfTile());
						}
						setSelectable(newSelectable);
					} else {
						setSelectTimes(0);
					}
				}
			}
		} else if (executingProgram instanceof YellowRotateCard) {
			if (selectedObject instanceof Direction) {
				executingProgram.getProgrammedMech().setDirection((Direction) selectedObject);
				setSelectable(((YellowRotateCard) executingProgram)
						.attack(executingProgram.getCmdBox().getCmdCardList().size()));
				if (selectable.size() == 0) {
					setSelectTimes(0);
				}
			} else if (selectedObject instanceof Token) {
				((Token) selectedObject).damaged();
				selectable.remove(selectedObject);
				selectTimes -= 1;
			}
		} else if (executingProgram instanceof BackMoveCard || executingProgram instanceof ForwardMoveCard
				|| executingProgram instanceof LeftMoveCard || executingProgram instanceof RightMoveCard) {
			if (selectedObject instanceof Direction) {
				GameController.move(executingProgram.getProgrammedMech(), (Direction) selectedObject);
				selectTimes -= 1;
			}
		} else if (executingProgram instanceof Rotate180Card || executingProgram instanceof Rotate270Card
				|| executingProgram instanceof Rotate90Card) {
			if (selectedObject instanceof Direction) {
				executingProgram.getProgrammedMech().setDirection((Direction) selectedObject);
				selectTimes -= 1;
			}
		}
		if (selectTimes == 0) {
			board.clearSelectable();
			programCount += 1;
			execute(programCount);
		}
		board.drawGameBoard();
	}

	public static void execute(int programCount) {
		redMech.getCmdBoard().setDisableSlot(true);
		blueMech.getCmdBoard().setDisableSlot(true);
		if (programCount == 12) {
			nextPhase();
		} else {
			if (programCount < 6) {
				redMech.getCmdBoard().getCmdBox(programCount).execute();
			} else {
				blueMech.getCmdBoard().getCmdBox(programCount - 6).execute();
			}
		}
	}

	public static void addDamgeCount() {
		damageCount += 1;
		if (damageCount == 10) {
			// TODO Make game End Scene
		}
	}

	public static void addScore() {
		score += 1;
	}

	// For testing
	public static void initializeTest() {
		currentPhase = Phase.Program;
		turnCount = 1;
		score = 0;
		damageCount = 0;
		programCount = 0;
		board = new Board();
	}

	public static void setDraftedCard(CmdCard cmdCard) {
		draftedCard = new DraftedCard(cmdCard);
	}

	public static void setRedMech(Mech redMech) {
		GameController.redMech = redMech;
	}

	public static void setBlueMech(Mech blueMech) {
		GameController.blueMech = blueMech;
	}

	public static Minion setMinion(int x, int y) {
		return new Minion(Direction.UP, board.getTile(x, y));

	}

	// getter setter
	public static ArrayList<Object> getSelectable() {
		return selectable;
	}

	public static int getSelectTimes() {
		return selectTimes;
	}

	public static void setSelectable(ArrayList<Object> selectable) {
		// disable old selectables
		for (Object oldSelctable : GameController.selectable) {
			if (oldSelctable instanceof Tile) {
				((Tile) oldSelctable).setSelectable(false);
				((Tile) oldSelctable).setSelectToken(false);
				((Tile) oldSelctable).draw();
			}
			if (oldSelctable instanceof Token) {
				((Token) oldSelctable).getSelfTile().setSelectable(false);
				((Token) oldSelctable).getSelfTile().setSelectToken(false);
				((Token) oldSelctable).getSelfTile().draw();
			}
			if (oldSelctable instanceof Direction) {
				directionPane.drawDirection();
			}
		}

		// enable new selectable
		GameController.selectable = selectable;
		if (selectable.size() != 0) {
			if (selectable.get(0) instanceof Tile) {
				for (Object tile : selectable) {
					((Tile) tile).setSelectable(true);
					((Tile) tile).setSelectToken(false);
					((Tile) tile).draw();
				}
			}
			if (selectable.get(0) instanceof Token) {
				for (Object token : selectable) {
					((Token) token).getSelfTile().setSelectable(true);
					((Token) token).getSelfTile().setSelectToken(true);
					((Token) token).getSelfTile().draw();
				}
			}
			if (selectable.get(0) instanceof Direction) {
				for (Object dir : selectable) {
					directionPane.drawSelectableDirection((Direction) dir);
				}
			}
		}
	}

	public static void setSelectTimes(int selectTimes) {
		GameController.selectTimes = selectTimes;
	}

	public static Board getBoard() {
		return board;
	}

	public static Phase getCurrentPhase() {
		return currentPhase;
	}

	public static int getProgramCount() {
		return programCount;
	}

	public static void setProgramCount(int programCount) {
		GameController.programCount = programCount;
	}

	public static void setExecutingProgram(CmdCard executingProgram) {
		GameController.executingProgram = executingProgram;
	}

	public static void setStepCount(int stepCount) {
		GameController.stepCount = stepCount;
	}

	public static int getScore() {
		return GameController.score;
	}

	public static int getHealth() {
		return 10 - GameController.damageCount;
	}

	public static void setSelectedCard(int selectedCard) {
		if (draftedCard.getDraftedCardList().get(selectedCard) != null) {
			GameController.selectedCard = selectedCard;
			if (selectedMech != null && selectedCmdBox != null) {
				try {
					setProgram(selectedMech, selectedCmdBox, selectedCard);
				} catch (SelectMechException e) {
					// TODO Auto-generated catch block
					//Creat new pop up
				}
			} else {
				draftedCard.setSelectedDraftedCard(true);
			}
		}
	}

	public static void setSelectedSlot(Mech selectedMech, CmdBox selectedCmdBox) {
		GameController.selectedMech = selectedMech;
		GameController.selectedCmdBox = selectedCmdBox;
		if (selectedCard != 6) {
			try {
				setProgram(selectedMech, selectedCmdBox, selectedCard);
			} catch (SelectMechException e) {
				// TODO Auto-generated catch block
				//Creat new pop up
			}
		} else {
			redMech.getCmdBoard().setSelectedCmdBox(selectedCmdBox);
			blueMech.getCmdBoard().setSelectedCmdBox(selectedCmdBox);
		}
	}

	public static Mech getBlueMech() {
		return blueMech;
	}

	public static Mech getRedMech() {
		return redMech;
	}

	public static DraftedCard getDraftedCard() {
		return draftedCard;
	}

	public static int getDamageCount() {
		return damageCount;
	}

	public static int getSelectedCard() {
		return selectedCard;
	}

	public static PhasePane getPhasePane() {
		return phasePane;
	}

	public static DirectionPane getDirectionPane() {
		return directionPane;
	}

	public static void setSpawnNo(int spawnNo) {
		GameController.spawnNo = spawnNo;
	}

	public static int getSpawnNo() {
		return spawnNo;
	}
}
