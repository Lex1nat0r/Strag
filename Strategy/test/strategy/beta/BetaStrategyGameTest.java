package strategy.beta;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import strategy.Piece;
import strategy.PieceType;
import strategy.PlayerColor;
import strategy.Position;
import strategy.StrategyException;
import strategy.common.RectangularStrategyBoard;

/**
 * Test case for the BetaStrategy game.
 * 
 * @author Andrew Hurle, Alex Thornton-Clark, Gabriel Stern-Robbins
 * @version Sep 11, 2011
 */
public class BetaStrategyGameTest {

	private BetaStrategyGame game;
	private BetaStrategyGame movementTestGame;
	private BetaStrategyGame battleTestGame;
	private BetaStrategyGame playerPlaceTestGame;
	private BetaStrategyGame gameOverGame;
	
	@Before
	public void setUp() throws Exception {
		game = new BetaStrategyGame();
		
		movementTestGame = new BetaStrategyGame();
		RectangularStrategyBoard movementTestBoard = new RectangularStrategyBoard(6, 6);
		movementTestBoard.putPieceAt(new Position(0,0), new Piece(PieceType.SCOUT, PlayerColor.RED));
		movementTestBoard.putPieceAt(new Position(0,1), new Piece(PieceType.SCOUT, PlayerColor.RED));
		movementTestBoard.putPieceAt(new Position(3,1), new Piece(PieceType.FLAG, PlayerColor.RED));
		movementTestBoard.putPieceAt(new Position(0,4), new Piece(PieceType.BOMB, PlayerColor.RED));
		movementTestBoard.putPieceAt(new Position(0,5), new Piece(PieceType.SPY, PlayerColor.RED));
		movementTestGame.setBoard(movementTestBoard);
		
		battleTestGame = new BetaStrategyGame();
		RectangularStrategyBoard battleTestBoard = new RectangularStrategyBoard(6, 6);
		battleTestBoard.putPieceAt(new Position(0,0), new Piece(PieceType.LIEUTENANT, PlayerColor.RED));
		battleTestBoard.putPieceAt(new Position(1,0), new Piece(PieceType.SERGEANT, PlayerColor.BLUE));
		battleTestBoard.putPieceAt(new Position(1,1), new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE));
		battleTestBoard.putPieceAt(new Position(0,1), new Piece(PieceType.SERGEANT, PlayerColor.RED));
		battleTestBoard.putPieceAt(new Position(1,2), new Piece(PieceType.SERGEANT, PlayerColor.BLUE));
		battleTestBoard.putPieceAt(new Position(0,2), new Piece(PieceType.SERGEANT, PlayerColor.RED));
		battleTestBoard.putPieceAt(new Position(1,3), new Piece(PieceType.BOMB, PlayerColor.BLUE));
		battleTestBoard.putPieceAt(new Position(0,3), new Piece(PieceType.MINER, PlayerColor.RED));
		battleTestBoard.putPieceAt(new Position(1,4), new Piece(PieceType.MARSHAL, PlayerColor.BLUE));
		battleTestBoard.putPieceAt(new Position(0,4), new Piece(PieceType.SPY, PlayerColor.RED));
		battleTestGame.setBoard(battleTestBoard);
		
		gameOverGame = new BetaStrategyGame();
		//put a scout and flag next to each other to test a win
		RectangularStrategyBoard gameOverTestBoard = new RectangularStrategyBoard(6, 6);
		gameOverTestBoard.putPieceAt(new Position(4,4), new Piece(PieceType.SCOUT, PlayerColor.RED));
		gameOverTestBoard.putPieceAt(new Position(4,5), new Piece(PieceType.FLAG, PlayerColor.BLUE));
		gameOverTestBoard.putPieceAt(new Position(5,4), new Piece(PieceType.SCOUT, PlayerColor.BLUE));
		gameOverTestBoard.putPieceAt(new Position(5,5), new Piece(PieceType.FLAG, PlayerColor.RED));
		gameOverGame.setBoard(gameOverTestBoard);
		
		playerPlaceTestGame = new BetaStrategyGame(true);
	}

	@Test
	public void testInitialState() {
		assertFalse(game.isGameOver());
		assertEquals(null, game.getWinner());
	}

	@Test
	public void testInitializeGamePlacesCorrectNumberOfPieces() {
		assertEquals(24, game.getNumPiecesOnBoard());
	}
	
	@Test
	public void testInitializeGameCreatesRandomArrangementOfPieces() throws StrategyException {
		BetaStrategyGame secondGame = new BetaStrategyGame();
		assertFalse(secondGame.equals(game));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveToSamePosition() throws StrategyException {
		movementTestGame.move(new Position(0,0), new Position(0,0));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveFromUnoccupiedPosition() throws StrategyException {
		movementTestGame.move(new Position(5,5), new Position(5,4));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveFromPositionOutOfBounds() throws StrategyException {
		movementTestGame.move(new Position(6,5), new Position(5,5));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveToPositionOutOfBounds() throws StrategyException {
		movementTestGame.move(new Position(0,5), new Position(0,6));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveDiagonally() throws StrategyException {
		movementTestGame.move(new Position(0,0), new Position(1,1));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveImmovablePiece() throws StrategyException {
		movementTestGame.move(new Position(3,1), new Position(3,2));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveOutOfRange() throws StrategyException {
		movementTestGame.move(new Position(0,5), new Position(2,5));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveOntoFriendlyPiece() throws StrategyException {
		movementTestGame.move(new Position(0,5), new Position(0,4));
	}
	
	@Test(expected=StrategyException.class)
	public void testMoveThroughOccupiedSpace() throws StrategyException {
		//there's a flag between 0,1 and 5,1
		movementTestGame.move(new Position(0,1), new Position(5,1));
	}
	
	@Test
	public void testMoveInfiniteRangePiece() throws StrategyException {
		Piece originalPiece = movementTestGame.getPieceAt(new Position(0,0));
		Piece destinationPiece = movementTestGame.move(new Position(0,0), new Position(5,0));
		assertEquals(originalPiece, destinationPiece);
		assertEquals(originalPiece, movementTestGame.getPieceAt(new Position(5,0)));
		assertFalse(movementTestGame.getBoard().isOccupied(new Position(0,0)));
	}
	
	@Test
	public void testMoveDefaultRangePiece() throws StrategyException {
		Piece originalPiece = movementTestGame.getPieceAt(new Position(0,5));
		Piece destinationPiece = movementTestGame.move(new Position(0,5), new Position(1,5));
		assertEquals(originalPiece, destinationPiece);
		assertEquals(originalPiece, movementTestGame.getPieceAt(new Position(1,5)));
		assertFalse(movementTestGame.getBoard().isOccupied(new Position(0,5)));
	}
	
	@Test
	public void testBattleLieutenantAttacksSergeantAndWins() throws StrategyException {
		Piece lieutenant = battleTestGame.getPieceAt(new Position(0,0));
		Piece returnedPiece = battleTestGame.move(new Position(0,0), new Position(1,0));
		assertEquals(lieutenant, returnedPiece);
		assertEquals(lieutenant, battleTestGame.getPieceAt(new Position(1,0)));
		assertFalse(battleTestGame.getBoard().isOccupied(new Position(0,0)));
	}
	
	@Test
	public void testBattleSergeantAttacksLieutenantAndLoses() throws StrategyException {
		Piece lieutenant = battleTestGame.getPieceAt(new Position(1,1));
		Piece returnedPiece = battleTestGame.move(new Position(0,1), new Position(1,1));
		assertEquals(lieutenant, returnedPiece);
		assertEquals(lieutenant, battleTestGame.getPieceAt(new Position(1,1)));
		assertFalse(battleTestGame.getBoard().isOccupied(new Position(0,1)));
	}
	
	@Test
	public void testBattleSergeantAttacksSergeantAndDraws() throws StrategyException {
		Piece returnedPiece = battleTestGame.move(new Position(0,2), new Position(1,2));
		assertEquals(Piece.NULL_PIECE, returnedPiece);
		assertEquals(Piece.NULL_PIECE, battleTestGame.getPieceAt(new Position(1,2)));
		assertFalse(battleTestGame.getBoard().isOccupied(new Position(0,2)));
	}
	
	@Test
	public void testBattleMinerAttacksBombAndWins() throws StrategyException {
		Piece miner = battleTestGame.getPieceAt(new Position(0,3));
		Piece returnedPiece = battleTestGame.move(new Position(0,3), new Position(1,3));
		assertEquals(miner, returnedPiece);
	}
	
	@Test
	public void testBattleSpyAttacksMarshalAndWins() throws StrategyException {
		Piece spy = battleTestGame.getPieceAt(new Position(0,4));
		Piece returnedPiece = battleTestGame.move(new Position(0,4), new Position(1,4));
		assertEquals(spy, returnedPiece);
	}
	
	@Test
	public void testPlayerCanPlacePiece() throws StrategyException {
		Piece scout = new Piece(PieceType.SCOUT, PlayerColor.RED);
		playerPlaceTestGame.playerPlacePiece(new Position(0, 0), scout);
		assertEquals(scout, playerPlaceTestGame.getPieceAt(new Position(0, 0)));
		
	}

	@Test (expected=StrategyException.class)
	public void testPlayerPlacePieceWhenPlayerNotAllowedToPlacePiece() throws StrategyException {
		Piece scout = new Piece(PieceType.SCOUT, PlayerColor.RED);
		game.playerPlacePiece(new Position(0, 0), scout);
	}
	
	@Test (expected=StrategyException.class)
	public void testPlayerPlacePieceInOccupiedSpace() throws StrategyException {
		Piece scout = new Piece(PieceType.SCOUT, PlayerColor.RED);
		playerPlaceTestGame.playerPlacePiece(new Position(0, 0), scout);
		Piece marshal = new Piece(PieceType.MARSHAL, PlayerColor.RED);
		playerPlaceTestGame.playerPlacePiece(new Position(0, 0), marshal);
	}
	
	@Test (expected=StrategyException.class)
	public void testPlayerCannotPlaceMultipleOfSamePiece() throws StrategyException {
		Piece scout = new Piece(PieceType.SCOUT, PlayerColor.RED);
		playerPlaceTestGame.playerPlacePiece(new Position(0, 0), scout);
		Piece anotherScout = new Piece(PieceType.SCOUT, PlayerColor.RED);
		playerPlaceTestGame.playerPlacePiece(new Position(1, 0), anotherScout);
	}
	
	@Test (expected=StrategyException.class)
	public void testPlayerCannotPlaceRedPieceOutsideSetupZone() throws StrategyException {
		Piece redScout = new Piece(PieceType.SCOUT, PlayerColor.RED);
		playerPlaceTestGame.playerPlacePiece(new Position(5,5), redScout);
	}
	
	@Test (expected=StrategyException.class)
	public void testPlayerCannotPlaceBluePieceOutsideSetupZone() throws StrategyException {
		Piece blueScout = new Piece(PieceType.SCOUT, PlayerColor.BLUE);
		playerPlaceTestGame.playerPlacePiece(new Position(0,0), blueScout);
	}
	
	@Test 
	public void testRedCapsBlueFlag() throws StrategyException {
		Piece scout = gameOverGame.getPieceAt(new Position(4,4));
		Piece returnedPiece = gameOverGame.move(new Position(4,4), new Position(4,5));
		assertEquals(scout, returnedPiece);
		assertEquals(scout, gameOverGame.getPieceAt(new Position(4,5)));
		assertFalse(gameOverGame.getBoard().isOccupied(new Position(4,4)));
	}

	@Test
	public void testIsGameOver() throws StrategyException {
		Piece scout = gameOverGame.getPieceAt(new Position(4,4));
		Piece returnedPiece = gameOverGame.move(new Position(4,4), new Position(4,5));
		assertEquals(scout, returnedPiece);
		assertEquals(scout, gameOverGame.getPieceAt(new Position(4,5)));
		assertFalse(gameOverGame.getBoard().isOccupied(new Position(4,4)));
		assertEquals(true,gameOverGame.isGameOver());
	}

	@Test
	public void testGetWinner() throws StrategyException {
		//red should win
		Piece scout = gameOverGame.getPieceAt(new Position(4,4));
		Piece returnedPiece = gameOverGame.move(new Position(4,4), new Position(4,5));
		assertEquals(scout, returnedPiece);
		assertEquals(scout, gameOverGame.getPieceAt(new Position(4,5)));
		assertFalse(gameOverGame.getBoard().isOccupied(new Position(4,4)));
		assertEquals(PlayerColor.RED,gameOverGame.getWinner());
	}
	
	@Test (expected=StrategyException.class)
	public void testMoveAfterOver() throws StrategyException {
		gameOverGame.move(new Position(4,4), new Position(4,5));
		gameOverGame.move(new Position(4,5), new Position(4,4));
	}
	
	@Test
	public void testMorethanFiveMoves() throws StrategyException {
		gameOverGame.move(new Position(4,4), new Position(4,3));
		gameOverGame.move(new Position(5,4), new Position(5,3));
		gameOverGame.move(new Position(4,3), new Position(4,4));
		gameOverGame.move(new Position(5,3), new Position(5,4));
		gameOverGame.move(new Position(4,4), new Position(4,3));
		gameOverGame.move(new Position(5,4), new Position(5,3));
		gameOverGame.move(new Position(4,3), new Position(4,4));
		gameOverGame.move(new Position(5,3), new Position(5,4));
		gameOverGame.move(new Position(4,4), new Position(4,3));
		gameOverGame.move(new Position(5,4), new Position(5,3));
		assertEquals(true,gameOverGame.isGameOver());
	}
	
	@Test (expected=StrategyException.class)
	public void testDoubleMove() throws StrategyException {
		gameOverGame.move(new Position(4,4), new Position(4,3));
		gameOverGame.move(new Position(4,3), new Position(4,4));
	}
	
	@Test (expected=StrategyException.class)
	public void testBlueFirst() throws StrategyException {
		gameOverGame.move(new Position(5,4), new Position(5,3));
	}

	@Test
	public void testGetPieceAt() throws StrategyException {
		//this method just delegates to the board, where it is more thoroughly tested
		//just a very basic test here
		assertTrue(game.getPieceAt(new Position(0,0)) instanceof Piece);
	}
	
	@Test
	public void testEqualsObject() throws StrategyException {
		RectangularStrategyBoard board = new RectangularStrategyBoard(6, 6);
		game.setBoard(board);
		BetaStrategyGame mockGame = new BetaStrategyGame();
		RectangularStrategyBoard mockBoard = new RectangularStrategyBoard(6, 6);
		mockGame.setBoard(mockBoard);
		assertTrue(game.equals(game));
		assertTrue(game.equals(mockGame));
		assertFalse(game.equals(movementTestGame));
		assertFalse(game.equals(null));
		assertFalse(game.equals(new Object()));
	}
	
	//Hurf a derf
	//gawddammit
	
}
