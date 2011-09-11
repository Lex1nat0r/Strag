package strategy.beta;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import strategy.Piece;
import strategy.PieceType;
import strategy.PlayerColor;

public class PieceTest {

	Piece bluScout;
	Piece redMajor;
	
	@Before
	public void setUp() throws Exception {
		bluScout = new Piece(PieceType.SCOUT, PlayerColor.BLUE);
		redMajor = new Piece(PieceType.MAJOR, PlayerColor.RED);
	}

	@Test
	public void testHashCode() {
		Piece otherBluScout = new Piece(PieceType.SCOUT, PlayerColor.BLUE);
		int hash = bluScout.hashCode();
		assertEquals(hash, otherBluScout.hashCode());
		assertEquals(hash, bluScout.hashCode());
	}

	@Test
	@Ignore
	public void testPiece() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetType() {
		assertEquals(PieceType.SCOUT, bluScout.getType());
		assertEquals(PieceType.MAJOR, redMajor.getType());
	}

	@Test
	public void testGetColor() {
		assertEquals(PlayerColor.BLUE, bluScout.getColor());
		assertEquals(PlayerColor.RED, redMajor.getColor());
	}

	@Test
	public void testEqualsObject() {
		Piece mockBluScout = new Piece(PieceType.SCOUT, PlayerColor.BLUE);
		assertTrue(bluScout.equals(bluScout));
		assertTrue(bluScout.equals(mockBluScout));
		assertFalse(bluScout.equals(redMajor));
		assertFalse(bluScout.equals(null));
		assertFalse(bluScout.equals(new Object()));
		assertTrue(Piece.NULL_PIECE.equals(Piece.NULL_PIECE));
		assertFalse(Piece.NULL_PIECE.equals(bluScout));
	}

	@Test
	@Ignore
	public void testIsMoveable() {
		fail("Not yet implemented");
	}

}
