package strategy.beta;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
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
 * @version Sep 8, 2011
 */
public class BetaStrategyGameTest {

	private BetaStrategyGame game;
	private BetaStrategyGame movementTestGame;
	private BetaStrategyGame battleTestGame;
	
	@Before
	public void setUp() throws Exception {
		game = new BetaStrategyGame();
		game.initializeGame();
		
		movementTestGame = new BetaStrategyGame();
		movementTestGame.initializeGame();
		RectangularStrategyBoard movementTestBoard = new RectangularStrategyBoard(6, 6);
		movementTestBoard.initializeBoard();
		movementTestBoard.putPieceAt(new Position(0,0), new Piece(PieceType.SCOUT, PlayerColor.RED));
		movementTestBoard.putPieceAt(new Position(0,1), new Piece(PieceType.SCOUT, PlayerColor.RED));
		movementTestBoard.putPieceAt(new Position(3,1), new Piece(PieceType.FLAG, PlayerColor.RED));
		movementTestBoard.putPieceAt(new Position(0,4), new Piece(PieceType.BOMB, PlayerColor.RED));
		movementTestBoard.putPieceAt(new Position(0,5), new Piece(PieceType.SPY, PlayerColor.RED));
		movementTestGame.setBoard(movementTestBoard);
		
		battleTestGame = new BetaStrategyGame();
		battleTestGame.initializeGame();
		RectangularStrategyBoard battleTestBoard = new RectangularStrategyBoard(6, 6);
		battleTestBoard.initializeBoard();
		battleTestBoard.putPieceAt(new Position(0,0), new Piece(PieceType.LIEUTENANT, PlayerColor.RED));
		battleTestBoard.putPieceAt(new Position(1,0), new Piece(PieceType.SERGEANT, PlayerColor.BLUE));
		battleTestBoard.putPieceAt(new Position(1,1), new Piece(PieceType.LIEUTENANT, PlayerColor.BLUE));
		battleTestBoard.putPieceAt(new Position(0,1), new Piece(PieceType.SERGEANT, PlayerColor.RED));
		battleTestBoard.putPieceAt(new Position(1,2), new Piece(PieceType.SERGEANT, PlayerColor.BLUE));
		battleTestBoard.putPieceAt(new Position(0,2), new Piece(PieceType.SERGEANT, PlayerColor.RED));
		battleTestGame.setBoard(battleTestBoard);
	}

	@Test
	@Ignore
	public void testBetaStrategyGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitializeGamePlacesCorrectNumberOfPieces() {
		assertEquals(24, game.getNumPiecesOnBoard());
	}
	
	@Test
	public void testInitializeGameCreatesRandomArrangementOfPieces() throws StrategyException {
		BetaStrategyGame secondGame = new BetaStrategyGame();
		secondGame.initializeGame();
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
	@Ignore
	public void testIsGameOver() {
		fail("Not yet implemented");
	}

	@Test
	@Ignore
	public void testGetWinner() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPieceAt() throws StrategyException {
		//this method just delegates to the board, where it is more thoroughly tested
		//just a very basic test here
		assertTrue(game.getPieceAt(new Position(0,0)) instanceof Piece);
	}

}
