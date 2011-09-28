package strategy.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import strategy.*;

/**
 * Test case for the RectangularStrategyBoard class.
 * 
 * @author Andrew Hurle, Alex Thornton-Clark, Gabriel Stern-Robbins
 * @version Sep 11, 2011
 */
public class RectangularStrategyBoardTest {

	private RectangularStrategyBoard board;
	
	@Before
	public void setUp() throws Exception {
		board = new RectangularStrategyBoard(6, 6);
	}

	@Test
	public void testRectangularStrategyBoard() {
		Iterator<Piece> iter = board.iterator();

		while (iter.hasNext()) {
			assertTrue(Piece.NULL_PIECE == iter.next());
		}
	}

	@Test
	public void testIterator() {
		Iterator<Piece> iter = board.iterator();
		int iterCount = 0;
		
		while (iter.hasNext()) {
			iter.next();
			iterCount++;
		}
		
		assertEquals(36, iterCount);
	}

	@Test
	public void testGetPieceAt() throws StrategyException {
		assertTrue(Piece.NULL_PIECE == board.getPieceAt(new Position(0, 0)));
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testGetPieceAtInvalidPosition() {
		board.getPieceAt(new Position(-1, 0));
	}

	@Test
	public void testPutPieceAt() throws StrategyException {
		Piece redBomb = new Piece(PieceType.BOMB, PlayerColor.RED);
		board.putPieceAt(new Position(0, 0), redBomb);
		assertTrue(redBomb == board.getPieceAt(new Position(0, 0)));
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testPutPieceAtInvalidPosition() {
		board.putPieceAt(new Position(-1, 0), new Piece(PieceType.SCOUT, PlayerColor.RED));
	}

	@Test
	public void testIsOccupied() throws StrategyException {
		assertFalse(board.isOccupied(new Position(0, 0)));
		board.putPieceAt(new Position(0, 0), new Piece(PieceType.BOMB, PlayerColor.RED));
		assertTrue(board.isOccupied(new Position(0, 0)));
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testIsOccupiedInvalidPosition() {
		board.isOccupied(new Position(-1, 0));
	}

	@Test
	public void testGetDistance() throws StrategyException {
		assertEquals(board.getDistance(new Position(0,0), new Position(0,0)), 0);
		assertEquals(board.getDistance(new Position(0,0), new Position(0,1)), 1);
		assertEquals(board.getDistance(new Position(0,0), new Position(1,0)), 1);
		assertEquals(board.getDistance(new Position(0,1), new Position(0,0)), 1);
		assertEquals(board.getDistance(new Position(1,0), new Position(0,0)), 1);
		assertEquals(board.getDistance(new Position(2,2), new Position(4,2)), 2);
	}
	
	@Test
	public void testOccupiedSpaceBetweenPositions() throws StrategyException {
		assertFalse(board.isOccupiedSpaceBetweenPositions(new Position(3,0), new Position(3,5)));
		assertFalse(board.isOccupiedSpaceBetweenPositions(new Position(0,3), new Position(5,3)));
		board.putPieceAt(new Position(3,3), new Piece(PieceType.BOMB, PlayerColor.RED));
		assertTrue(board.isOccupiedSpaceBetweenPositions(new Position(3,0), new Position(3,5)));
		assertTrue(board.isOccupiedSpaceBetweenPositions(new Position(0,3), new Position(5,3)));
	}
	
	@Test
	public void testValidatePositionSuccessfully() throws StrategyException
	{
		board.validatePosition(new Position(0, 0));
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testValidatePositionRowOverflow()
	{
		board.validatePosition(new Position(6, 0));
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testValidatePositionRowUnderflow()
	{
		board.validatePosition(new Position(-1, 0));
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testValidatePositionColumnOverflow()
	{
		board.validatePosition(new Position(0, 6));
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void testValidatePositionColumnUnderflow()
	{
		board.validatePosition(new Position(0, -1));
	}

	@Test
	public void testToString() throws StrategyException {
		assertEquals("NNNNNN\n" + 
				"NNNNNN\n" +
				"NNNNNN\n" +
				"NNNNNN\n" + 
				"NNNNNN\n" +
				"NNNNNN\n", board.toString());
		board.putPieceAt(new Position(0, 0), new Piece(PieceType.BOMB, PlayerColor.RED));
		assertEquals("NNNNNN\n" + 
				"NNNNNN\n" +
				"NNNNNN\n" +
				"NNNNNN\n" + 
				"NNNNNN\n" +
				"bNNNNN\n", board.toString());
		board.putPieceAt(new Position(2, 2), new Piece(PieceType.BOMB, PlayerColor.RED));
		assertEquals("NNNNNN\n" + 
				"NNNNNN\n" +
				"NNNNNN\n" +
				"NNbNNN\n" + 
				"NNNNNN\n" +
				"bNNNNN\n", board.toString());
	}
	
	@Test
	public void testEqualsObject() throws StrategyException {
		RectangularStrategyBoard mockBoard = new RectangularStrategyBoard(6, 6);
		RectangularStrategyBoard differentlySizedBoard = new RectangularStrategyBoard(6, 5);
		assertTrue(board.equals(board));
		assertTrue(board.equals(mockBoard));
		mockBoard.putPieceAt(new Position(0, 0), new Piece(PieceType.SCOUT, PlayerColor.RED));
		assertFalse(board.equals(mockBoard));
		assertFalse(board.equals(differentlySizedBoard));
		assertFalse(board.equals(new Object()));
	}
	
	@Test
	public void testHashCode() {
		int hash = board.hashCode();
		assertEquals(hash, board.hashCode());  //test consistency
	}
	
	@Test
	public void testPlacePiecesOnBoardUsingPieceAssociations() {
		PiecePositionAssociation[] startingRedPieces =  {
			new PiecePositionAssociation(new Piece(PieceType.FLAG, PlayerColor.RED), new Position(0, 0)),
		    new PiecePositionAssociation(new Piece(PieceType.BOMB, PlayerColor.RED), new Position(1, 0)),
		    new PiecePositionAssociation(new Piece(PieceType.BOMB, PlayerColor.RED), new Position(1, 1)),
		    new PiecePositionAssociation(new Piece(PieceType.BOMB, PlayerColor.RED), new Position(0, 1))
		};
		
		PiecePositionAssociation[] startingBluePieces =  {
			new PiecePositionAssociation(new Piece(PieceType.FLAG, PlayerColor.BLUE), new Position(9, 9)),
		    new PiecePositionAssociation(new Piece(PieceType.BOMB, PlayerColor.BLUE), new Position(8, 9)),
		    new PiecePositionAssociation(new Piece(PieceType.BOMB, PlayerColor.BLUE), new Position(8, 8)),
		    new PiecePositionAssociation(new Piece(PieceType.BOMB, PlayerColor.BLUE), new Position(9, 8))
		};
		
		RectangularStrategyBoard deltaBoard = new RectangularStrategyBoard(10, 10, startingRedPieces, startingBluePieces);
		assertEquals(new Piece(PieceType.BOMB, PlayerColor.RED), deltaBoard.getPieceAt(new Position(0, 1)));
		assertEquals(new Piece(PieceType.BOMB, PlayerColor.BLUE), deltaBoard.getPieceAt(new Position(9, 8)));
		assertEquals(Piece.NULL_PIECE, deltaBoard.getPieceAt(new Position(5,5)));
	}

}
