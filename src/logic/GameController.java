package logic;

import java.util.ArrayList;
import card.base.CmdCard;
import cmdcard.*;
import exception.*;
import tile.*;
import token.*;

public class GameController {
	private static Board board;
	private static Mech redMech;
	private static int redMechProgram;
	private static Mech blueMech;
	private static int blueMechProgram;
	private static Phase currentPhase;
	private static int turnCount;
	private static int score;
	private static int damageCount;
	private static int programCount;
	private static ArrayList<Object> selectable = new ArrayList<Object>();
	private static int selectTimes;
	private static CmdCard executingProgram;
	private static DraftedCard draftedCard;
	private static boolean gameEnd;
	private static Direction movingDirection;

	public static void initializeGame() {
		initializeBoard();
		draftedCard = new DraftedCard();
		redMech = new Mech(Direction.DOWN, board.getTile(0, 0), 0);
		redMechProgram = 0;
		blueMech = new Mech(Direction.UP, board.getTile(9, 9), 1);
		blueMechProgram = 0;
		currentPhase = Phase.Program;
		turnCount = 1;
		score = 0;
		damageCount = 0;
		programCount = 0;
		gameEnd = false;
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
		Minion testMinion = new Minion(Direction.UP, board.getTile(0, 1));
		Minion testMinion2 = new Minion(Direction.UP, board.getTile(0, 2));
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

	public static void update() {
		switch (currentPhase) {
		case Program:
			System.out.println("Score: " + score);
			board.update();
			draftedCard.update();
			redMech.update();
			blueMech.update();
			break;
		case Execute:
			System.out.println("Score: " + score);
			board.update();
			redMech.update();
			blueMech.update();
			System.out.println("Executing Program No." + (programCount + 1));
			System.out.println("times = " + selectTimes);
			String result = "selectable = [";
			for (Object e : selectable) {
				if (e instanceof Tile) {
					result += " [" + ((Tile) e).getLocationX() + "," + ((Tile) e).getLocationY() + "] ";
				}
				if (e instanceof Token) {
					result += " [" + ((Token) e).getSelfTile().getLocationX() + ","
							+ ((Token) e).getSelfTile().getLocationY() + "] ";
				}
				if (e instanceof Direction) {
					result += " [" + e + "] ";
				}
			}
			result += "]";
			System.out.println(result);
			if (selectable.size() != 0) {
				if (selectable.get(0) instanceof Tile) {
					System.out.println("Select Target Tile");
				}
				if (selectable.get(0) instanceof Direction) {
					System.out.println("Select Target Direction");
				}
				if (selectable.get(0) instanceof Token) {
					System.out.println("Select Target Token");
				}

			}
			break;
		default:
			System.out.println("Score: " + score);
			board.update();
			draftedCard.update();
			redMech.update();
			blueMech.update();
			break;
		}

	}

	public static void nextPhase() {
		switch (currentPhase) {
		case Program:
			draftedCard.reDeal();
			redMechProgram = 0;
			blueMechProgram = 0;
			currentPhase = Phase.Execute;
			execute(programCount);
			break;
		case Execute:
			programCount = 0;
			currentPhase = Phase.MinionMove;
			switch ((int) (Math.random() * 4)) {
			case 0:
				for (Minion e : getBoard().getMinionList()) {
					move(e, Direction.UP);
				}
				break;
			case 1:
				for (Minion e : getBoard().getMinionList()) {
					move(e, Direction.DOWN);
				}
				break;
			case 2:
				for (Minion e : getBoard().getMinionList()) {
					move(e, Direction.LEFT);
				}
				break;
			case 3:
				for (Minion e : getBoard().getMinionList()) {
					move(e, Direction.RIGHT);
				}
				break;
			default:
				break;
			}
			break;
		case MinionMove:
			currentPhase = Phase.MinionAttack;
			for (Minion minion : getBoard().getMinionList()) {
				minion.attack();
			}
			nextPhase();
			break;
		case MinionAttack:
			currentPhase = Phase.MinionSpawn;
			for (SpawnTile spawnTile : getBoard().getSpawnTileList()) {
				spawnTile.spawn();
			}
			nextPhase();
			break;
		case MinionSpawn:
			currentPhase = Phase.Program;
			update();
			turnCount += 1;
			if (turnCount % 3 == 0) {
				creatSpawnTile();
			}
			break;
		}
	}

	public static void setProgram(int no, int cmdSlot, int selectedCardSlot)
			throws SelectEmptyCardException, IndexOutOfRangeException, SelectMechException {
		if (selectedCardSlot < 0 && selectedCardSlot > 6) {
			throw new IndexOutOfRangeException("Can't put number out of slot(Card Slot)");
		}
		if (no != 0 && no != 1) {
			throw new SelectMechException("Can't select unless 1 or 2 in Select Mech");
		}
		if (cmdSlot < 0 && cmdSlot > 6) {
			throw new IndexOutOfRangeException("Can't put number out of slot(CommandSlot)");
		}
		if (draftedCard.getDraftedCardList().get(selectedCardSlot) == null) {
			throw new SelectEmptyCardException("Can't select empty card");
		}
		if (no == 0 && redMechProgram < 2) {
			draftedCard.getDraftedCardList().get(selectedCardSlot).setProgrammedMech(redMech);
			redMech.getCmdBoard().getCmdBox(cmdSlot).addCmdCard(draftedCard.getDraftedCardList().get(selectedCardSlot));
			draftedCard.getDraftedCardList().set(selectedCardSlot, null);
			redMechProgram += 1;
		} else if (no == 1 && blueMechProgram < 2) {
			draftedCard.getDraftedCardList().get(selectedCardSlot).setProgrammedMech(blueMech);
			blueMech.getCmdBoard().getCmdBox(cmdSlot)
					.addCmdCard(draftedCard.getDraftedCardList().get(selectedCardSlot));
			draftedCard.getDraftedCardList().set(selectedCardSlot, null);
			blueMechProgram += 1;
		} else {
			System.out.println("error");
		}
		if (redMechProgram + blueMechProgram == 4) {
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
			} else if (selectedToken.getSelfTile() instanceof MoveTile) {
				move(selectedToken, dir);
			}
			return true;
		}
		;
		return false;
	}

	public static void setSelectable(ArrayList<Object> selectable) {
		GameController.selectable = selectable;
	}

	public static void setSelectTimes(int selectTimes) {
		GameController.selectTimes = selectTimes;
		if (selectTimes == 0) {
			System.out.println("No target");
			programCount += 1;
			execute(programCount);
		}
	}

	public static void select(int i) throws IndexOutOfRangeException {
		if(selectable.size()==0) {
			System.out.println("Bug nothing to select");
		}
		if (i >= selectable.size() || i < 0) {
			throw new IndexOutOfRangeException("No target No." + (i + 1));
		}
		if (executingProgram instanceof BlueAttackCard) {
			if (selectable.get(i) instanceof Token) {
				((Token) selectable.get(i)).damaged();
				selectable.remove(i);
				selectTimes -= 1;
			}
		} else if (executingProgram instanceof BlueMoveCard) {
			if (selectable.get(i) instanceof Tile) {
				move(executingProgram.getProgrammedMech(), executingProgram.getProgrammedMech().getDirection());
				selectTimes -= 1;
				if (selectTimes != 0) {
					setSelectable(((BlueMoveCard) executingProgram).attack(1));
				}
			} else if (selectable.get(i) instanceof Token) {
				((Token) selectable.get(i)).damaged();
				selectable.remove(i);
				setSelectable(((BlueMoveCard) executingProgram).move(1));
			}
		} else if (executingProgram instanceof BlueRotateCard) {
			if (selectable.get(i) instanceof Direction) {
				executingProgram.getProgrammedMech().setDirection((Direction) selectable.get(i));
				setSelectable(((BlueRotateCard) executingProgram)
						.attack(executingProgram.getCmdBox().getCmdCardList().size()));
				if (selectable.size() > executingProgram.getCmdBox().getCmdCardList().size()) {
					setSelectTimes(executingProgram.getCmdBox().getCmdCardList().size());
				} else {
					setSelectTimes(selectable.size());
				}
			} else if (selectable.get(i) instanceof Token) {
				((Token) selectable.get(i)).damaged();
				selectable.remove(i);
				selectTimes -= 1;
			}
		} else if (executingProgram instanceof GreenAttackCard) {
			if (selectable.get(i) instanceof Token) {
				((Token) selectable.get(i)).damaged();
				selectable.remove(i);
				selectTimes -= 1;
			}
		} else if (executingProgram instanceof GreenMoveCard) {
			if (selectable.get(i) instanceof Direction) {
				movingDirection = ((Direction) selectable.get(i));
				ArrayList<Object> newSelectableList = new ArrayList<Object>();
				for (Tile e : getBoard().getAdjacentTile(executingProgram.getProgrammedMech().getSelfTile(), 1,
						movingDirection)) {
					newSelectableList.add((Object) e);
				}
				setSelectable(newSelectableList);
				if(selectable.size()!=0) {
					setSelectTimes(executingProgram.getCmdBox().getCmdCardList().size());
				}else {
					setSelectTimes(0);
				}
			}else if (selectable.get(i) instanceof Tile) {
				move(executingProgram.getProgrammedMech(), movingDirection);
				selectTimes -= 1;
				if(selectTimes!=0) {
					ArrayList<Object> newSelectableList = new ArrayList<Object>();
					for (Tile e : getBoard().getAdjacentTile(executingProgram.getProgrammedMech().getSelfTile(), 1,
							movingDirection)) {
						newSelectableList.add((Object) e);
					}
					setSelectable(newSelectableList);
				}
				if(selectable.size()==0) {
					setSelectTimes(0);
				}
			}
		} else if (executingProgram instanceof GreenRotateCard) {
			if (selectable.get(i) instanceof Direction) {
				executingProgram.getProgrammedMech().setDirection((Direction) selectable.get(i));
				setSelectable(((GreenRotateCard) executingProgram)
						.attack(executingProgram.getCmdBox().getCmdCardList().size()));
				if (selectable.size() != 0) {
					setSelectTimes(1);
				} else {
					setSelectTimes(0);
				}
			}else if(selectable.get(i) instanceof Token) {
				((Token) selectable.get(i)).damaged();
				selectable.remove(i);
				selectTimes -= 1;
			}
		} else if (executingProgram instanceof RedMoveCard) {
			if(selectable.get(i) instanceof Tile) {
				GameController.move(executingProgram.getProgrammedMech(), executingProgram.getProgrammedMech().getDirection());
				setSelectable(((RedMoveCard) executingProgram).move(1));
				selectTimes -= 1;
				if(selectable.size() == 0 || selectTimes == 0) {
					setSelectable(((RedMoveCard) executingProgram).attack(1));
					setSelectTimes(selectable.size());
				}
			}else if(selectable.get(i) instanceof Token) {
				((Token) selectable.get(i)).damaged();
				selectable.remove(i);
				selectTimes -= 1;
			}
		}// next card type

		if (selectTimes == 0) {
			programCount += 1;
			execute(programCount);
		} else {
			update();
		}
	}

	public static void execute(int programCount) {
		if (programCount == 12) {
			nextPhase();
		} else {
			if (programCount < 6) {
				redMech.getCmdBoard().getCmdBox(programCount).execute();
			} else {
				blueMech.getCmdBoard().getCmdBox(programCount - 6).execute();
			}
			programCount++;
		}
	}

	public static void addDamgeCount() {
		damageCount += 1;
		if (damageCount == 10) {
			gameEnd = true;
		}
	}

	public static Board getBoard() {
		return board;
	}

	public static boolean getGameEnd() {
		return gameEnd;
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

	public static void addScore() {
		score += 1;
	}

	public static void setExecutingProgram(CmdCard executingProgram) {
		GameController.executingProgram = executingProgram;
	}
}
