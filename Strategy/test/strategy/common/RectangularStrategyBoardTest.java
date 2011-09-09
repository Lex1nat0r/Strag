package strategy.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import strategy.*;

public class RectangularStrategyBoardTest {

	private RectangularStrategyBoard board;
	
	@Before
	public void setUp() throws Exception {
		board = new RectangularStrategyBoard(6, 6);
		board.initializeBoard();
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
	public void testGetPieceAt() {
		assertTrue(Piece.NULL_PIECE == board.getPieceAt(new Position(0, 0)));
	}

	@Test
	public void testPutPieceAt() {
		Piece redBomb = new Piece(PieceType.BOMB, PlayerColor.RED);
		board.putPieceAt(new Position(0, 0), redBomb);
		assertTrue(redBomb == board.getPieceAt(new Position(0, 0)));
	}

	@Test
	public void testIsOccupied() {
		assertFalse(board.isOccupied(new Position(0, 0)));
		board.putPieceAt(new Position(0, 0), new Piece(PieceType.BOMB, PlayerColor.RED));
		assertTrue(board.isOccupied(new Position(0, 0)));
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
	
	@Test(expected=StrategyException.class)
	public void testGetDistanceDiagonally() throws StrategyException {
		board.getDistance(new Position(0,0), new Position(1,1));
	}

	@Test
	public void testInitializeBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
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

}
