package strategy.playeratcahgsr.common;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import strategy.common.PlayerColor;
import strategy.playeratcahgsr.common.Piece;
import strategy.playeratcahgsr.common.PieceType;

/**
 * Test case for the Piece class.
 * 
 * @author Andrew Hurle, Alex Thornton-Clark, Gabriel Stern-Robbins
 * @version Sep 8, 2011
 */
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
		assertFalse(Piece.NULL_PIECE.equals(Piece.WATER_PIECE));
		assertTrue(Piece.WATER_PIECE.equals(Piece.WATER_PIECE));
		assertFalse(Piece.WATER_PIECE.equals(bluScout));
		assertFalse(Piece.WATER_PIECE.equals(Piece.NULL_PIECE));
		assertTrue(Piece.UNKNOWN_PIECE.equals(Piece.UNKNOWN_PIECE));
		assertFalse(Piece.UNKNOWN_PIECE.equals(bluScout));
		assertFalse(Piece.UNKNOWN_PIECE.equals(Piece.NULL_PIECE));
	}

	@Test
	public void testIsMoveable() {
		Piece flag = new Piece(PieceType.FLAG, PlayerColor.RED);
		Piece bomb = new Piece(PieceType.BOMB, PlayerColor.RED);
		assertTrue(bluScout.isMoveable());
		assertFalse(flag.isMoveable());
		assertFalse(bomb.isMoveable());
	}

}
