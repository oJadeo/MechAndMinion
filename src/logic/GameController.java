package logic;

import java.util.ArrayList;
import card.base.*;
import cmdcard.*;
import damagecard.*;
import exception.*;
import tile.*;
import token.*;

public class GameController {
	private static Board board;
	public static Mech redMech;
	private static Mech blueMech;
	private static Phase currentPhase;
	private static int turnCount;
	private static int score;
	private static int damageCount;
	private static boolean gameEnd;

	// Drafted Card Variable
	private static DraftedCard draftedCard;
	private static int redMechProgram;
	private static int blueMechProgram;

	// ExecutintProgram variable
	private static int programCount;
	private static ArrayList<Object> selectable = new ArrayList<Object>();
	private static int selectTimes;
	private static CmdCard executingProgram;
	private static Direction movingDirection;
	private static int stepCount;

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

	public static void initializeTest() {
		currentPhase = Phase.Program;
		turnCount = 1;
		score = 0;
		damageCount = 0;
		programCount = 0;
		gameEnd = false;
		board = new Board();
		redMechProgram = 0;
		blueMechProgram = 0;
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
    
    public static Minion setMinion(int x,int y) {
    	return new Minion(Direction.UP, board.getTile(x,y));
    	
    }
    public static ArrayList<Object> getSelectable(){
    	return selectable;
    }
    public static int getSelectTimes() {
    	return selectTimes;
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
		case MinionMove:
			System.out.println("Score: " + score);
			board.update();
			draftedCard.update();
			redMech.update();
			blueMech.update();
			System.out.println("Minion Moving"+board.getMinionList().get(0).getDirection());
			break;
		case MinionAttack:
			System.out.println("Score: " + score);
			board.update();
			draftedCard.update();
			redMech.update();
			blueMech.update();
			System.out.println("Minion Attacking");
			break;
		case MinionSpawn:
			System.out.println("Score: " + score);
			board.update();
			draftedCard.update();
			redMech.update();
			blueMech.update();
			System.out.println("Minion Spawning");
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
			if(board.getMinionList().size()!=0) {
			currentPhase = Phase.MinionMove;
			Direction moveDirection = null;
			switch ((int) (Math.random() * 4)) {
			case 0:
				moveDirection = Direction.UP;
				break;
			case 1:
				moveDirection = Direction.DOWN;
				break;
			case 2:
				moveDirection = Direction.LEFT;
				break;
			case 3:
				moveDirection = Direction.RIGHT;
				break;
			default:
				break;
			}
			for(Minion minion :getBoard().getMinionList()) {
				minion.setDirection(moveDirection);
			}
			}else {
				currentPhase = Phase.MinionSpawn;
			}
			break;
		case MinionMove:
			currentPhase = Phase.MinionAttack;
			break;
		case MinionAttack:
			currentPhase = Phase.MinionSpawn;
			break;
		case MinionSpawn:
			currentPhase = Phase.Program;
			turnCount += 1;
			if (turnCount % 3 == 0) {
				creatSpawnTile();
				System.out.println("SpawnTile is created");
			}
			break;
		default:
			break;
		}
	}
	
	public static void minionAttack() {
		for (Minion minion : getBoard().getMinionList()) {
			minion.attack();
		}
	}
	
	public static void minionSpawn() {
		for (SpawnTile spawnTile : getBoard().getSpawnTileList()) {
			spawnTile.spawn();
		}
	}
	
	public static void minionMove() {
		for(Minion minion : getBoard().getMinionList()) {
			GameController.move(minion, minion.getDirection());
		}
	}
	
	public static void setProgram(int no, int cmdSlot, int selectedCardSlot)
			throws SelectEmptyCardException, IndexOutOfRangeException, SelectMechException {
		if (selectedCardSlot < 0 || selectedCardSlot > 6) {
			throw new IndexOutOfRangeException("Can't put number out of slot(Card Slot)");
		}
		if (no != 0 && no != 1) {
			throw new SelectMechException("Can't select unless 1 or 2 in Select Mech");
		}
		if (cmdSlot < 0 || cmdSlot > 6) {
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
			} else if (selectedToken.getSelfTile() instanceof SlipTile) {
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
			programCount += 1;
			execute(programCount);
		}
	}

	public static void select(int i) throws IndexOutOfRangeException {
		if (selectable.size() == 0) {
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
				if (((BlueAttackCard) executingProgram).attack(1).size() != 0) {
					setSelectable(((BlueAttackCard) executingProgram).attack(1));
				} else {
					selectTimes = 0;
				}
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
				if (selectable.size() != 0) {
					setSelectTimes(executingProgram.getCmdBox().getCmdCardList().size());
				} else {
					setSelectTimes(0);
				}
			} else if (selectable.get(i) instanceof Tile) {
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
			if (selectable.get(i) instanceof Direction) {
				executingProgram.getProgrammedMech().setDirection((Direction) selectable.get(i));
				setSelectable(((GreenRotateCard) executingProgram)
						.attack(executingProgram.getCmdBox().getCmdCardList().size()));
				if (selectable.size() != 0) {
					setSelectTimes(1);
				} else {
					setSelectTimes(0);
				}
			} else if (selectable.get(i) instanceof Token) {
				((Token) selectable.get(i)).damaged();
				selectable.remove(i);
				selectTimes -= 1;
			}
		} else if (executingProgram instanceof RedAttackCard) {
			if (selectable.get(i) instanceof Token) {
				((Token) selectable.get(i)).damaged();
				selectable.remove(i);
				selectTimes -= 1;
			}
		} else if (executingProgram instanceof RedMoveCard) {
			if (selectable.get(i) instanceof Tile) {
				GameController.move(executingProgram.getProgrammedMech(),
						executingProgram.getProgrammedMech().getDirection());
				setSelectable(((RedMoveCard) executingProgram).move(1));
				selectTimes -= 1;
				if (selectable.size() == 0 || selectTimes == 0) {
					setSelectable(((RedMoveCard) executingProgram).attack(1));
					setSelectTimes(selectable.size());
				}
			} else if (selectable.get(i) instanceof Token) {
				((Token) selectable.get(i)).damaged();
				selectable.remove(i);
				selectTimes -= 1;
			}
		} else if (executingProgram instanceof RedRotateCard) {
			if (selectable.get(i) instanceof Direction) {
				executingProgram.getProgrammedMech().setDirection((Direction) selectable.get(i));
				setSelectable(((RedRotateCard) executingProgram)
						.attack(executingProgram.getCmdBox().getCmdCardList().size()));
				setSelectTimes(selectable.size());
			} else if (selectable.get(i) instanceof Token) {
				((Token) selectable.get(i)).damaged();
				selectable.remove(i);
				selectTimes -= 1;
			}
		} else if (executingProgram instanceof YellowAttackCard) {
			if (selectable.get(i) instanceof Token) {
				ArrayList<Object> newSelectable = new ArrayList<Object>();
				for (Tile tile : getBoard().getDiagonalTile(((Token) selectable.get(i)).getSelfTile(), 1,
						Direction.ALL)) {
					if (tile.getToken() instanceof Token
							&& !tile.getToken().equals(executingProgram.getProgrammedMech())) {
						newSelectable.add((Object) tile.getToken());
					}
				}
				((Token) selectable.get(i)).damaged();
				selectable.remove(i);
				if (newSelectable.size() != 0) {
					setSelectable(newSelectable);
					selectTimes -= 1;
				} else {
					selectTimes = 0;
				}
			}
		} else if (executingProgram instanceof YellowMoveCard) {
			if (selectable.get(i) instanceof Tile) {
				if (selectable.get(i).equals(executingProgram.getProgrammedMech().getSelfTile())) {
					selectTimes = 0;
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
						selectTimes = 0;
					}
				}
			}
		} else if (executingProgram instanceof YellowRotateCard) {
			if (selectable.get(i) instanceof Direction) {
				executingProgram.getProgrammedMech().setDirection((Direction) selectable.get(i));
				setSelectable(((YellowRotateCard) executingProgram)
						.attack(executingProgram.getCmdBox().getCmdCardList().size()));
				if (selectable.size() == 0) {
					selectTimes = 0;
				}
			} else if (selectable.get(i) instanceof Token) {
				((Token) selectable.get(i)).damaged();
				selectable.remove(i);
				selectTimes -= 1;
			}
		} else if (executingProgram instanceof BackMoveCard || executingProgram instanceof ForwardMoveCard
				|| executingProgram instanceof LeftMoveCard || executingProgram instanceof RightMoveCard) {
			if (selectable.get(i) instanceof Direction) {
				GameController.move(executingProgram.getProgrammedMech(), (Direction) selectable.get(i));
				selectTimes -= 1;
			}
		} else if (executingProgram instanceof Rotate180Card || executingProgram instanceof Rotate270Card || executingProgram instanceof Rotate90Card) {
			if (selectable.get(i) instanceof Direction) {
				executingProgram.getProgrammedMech().setDirection((Direction) selectable.get(i));
				selectTimes -= 1;
			}
		}

		if (selectTimes == 0) {
			programCount += 1;
			execute(programCount);
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

	public static void setStepCount(int stepCount) {
		GameController.stepCount = stepCount;
	}

}
